package com.eswinpalacios.clients.dto;

public class KPIResponse {
    private double average;
    private double standarDeviation;

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getStandarDeviation() {
        return standarDeviation;
    }

    public void setStandarDeviation(double standarDeviation) {
        this.standarDeviation = standarDeviation;
    }
}
