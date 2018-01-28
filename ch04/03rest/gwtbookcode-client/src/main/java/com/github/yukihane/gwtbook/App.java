package com.github.yukihane.gwtbook;

import com.github.yukihane.gwtbook.entity.CalcRequestData;
import com.github.yukihane.gwtbook.entity.CalcResult;
import com.github.yukihane.gwtbook.entity.Room;
import com.github.yukihane.gwtbook.entity.Student;
import com.github.yukihane.gwtbook.service.CalcService;
import com.github.yukihane.gwtbook.view.JsStudent;
import com.github.yukihane.gwtbook.view.TableStudent;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLTextAreaElement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class App implements EntryPoint {

    private final CalcService service = GWT.create(CalcService.class);

    private TableStudent table;

    private HTMLButtonElement addButton;

    private HTMLButtonElement sendButton;

    private HTMLTextAreaElement result;

    @Override
    public void onModuleLoad() {
        GWT.log("Hello, GWT!");
        Defaults.setServiceRoot("rest");

        final TableStudent table = (TableStudent) DomGlobal.document.body.getElementsByTagName("table-student").item(0);
        final HTMLButtonElement addButton = getButtonElement("addButton");
        final HTMLButtonElement sendButton = getButtonElement("sendButton");
        // final HTMLTextAreaElement result = getElementByName("result");

        addButton.addEventListener("click", e -> table.addRow());
        sendButton.addEventListener("click", ev -> {
            sendToServer(table.getStudents());
        });

        this.table = table;
        this.addButton = addButton;
        this.sendButton = sendButton;
        // this.result = result;
    }

    private void sendToServer(final JsStudent[] students) {
        final List<Student> list = Stream.of(students).map(e -> {
            double height = 0.0;
            try {
                height = Double.valueOf(e.getHeight());
            } catch (final NumberFormatException ex) {
            }
            return new Student(e.getName(), height, Room.valueOf(e.getRoom()));
        }).collect(Collectors.toList());

        final CalcRequestData data = new CalcRequestData();
        data.setStudents(list);

        service.calc(data, new MethodCallback<CalcResult>() {
            @Override
            public void onSuccess(final Method method, final CalcResult response) {
                final String text = createResultPanel(response);
            }

            @Override
            public void onFailure(final Method method, final Throwable exception) {
                GWT.log("Error received.", exception);
            }
        });
    }

    private String createResultPanel(final CalcResult result) {

        final StringBuilder b = new StringBuilder();
        result.getAverages().entrySet().stream().sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey())).forEach(e -> {
            b.append(e.getKey().getDisplayName() + " の身長平均値は " + e.getValue() + " です。\n");
        });
        b.append("最も高い身長の人は " + result.getHighest().getName() + " さんです。");
        return b.toString();
    }

    private static HTMLButtonElement getButtonElement(final String name) {
        final Optional<Element> element = DomGlobal.document.body.getElementsByTagName("button").asList().stream()
                .filter(e -> name.equals(e.getAttribute("name"))).findFirst();

        return (HTMLButtonElement) (element.get());
    }

    private static <T extends HTMLElement> T getElementByTagName(final String tagName) {
        return (T) DomGlobal.document.body.getElementsByTagName(tagName).item(0);
    }
}
