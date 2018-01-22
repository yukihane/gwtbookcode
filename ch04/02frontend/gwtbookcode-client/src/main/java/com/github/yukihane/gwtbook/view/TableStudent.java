package com.github.yukihane.gwtbook.view;

import jsinterop.annotations.JsType;

@JsType(isNative = true)
public interface TableStudent {

    void addRow();

    JsStudent[] getStudents();
}
