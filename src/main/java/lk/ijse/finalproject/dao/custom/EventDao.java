package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.EventDesignDto;
import lk.ijse.finalproject.entity.Event;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface EventDao extends CrudDao<Event> {

    int countCompletedEvent(String event_status) throws SQLException, ClassNotFoundException;
    String popularEvent() throws SQLException, ClassNotFoundException;
    List<Event> getAllCurrentEvent(LocalDate date) throws SQLException, ClassNotFoundException;
    ArrayList<Event> getAllCurrentEvents() throws SQLException, ClassNotFoundException;
    Event showAllAppointments(String serchId) throws SQLException, ClassNotFoundException;



}
