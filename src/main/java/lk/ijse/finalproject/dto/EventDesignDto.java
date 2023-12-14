package lk.ijse.finalproject.dto;

import lk.ijse.finalproject.dto.tm.EventTm;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventDesignDto {

    private String eid;
    private String type;
    private String location;
    private String aId;
    private LocalDate date;
    private LocalTime time;
    private String theme;
    private String status;

    //private List<EventTm> eventList=new ArrayList<>();
    public EventDesignDto(String eId, String type, String location, String aId, LocalTime time, LocalDate date,String theme,String status) {
        this.eid=eId;
        this.type=type;
        this.location=location;
        this.aId=aId;
        this.time=time;
        this.date=date;
        this.theme=theme;
        this.status=status;
    }
    public EventDesignDto(String eId, String type, String location, String aId, LocalTime time, LocalDate date,String status) {
        this.eid=eId;
        this.type=type;
        this.location=location;
        this.aId=aId;
        this.time=time;
        this.date=date;
        this.theme=theme;
        this.status=status;
    }

    @Override
    public String toString() {
        return "EventDesignDto{" +
                "eid='" + eid + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", aId='" + aId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", theme='" + theme + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}
