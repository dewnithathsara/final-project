package lk.ijse.finalproject.dao.impl;

import javafx.scene.control.Alert;
import lk.ijse.finalproject.dao.SqlUtil;
import lk.ijse.finalproject.dao.custom.EventDao;
import lk.ijse.finalproject.dao.impl.CollabaratingDaoImpl;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.EventDesignDto;
import lk.ijse.finalproject.entity.Event;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDaoImpl implements EventDao {

    public CollabaratingDaoImpl collabaratingModel=new CollabaratingDaoImpl();
    @Override
    public String generateEventDesignId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.testQuery("SELECT eid FROM event ORDER BY eid DESC LIMIT 1  ");
        if (resultSet.next()) {
            String id= resultSet.getString("eid");
            int newCustomerId = Integer.parseInt(id.replace("Z00", "")) + 1;
            return String.format("Z00%03d", newCustomerId);
        } else {
            return "Z001";
        }

    }
    @Override
    public boolean saveEvent(Event dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("INSERT INTO event(eid,type,location,aId,time,event_date,theme,event_status) VALUES(?,?,?,?,?,?,?,?)",dto.getEid(),dto.getType(),dto.getLocation(),dto.getaId(),dto.getTime(),dto.getDate(),dto.getTheme(),dto.getStatus());
    }
    @Override
    public List<Event> getAllevents() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("SELECT * FROM event");
        ArrayList<Event> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new Event(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDate(5).toLocalDate(),
                            resultSet.getTime(6).toLocalTime(),
                            resultSet.getString(7),
                            resultSet.getString(8)

                    )
            );
        }
        System.out.println(dtoList);
        return dtoList;
    }
    @Override
    public boolean updateEvent(Event dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("UPDATE event SET type=?,location=?,aId=?,time=?,date=?,theme=?,status=? WHERE eid=?",dto.getType(),dto.getLocation(),dto.getaId(),dto.getTime(),dto.getDate(),dto.getTheme(),dto.getStatus(),dto.getEid());
    }
    //transaction part
    @Override
    public boolean deletEevent(String id) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        boolean isDeleted = false;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            String sql = "DELETE FROM event WHERE eid=?";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, id);
            isDeleted = pstm.executeUpdate() > 0;
            if (isDeleted) {
                connection.commit();
                collabaratingModel.deleteCollabaration(id);
            } else {
                connection.rollback();
            }
        } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }finally {
            connection.rollback();
        }
        return isDeleted;
    }
    @Override
    public int countCompletedEvent(String event_status) throws SQLException, ClassNotFoundException {
        int totalCompleted = 0;
       ResultSet resultSet = SqlUtil.testQuery("SELECT COUNT(eid) AS total_eventCompleted_count FROM event WHERE event_status=?",event_status);
            if (resultSet.next()) {
                totalCompleted = resultSet.getInt("total_eventCompleted_count");
            }
        return totalCompleted;
    }



    @Override
    public String popularEvent() throws SQLException, ClassNotFoundException {
        String type=null;
       ResultSet resultSet = SqlUtil.testQuery("SELECT  MAX(type) AS most_popular_event, COUNT(*) AS event_count FROM event GROUP BY type ORDER BY event_count DESC LIMIT 1");
            if (resultSet.next()) {
                type = resultSet.getString("most_popular_event");
            }
        return type;
    }
    @Override
    public List<Event> getAllCurrentEvent(LocalDate date) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("SELECT * FROM event WHERE event_date=?",date);
        ArrayList<Event> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new Event(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDate(5).toLocalDate(),
                            resultSet.getTime(6).toLocalTime(),
                            resultSet.getString(7),
                            resultSet.getString(8)
                    )
            );
        }
        return dtoList;
    }
    @Override
    public ArrayList<Event> getAllCurrentEvents() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("SELECT * FROM event ");
        ArrayList<Event> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new Event(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDate(5).toLocalDate(),
                            resultSet.getTime(6).toLocalTime(),
                            resultSet.getString(7),
                            resultSet.getString(8)
                    )
            );
        }
        return dtoList;
    }
    @Override
    public Event showAllAppointments(String serchId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("select * from customer where cId=?",serchId);
        if (resultSet.next()){
            return new Event(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getTime(6).toLocalTime(),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
        return  null;
    }
}





