package com.github.yukihane.gwtbook.service;

import com.github.yukihane.gwtbook.entity.CalcRequestData;
import com.github.yukihane.gwtbook.entity.CalcResult;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

public interface CalcService extends RestService {

    @POST
    @Path("calc")
    void calc(CalcRequestData data, MethodCallback<CalcResult> callback);
}
