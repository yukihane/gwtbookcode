package com.github.yukihane.gwtbook.entity;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CalcRequestData implements Serializable {

    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(final List<Student> students) {
        this.students = students;
    }

}
