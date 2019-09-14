package by.bntu.fitr.poisit.tarasenko.project.model.entity;

import java.util.concurrent.TimeUnit;

public class Train {

    private String arrivalTime;
    private String departureTime;
    private String duration;
    private String arrivalStation;
    private String departureStation;
    private String number;
    private String exceptDays;
    private String rout;

    public Train() {
    }

    public Train(String arrivalTime, String departureTime, String duration, String arrivalStation,
                 String departureStation, String number, String exceptDays, String rout) {
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.duration = duration;
        this.arrivalStation = arrivalStation;
        this.departureStation = departureStation;
        this.number = number;
        this.exceptDays = exceptDays;
        this.rout = rout;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExceptDays() {
        return exceptDays;
    }

    public void setExceptDays(String exceptDays) {
        this.exceptDays = exceptDays;
    }

    @Override
    public String toString() {
        return "Train{" +
                "arrivalTime='" + arrivalTime + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", duration=" + duration +
                ", arrivalStation='" + arrivalStation + '\'' +
                ", departureStation='" + departureStation + '\'' +
                ", number='" + number + '\'' +
                ", exceptDays='" + exceptDays + '\'' +
                ", rout='" + rout +
                '}';
    }

    public String getRout() {
        return rout;
    }

    public void setRout(String rout) {
        this.rout = rout;
    }
}
