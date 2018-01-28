package com.github.yukihane.gwtbook.entity;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class CalcResult implements Serializable {

    /** 学級クラスごとの身長平均値 */
    private Map<Room, Double> averages;

    /** 最も高い身長の生徒 */
    private Student highest;

    public CalcResult() {
    }

    public CalcResult(final Map<Room, Double> averages, final Student highest) {
        this.averages = averages;
        this.highest = highest;
    }

    public Map<Room, Double> getAverages() {
        return averages;
    }

    public void setAverages(final Map<Room, Double> averages) {
        this.averages = averages;
    }

    public Student getHighest() {
        return highest;
    }

    public void setHighest(final Student highest) {
        this.highest = highest;
    }

}
