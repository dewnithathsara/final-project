package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.EventRoleDto;
import lk.ijse.finalproject.entity.Event;
import lk.ijse.finalproject.entity.EventRole;

import java.sql.SQLException;
import java.util.List;

public interface EventRoleDao extends CrudDao <EventRole>{

    EventRole showallrole(String serchId) throws SQLException, ClassNotFoundException;
    List<EventRole> getAllById(String aid) throws SQLException, ClassNotFoundException;
}
