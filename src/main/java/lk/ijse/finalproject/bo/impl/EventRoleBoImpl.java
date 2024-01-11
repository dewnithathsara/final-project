package lk.ijse.finalproject.bo.impl;

import lk.ijse.finalproject.bo.custom.EventRoleBo;
import lk.ijse.finalproject.dao.custom.EventRoleDao;
import lk.ijse.finalproject.dao.impl.EventRoleDaoImpl;
import lk.ijse.finalproject.dto.EventDesignDto;
import lk.ijse.finalproject.dto.EventRoleDto;
import lk.ijse.finalproject.entity.Event;
import lk.ijse.finalproject.entity.EventRole;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventRoleBoImpl implements EventRoleBo {
    public EventRoleDao eventRoleDaoImpl = new EventRoleDaoImpl();
    @Override
    public boolean assignRole(EventRoleDto dto) throws SQLException, ClassNotFoundException {
        return eventRoleDaoImpl.assignRole(new EventRole(dto.getEmpId(),dto.getTask(),dto.getAid(),dto.getStatus()));
    }

    @Override
    public boolean DeleteEventRole(String id) throws SQLException, ClassNotFoundException {
        return eventRoleDaoImpl.DeleteEventRole(id);
    }

    @Override
    public List<EventRoleDto> getAllEventRole(String aid) throws SQLException, ClassNotFoundException {
        ArrayList<EventRole> events = (ArrayList<EventRole>) eventRoleDaoImpl.getAllEventRole(aid);
        ArrayList<EventRoleDto> dto = new ArrayList<>();
        for(EventRole eventsrole:events){
            dto.add(new EventRoleDto(eventsrole.getEmpId(),eventsrole.getAid(),eventsrole.getTask(),eventsrole.getStatus()));
        }
        return dto;
    }

    @Override
    public EventRoleDto showallrole(String serchId) throws SQLException, ClassNotFoundException {
        EventRole entity=eventRoleDaoImpl.showallrole(serchId);
        return new EventRoleDto(entity.getEmpId(), entity.getAid(), entity.getTask(), entity.getStatus());
    }

    @Override
    public boolean updateEventRole(EventRoleDto dto) throws SQLException, ClassNotFoundException {
        return eventRoleDaoImpl.updateEventRole(new EventRole(dto.getEmpId(),dto.getTask(),dto.getAid(),dto.getStatus()));
    }
}
