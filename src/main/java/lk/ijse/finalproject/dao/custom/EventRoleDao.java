package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.EventRoleDto;
import lk.ijse.finalproject.entity.Event;
import lk.ijse.finalproject.entity.EventRole;

import java.sql.SQLException;
import java.util.List;

public interface EventRoleDao extends CrudDao {
    boolean assignRole(EventRole dto) throws SQLException, ClassNotFoundException;
    boolean DeleteEventRole(String id) throws SQLException, ClassNotFoundException;
    List<EventRole> getAllEventRole(String aid) throws SQLException, ClassNotFoundException;
    EventRole showallrole(String serchId) throws SQLException, ClassNotFoundException;
    boolean updateEventRole(EventRole dto) throws SQLException, ClassNotFoundException;
}
