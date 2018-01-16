package com.github.yukihane.gwtbook.entity;

public class Student {

    /** 名前 */
    private String name;

    /** 身長 */
    private double height;

    /** 学級 */
    private Room room;

    public Student() {
    }

    public Student(final String name, final double height, final Room room) {
        this.name = name;
        this.height = height;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(final double height) {
        this.height = height;
    }

    public Room getRoom() {
        return room;
    }

    public void setClassName(final Room className) {
        this.room = className;
    }
}
