package com.github.yukihane.gwtbook.widget;

import com.github.yukihane.gwtbook.entity.Room;
import com.github.yukihane.gwtbook.entity.Student;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * APIドキュメントのサンプルやShowCaseの実装コードを見つつ実装.
 *
 * @see http://www.gwtproject.org/javadoc/latest/com/google/gwt/user/cellview/client/CellTable.html
 * @see http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCellTable
 */
public class StudentTable extends CellTable<Student> {

    public StudentTable() {
        super();

        final Column<Student, String> nameCol = new Column<Student, String>(new EditTextCell()) {
            @Override
            public String getValue(final Student object) {
                final String e = object.getName();
                return e == null ? "" : e;
            }
        };
        nameCol.setFieldUpdater((index, object, value) -> {
            object.setName(value);
        });
        setColumnWidth(nameCol, 50, Unit.PCT);
        addColumn(nameCol, "名前");

        final Column<Student, String> heightCol = new Column<Student, String>(new EditTextCell()) {
            @Override
            public String getValue(final Student object) {
                final double h = object.getHeight();
                return h == 0.0 ? "" : Objects.toString(object.getHeight());
            }
        };
        heightCol.setFieldUpdater((index, object, value) -> {
            try {
                final Double v = Double.valueOf(value);
                object.setHeight(v);
            } catch (final NumberFormatException e) {
                // エラー処理は省略
            }
        });
        setColumnWidth(heightCol, 100, Unit.PX);
        addColumn(heightCol, "身長");

        final List<String> roomNames = Stream.of(Room.values()).map(e -> e.getDisplayName())
                .collect(Collectors.toList());
        final SelectionCell roomCell = new SelectionCell(roomNames);
        final Column<Student, String> roomCol = new Column<Student, String>(roomCell) {
            @Override
            public String getValue(final Student object) {
                final Room e = object.getRoom();
                final Room r = e == null ? Room.A : e;
                return r.getDisplayName();
            }
        };
        roomCol.setFieldUpdater(new FieldUpdater<Student, String>() {

            @Override
            public void update(final int index, final Student object, final String value) {
                for(final Room e : Room.values()) {
                    if(e.getDisplayName().equals(value)) {
                        object.setRoom(e);
                        return;
                    }
                }

                object.setRoom(Room.A);
                return;
            }
        });
        setColumnWidth(roomCol, 100, Unit.PX);
        addColumn(roomCol, "学級クラス");
    }
}
