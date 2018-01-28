package com.github.yukihane.gwtbook.view;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true)
public interface JsStudent {

    @JsProperty
    String getName();

    @JsProperty
    String getHeight();

    @JsProperty
    String getRoom();
}
