package com.github.yukihane.gwtbook;

import com.github.yukihane.gwtbook.entity.CalcRequestData;
import com.github.yukihane.gwtbook.entity.CalcResult;
import com.github.yukihane.gwtbook.service.CalcService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet("/gwtbookapp/calc")
public class CalcServiceImpl extends RemoteServiceServlet implements CalcService {
    @Override
    public CalcResult calculate(final CalcRequestData req) {
        System.out.println("Called: calculate");
        return null;
    }
}
