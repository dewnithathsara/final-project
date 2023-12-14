package lk.ijse.finalproject.dto.tm;

import java.time.LocalTime;

public class EventTm {

    private String eid;
    private String type;
    private String location;

    private LocalTime time;
    private String theme;
    private String event_status;
    public EventTm(String eid, String type, String location, LocalTime time, String theme, String event_status) {
        this.eid = eid;
        this.type = type;
        this.location = location;

        this.time = time;
        this.theme = theme;
        this.event_status = event_status;
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

    public String getEvent_status() {
        return event_status;
    }

    public void setEvent_status(String event_status) {
        this.event_status = event_status;
    }

    @Override
    public String toString() {
        return "EventTm{" +
                "eid='" + eid + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +

                ", time=" + time +
                ", theme='" + theme + '\'' +
                ", event_status='" + event_status + '\'' +
                '}';
    }
}
