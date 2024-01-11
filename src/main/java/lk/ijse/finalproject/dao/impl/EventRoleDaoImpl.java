package lk.ijse.finalproject.dao.impl;

import javafx.scene.control.Alert;
import lk.ijse.finalproject.dao.SqlUtil;
import lk.ijse.finalproject.dao.custom.EventRoleDao;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.EventRoleDto;
import lk.ijse.finalproject.entity.Event;
import lk.ijse.finalproject.entity.EventRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventRoleDaoImpl implements EventRoleDao {

    @Override
    public boolean assignRole(EventRole dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("INSERT INTO eventRole VALUES(?,?,?,?)",dto.getEmpId(),dto.getTask(),dto.getAid(),dto.getStatus());
    }
    @Override
    public boolean DeleteEventRole(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("DELETE FROM eventRole WHERE empId=?",id);

    }

    @Override
    public boolean updateEventRole(EventRole dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery(" UPDATE eventRole SET aId=?,task=?,task_status=? WHERE empId=?",dto.getEmpId(),dto.getTask(),dto.getAid(),dto.getStatus());
    }
    @Override
    public List<EventRole> getAllEventRole(String aid) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("SELECT * FROM eventRole WHERE aId=?",aid);
        ArrayList<EventRole> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new EventRole(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;
    }
    public void showeventRole(String aid) {
    }
    @Override
    public EventRole showallrole(String serchId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet= SqlUtil.testQuery("SELECT * FROM eventRole WHERE empId=?",serchId);
        EventRole dto=null;
        if(resultSet.next()) {
            String empId = resultSet.getString(1);
            String aid = resultSet.getString(1);
            String task = resultSet.getString(1);
            String task_status = resultSet.getString(1);
            dto=new EventRole(empId,aid,task,task_status);
        }
        return dto;



    }
}
