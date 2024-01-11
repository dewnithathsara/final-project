package lk.ijse.finalproject.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class VendorCollabaration {
    private String sId;
    private String eId;
    private String vId;
    private String desc;
    private LocalTime time;
    private LocalDate date;
    private double price;

    public VendorCollabaration() {
    }

    public VendorCollabaration(String eId, String sId, String vId, String desc, LocalTime time, LocalDate date, double price) {
        this.sId = sId;
        this.eId = eId;
        this.vId = vId;
        this.desc = desc;
        this.time = time;
        this.date = date;
        this.price = price;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getvId() {
        return vId;
    }

    public void setvId(String vId) {
        this.vId = vId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
