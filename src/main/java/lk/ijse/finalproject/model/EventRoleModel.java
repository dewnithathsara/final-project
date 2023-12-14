package lk.ijse.finalproject.model;

import javafx.scene.control.Alert;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.EventRoleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventRoleModel {
    public boolean assignRole(EventRoleDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        boolean isAssignmentSuccessful = false;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO eventRole VALUES(?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getEmpId());
            pstm.setString(2, dto.getAid());
            pstm.setString(3, dto.getTask());
            pstm.setString(4, dto.getStatus());
            isAssignmentSuccessful = pstm.executeUpdate() > 0;

            if (isAssignmentSuccessful) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            connection.setAutoCommit(true);
        }
        return isAssignmentSuccessful;
    }

    public boolean DeleteEventRole(String id) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM eventRole WHERE empId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;

    }


    public boolean updateEventRole(EventRoleDto dto) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql=" UPDATE eventRole SET aId=?,task=?,task_status=? WHERE empId=?";
        PreparedStatement pstm= connection.prepareStatement(sql);
       // PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,dto.getAid());
        pstm.setString(2, dto.getTask());
        pstm.setString(3, dto.getStatus());

        pstm.setString(4,dto.getEmpId());
        boolean isUpdated=pstm.executeUpdate()>0;
        return isUpdated;
    }

    public List<EventRoleDto> getAllEventRole(String aid) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM eventRole WHERE aId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1, aid);

        ResultSet resultSet= pstm.executeQuery();
        ArrayList<EventRoleDto> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new EventRoleDto(
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

    public EventRoleDto showallrole(String serchId) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM eventRole WHERE empId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,serchId);
        ResultSet resultSet= pstm.executeQuery();
        EventRoleDto dto=null;
        if(resultSet.next()) {
            String empId = resultSet.getString(1);
            String aid = resultSet.getString(1);
            String task = resultSet.getString(1);
            String task_status = resultSet.getString(1);
            dto=new EventRoleDto(empId,aid,task,task_status);
        }
        return dto;



    }
}
