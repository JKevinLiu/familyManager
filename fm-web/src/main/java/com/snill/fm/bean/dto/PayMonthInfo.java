package com.snill.fm.bean.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PayMonthInfo implements Serializable {
    /**
     * var data = {
     xAxis:["201801","201802","201803","201804","201805","201806"],
     starRate:[180000/100,150000/100,270000/100,250000/100,300000/100,200000/100],
     starNum:[0,3,8,12,15,16],
     rectColor:["#b5cb85","GREEN","#b5cb85","#b5cb85","RED","#b5cb85"],
     };
     */
    private int width;
    private int height;
    private double maxValue;
    private List<String> xAxis;
    private List<Double> starRate;
    private List<Integer> starNum;
    private List<String> rectColor;

    public PayMonthInfo(){
        width = 800;
        height = 480;

        xAxis = new ArrayList<>();
        starRate = new ArrayList<>();
        starNum = new ArrayList<>();
        rectColor = new ArrayList<>();

        starNum.add(0);starNum.add(3);starNum.add(8);starNum.add(12);starNum.add(15);starNum.add(16);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public List<Double> getStarRate() {
        return starRate;
    }

    public void setStarRate(List<Double> starRate) {
        this.starRate = starRate;
    }

    public List<Integer> getStarNum() {
        return starNum;
    }

    public void setStarNum(List<Integer> starNum) {
        this.starNum = starNum;
    }

    public List<String> getRectColor() {
        return rectColor;
    }

    public void setRectColor(List<String> rectColor) {
        this.rectColor = rectColor;
    }
}
