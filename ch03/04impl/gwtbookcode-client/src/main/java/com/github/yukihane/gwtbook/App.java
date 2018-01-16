package com.github.yukihane.gwtbook;

import com.github.yukihane.gwtbook.entity.CalcRequestData;
import com.github.yukihane.gwtbook.entity.CalcResult;
import com.github.yukihane.gwtbook.entity.Room;
import com.github.yukihane.gwtbook.entity.Student;
import com.github.yukihane.gwtbook.service.CalcService;
import com.github.yukihane.gwtbook.service.CalcServiceAsync;
import com.github.yukihane.gwtbook.widget.StudentTable;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import java.util.ArrayList;

public class App implements EntryPoint {

    private final CalcServiceAsync calcService = GWT.create(CalcService.class);

    @Override
    public void onModuleLoad() {
        final TextArea field = new TextArea();
        field.setWidth("500px");
        field.setHeight("100px");

        final ListDataProvider<Student> studentProvider = new ListDataProvider<>();

        final Student student = new Student("初期 太郎", 170, Room.A);
        studentProvider.getList().add(student);

        final StudentTable cellTable = new StudentTable();
        cellTable.setWidth("500px", true);

        studentProvider.addDataDisplay(cellTable);

        final Button addButton = new Button("追加");
        addButton.addClickHandler(event -> {
            final Student e = new Student();
            studentProvider.getList().add(e);
            GWT.log("追加ボタンが押されました size: " + studentProvider.getList().size());
        });

        final Button sendButton = new Button("送信");
        sendButton.addClickHandler(event -> {

            final CalcRequestData input = new CalcRequestData();
            input.setStudents(new ArrayList<>(studentProvider.getList()));

            calcService.calculate(input, new AsyncCallback<CalcResult>() {

                @Override
                public void onSuccess(final CalcResult result) {
                    GWT.log("onSuccess: " + result);

                    field.setText(createResultPanel(result));
                }

                @Override
                public void onFailure(final Throwable caught) {
                    GWT.log("onFailure");
                }
            });
        });

        final VerticalPanel vpanel = new VerticalPanel();
        vpanel.add(cellTable);

        final FlowPanel fpanel = new FlowPanel();
        fpanel.add(addButton);
        fpanel.add(sendButton);

        vpanel.add(fpanel);

        vpanel.add(field);

        RootPanel.get().add(vpanel);

    }

    private String createResultPanel(final CalcResult result) {

        final StringBuilder b = new StringBuilder();
        result.getAverages().entrySet().stream().sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey())).forEach(e -> {
            b.append(e.getKey().getDisplayName() + " の身長平均値は " + e.getValue() + " です。\n");
        });
        b.append("最も高い身長の人は " + result.getHighest().getName() + " さんです。");
        return b.toString();
    }
}
