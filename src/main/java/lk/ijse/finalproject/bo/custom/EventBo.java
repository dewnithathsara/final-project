package lk.ijse.finalproject.bo.custom;

import lk.ijse.finalproject.bo.SuperBo;
import lk.ijse.finalproject.dto.EventDesignDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface EventBo extends SuperBo {
    String generateEventDesignId() throws SQLException, ClassNotFoundException;

    boolean saveEvent(EventDesignDto dto) throws SQLException, ClassNotFoundException;
    List<EventDesignDto> getAllevents() throws SQLException, ClassNotFoundException;
    boolean updateEvent(EventDesignDto dto) throws SQLException, ClassNotFoundException;
    boolean deletEevent(String id) throws SQLException, ClassNotFoundException;
    int countCompletedEvent(String event_status) throws SQLException, ClassNotFoundException;
    String popularEvent() throws SQLException, ClassNotFoundException;
    List<EventDesignDto> getAllCurrentEvent(LocalDate date) throws SQLException, ClassNotFoundException;
    ArrayList<EventDesignDto> getAllCurrentEvents() throws SQLException, ClassNotFoundException;
    EventDesignDto showAllAppointments(String serchId) throws SQLException, ClassNotFoundException;
}
