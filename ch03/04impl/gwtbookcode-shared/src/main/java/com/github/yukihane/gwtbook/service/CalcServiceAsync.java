package com.github.yukihane.gwtbook.service;

import com.github.yukihane.gwtbook.entity.CalcRequestData;
import com.github.yukihane.gwtbook.entity.CalcResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CalcServiceAsync {
    void calculate(CalcRequestData input, AsyncCallback<CalcResult> callback);
}
