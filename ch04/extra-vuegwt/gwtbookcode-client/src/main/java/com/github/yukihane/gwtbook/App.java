package com.github.yukihane.gwtbook;

import com.axellience.vuegwt.core.client.VueGWT;
import com.axellience.vuegwt.gwt2.client.widget.VueGwtWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class App implements EntryPoint {

    @Override
    public void onModuleLoad() {
        GWT.log("Hello, GWT!");

        VueGWT.init();
        
        VueGwtWidget widget = new VueGwtWidget<>(SimpleLinkComponent.class);
        RootPanel.get().add(widget);
    }
}
