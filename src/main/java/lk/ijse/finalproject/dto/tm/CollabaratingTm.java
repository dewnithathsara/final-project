package lk.ijse.finalproject.dto.tm;

import java.time.LocalDate;
import java.time.LocalTime;


public class CollabaratingTm {
    private  String Sid;
    private String Vid;
    private String feedback;
    private LocalTime time;
    private LocalDate date;
    private double price;

    public CollabaratingTm(String sid, String vid, String feedback, LocalTime time, LocalDate date, double price) {
        Sid = sid;
        Vid = vid;
        this.feedback = feedback;
        this.time = time;
        this.date = date;
        this.price = price;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getVid() {
        return Vid;
    }

    public void setVid(String vid) {
        Vid = vid;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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

    @Override
    public String toString() {
        return "CollabaratingTm{" +
                "Sid='" + Sid + '\'' +
                ", Vid='" + Vid + '\'' +
                ", feedback='" + feedback + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
