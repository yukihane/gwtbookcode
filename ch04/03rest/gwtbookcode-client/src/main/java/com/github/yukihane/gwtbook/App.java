package com.github.yukihane.gwtbook;

import com.github.yukihane.gwtbook.view.TableStudent;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLTextAreaElement;
import java.util.Optional;
import java.util.stream.Stream;

public class App implements EntryPoint {

    private TableStudent table;

    private HTMLButtonElement addButton;

    private HTMLButtonElement sendButton;

    private HTMLTextAreaElement result;

    @Override
    public void onModuleLoad() {
        GWT.log("Hello, GWT!");

        final TableStudent table = (TableStudent) DomGlobal.document.body.getElementsByTagName("table-student").item(0);
        final HTMLButtonElement addButton = getButtonElement("addButton");
        final HTMLButtonElement sendButton = getButtonElement("sendButton");
        // final HTMLTextAreaElement result = getElementByName("result");

        addButton.addEventListener("click", e -> table.addRow());
        sendButton.addEventListener("click", ev -> {
            Stream.of(table.getStudents()).forEach(e -> {
                DomGlobal.window.console.log(e.getName() + ", " + e.getHeight() + ", " + e.getRoom());
            });
        });

        this.table = table;
        this.addButton = addButton;
        this.sendButton = sendButton;
        // this.result = result;
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
