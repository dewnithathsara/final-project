package lk.ijse.finalproject.bo.custom;

import lk.ijse.finalproject.bo.SuperBo;
import lk.ijse.finalproject.dto.EventRoleDto;

import java.sql.SQLException;
import java.util.List;

public interface EventRoleBo extends SuperBo {
    boolean assignRole(EventRoleDto dto) throws SQLException, ClassNotFoundException;
    boolean DeleteEventRole(String id) throws SQLException, ClassNotFoundException;
    List<EventRoleDto> getAllEventRole(String aid) throws SQLException, ClassNotFoundException;
    EventRoleDto showallrole(String serchId) throws SQLException, ClassNotFoundException;
    boolean updateEventRole(EventRoleDto dto) throws SQLException, ClassNotFoundException;
}
