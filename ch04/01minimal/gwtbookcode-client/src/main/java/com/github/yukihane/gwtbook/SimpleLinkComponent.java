package com.github.yukihane.gwtbook;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.client.component.VueComponent;
import jsinterop.annotations.JsProperty;

@Component
public class SimpleLinkComponent extends VueComponent {
    @JsProperty String linkName = "Hello Vue GWT!";
}