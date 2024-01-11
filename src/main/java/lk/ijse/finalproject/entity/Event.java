package lk.ijse.finalproject.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private String eid;
    private String type;
    private String location;
    private String aId;
    private LocalDate date;
    private LocalTime time;
    private String theme;
    private String status;

    public Event() {
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Event(String eid, String type, String location, String aId, LocalDate date, LocalTime time, String theme, String status) {
        this.eid = eid;
        this.type = type;
        this.location = location;
        this.aId = aId;
        this.date = date;
        this.time = time;
        this.theme = theme;
        this.status = status;
    }
}
