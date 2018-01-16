package com.github.yukihane.gwtbook;

import com.github.yukihane.gwtbook.entity.Room;
import com.github.yukihane.gwtbook.entity.Student;
import com.github.yukihane.gwtbook.widget.StudentTable;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class App implements EntryPoint {

    @Override
    public void onModuleLoad() {
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

        final VerticalPanel vpanel = new VerticalPanel();
        vpanel.add(cellTable);
        vpanel.add(addButton);

        RootPanel.get().add(vpanel);

    }
}
