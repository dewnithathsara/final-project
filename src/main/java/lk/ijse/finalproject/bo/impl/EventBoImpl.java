package lk.ijse.finalproject.bo.impl;

import lk.ijse.finalproject.bo.custom.EmployeeBo;
import lk.ijse.finalproject.bo.custom.EventBo;
import lk.ijse.finalproject.dao.custom.EventDao;
import lk.ijse.finalproject.dao.impl.EventDaoImpl;
import lk.ijse.finalproject.dto.EmployeeDto;
import lk.ijse.finalproject.dto.EventDesignDto;
import lk.ijse.finalproject.entity.Employee;
import lk.ijse.finalproject.entity.Event;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventBoImpl implements EventBo {
    public EventDao eventDaoImpl =new EventDaoImpl();
    @Override
    public String generateEventDesignId() throws SQLException, ClassNotFoundException {
        return eventDaoImpl.generateId();
    }

    @Override
    public boolean saveEvent(EventDesignDto dto) throws SQLException, ClassNotFoundException {
        return eventDaoImpl.save(new Event(dto.getEid(),dto.getType(),dto.getLocation(),dto.getaId(),dto.getDate(),dto.getTime(),dto.getTheme(),dto.getStatus()));
    }

    @Override
    public List<EventDesignDto> getAllevents() throws SQLException, ClassNotFoundException {
        ArrayList<Event> events = (ArrayList<Event>) eventDaoImpl.getAll();
        ArrayList<EventDesignDto> dto = new ArrayList<>();
        for (Event c : events) {
            dto.add(new EventDesignDto(c.getEid(),c.getType(),c.getLocation(),c.getaId(),c.getTime(),c.getDate(),c.getTheme(),c.getStatus()));
        }
        return dto;
    }
    @Override
    public boolean updateEvent(EventDesignDto dto) throws SQLException, ClassNotFoundException {
        return eventDaoImpl.update(new Event(dto.getEid(),dto.getType(),dto.getLocation(),dto.getaId(),dto.getDate(),dto.getTime(),dto.getTheme(),dto.getStatus()));
    }

    @Override
    public boolean deletEevent(String id) throws SQLException, ClassNotFoundException {
        return eventDaoImpl.delete(id);
    }

    @Override
    public int countCompletedEvent(String event_status) throws SQLException, ClassNotFoundException {
        return eventDaoImpl.countCompletedEvent(event_status);
    }

    @Override
    public String popularEvent() throws SQLException, ClassNotFoundException {
        return eventDaoImpl.popularEvent();
    }

    @Override
    public List<EventDesignDto> getAllCurrentEvent(LocalDate date) throws SQLException, ClassNotFoundException {
        ArrayList<Event> events = (ArrayList<Event>) eventDaoImpl.getAllCurrentEvent(date);
        ArrayList<EventDesignDto> dto = new ArrayList<>();
        for (Event c : events) {
            dto.add(new EventDesignDto(c.getEid(),c.getType(),c.getLocation(),c.getaId(),c.getTime(),c.getDate(),c.getTheme(),c.getStatus()));
        }
        return dto;
    }

    @Override
    public ArrayList<EventDesignDto> getAllCurrentEvents() throws SQLException, ClassNotFoundException {
        ArrayList<Event> events = (ArrayList<Event>) eventDaoImpl.getAll();
        ArrayList<EventDesignDto> dto = new ArrayList<>();
        for (Event c : events) {
            dto.add(new EventDesignDto(c.getEid(),c.getType(),c.getLocation(),c.getaId(),c.getTime(),c.getDate(),c.getTheme(),c.getStatus()));
        }
        return dto;
    }

    @Override
    public EventDesignDto showAllAppointments(String serchId) throws SQLException, ClassNotFoundException {
        Event entity=eventDaoImpl.showAllAppointments(serchId);
        return new EventDesignDto(entity.getEid(),entity.getType(),entity.getLocation(),entity.getaId(),entity.getTime(),entity.getDate(),entity.getTheme(),entity.getStatus());
    }
}
