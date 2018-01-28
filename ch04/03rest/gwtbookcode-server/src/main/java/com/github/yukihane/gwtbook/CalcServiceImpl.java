package com.github.yukihane.gwtbook;

import com.github.yukihane.gwtbook.entity.CalcRequestData;
import com.github.yukihane.gwtbook.entity.CalcResult;
import com.github.yukihane.gwtbook.entity.Room;
import com.github.yukihane.gwtbook.entity.Student;
import com.github.yukihane.gwtbook.service.CalcService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet("/gwtbookapp/calc")
public class CalcServiceImpl extends RemoteServiceServlet implements CalcService {

    private static final Student NULL_OBJ = new Student();

    @Override
    public CalcResult calculate(final CalcRequestData req) {
        System.out.println("Called: calculate");

        final Map<Room, CalcResource> resources = new EnumMap<>(Room.class);

        Student highest = NULL_OBJ;

        for (final Student s : req.getStudents()) {
            CalcResource res = resources.get(s.getRoom());
            if (res == null) {
                res = new CalcResource();
                resources.put(s.getRoom(), res);
            }
            res.num += 1;
            res.total += s.getHeight();

            highest = max(highest, s);
        }

        final Map<Room, Double> averages = new HashMap<>();
        for (final Entry<Room, CalcResource> entry : resources.entrySet()) {
            final CalcResource v = entry.getValue();
            final double ave = v.total / v.num;
            averages.put(entry.getKey(), ave);
        }

        final CalcResult result = new CalcResult(averages, highest);

        return result;
    }

    private static Student max(final Student v1, final Student v2) {
        return v1.getHeight() > v2.getHeight() ? v1 : v2;
    }

    private static class CalcResource {
        int num;
        double total;
    }
}
