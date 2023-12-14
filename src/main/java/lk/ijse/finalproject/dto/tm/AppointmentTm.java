package lk.ijse.finalproject.dto.tm;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentTm {
    private String aid;
    private String status;
    private double fee;
    private String cid;
    private LocalTime app_time;
    private LocalDate app_date;



    public AppointmentTm(String aid, double fee, String status, LocalDate app_date,LocalTime app_time,String cid) {
        this.aid = aid;
        this.fee = fee;
        this.status = status;
        this.app_date =app_date;
        this.app_time =app_time;
        this.cid = cid;
        System.out.println("tm initialize");
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

    public String getaId() {
        return aid;
    }

    public void setaId(String aid) {
        this.aid = aid;
    }

    public String getcId() {
        System.out.println(cid);
        return cid;
    }

    public void setcId(String cid) {
        this.cid = cid;
    }

    public LocalTime getTime() {
        return app_time;
    }

    public void setTime(LocalTime app_time) {
        this.app_time = app_time;
    }

    public LocalDate getDate() {
        return app_date;
    }

    public void setDate(LocalDate app_date) {
        this.app_date = app_date;
    }

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "aid='" + aid + '\'' +
                ", fee=" + fee +
                ", status='" + status + '\'' +
                ", app_date='" + app_date + '\'' +
                ", app_time='" +app_time + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
