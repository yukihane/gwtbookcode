package com.github.yukihane.gwtbook.service;

import com.github.yukihane.gwtbook.entity.CalcRequestData;
import com.github.yukihane.gwtbook.entity.CalcResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("calc")
public interface CalcService extends RemoteService {
    CalcResult calculate(CalcRequestData req);
}
