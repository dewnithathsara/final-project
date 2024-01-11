package lk.ijse.finalproject.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private String aid;
    private String status;
    private double fee;
    private String cid;
    private LocalTime app_time;
    private LocalDate app_date;

    public Appointment() {
    }
   // aid,fee,status,app_date,app_time,cid
    public Appointment(String aid, double fee,String status, LocalTime app_time, LocalDate app_date, String cid) {
        this.aid = aid;
        this.status = status;
        this.fee = fee;
        this.cid = cid;
        this.app_time = app_time;
        this.app_date = app_date;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public LocalTime getApp_time() {
        return app_time;
    }

    public void setApp_time(LocalTime app_time) {
        this.app_time = app_time;
    }

    public LocalDate getApp_date() {
        return app_date;
    }

    public void setApp_date(LocalDate app_date) {
        this.app_date = app_date;
    }
}
