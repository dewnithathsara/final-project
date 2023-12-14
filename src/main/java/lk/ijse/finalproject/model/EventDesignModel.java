package lk.ijse.finalproject.model;

import javafx.scene.control.Alert;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.dto.EventDesignDto;
import lk.ijse.finalproject.dto.EventRoleDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDesignModel {

    public CollabaratingModel collabaratingModel=new CollabaratingModel();
    public String generateEventDesignId() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT eid FROM event ORDER BY eid DESC LIMIT 1  ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitEventId(resultSet.getString(1));
        }
        return splitEventId(null);
    }

    private String splitEventId(String currentEid) {
        if (currentEid != null) {
            String[] split = currentEid.split("Z0");
            int id = Integer.parseInt(split[1]);
            id++;
            return "Z00" + id;
        } else {
            return "Z001";
        }
    }

    public boolean saveEvent(EventDesignDto dto) throws SQLException, ClassNotFoundException {
      //  Connection connection=null;
       Connection  connection=DbConnection.getInstance().getConnection();
       // connection.setAutoCommit(false);
        String sql="INSERT INTO event(eid,type,location,aId,time,event_date,theme,event_status) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pstm= connection.prepareStatement(sql);
        pstm.setString(1, dto.getEid());
        pstm.setString(2, dto.getType());
        pstm.setString(3, dto.getLocation());
        pstm.setString(4, dto.getaId());
        pstm.setTime(5, Time.valueOf(dto.getTime()));
        pstm.setDate(6, Date.valueOf(dto.getDate()));
        pstm.setString(7, dto.getTheme());
        pstm.setString(8, dto.getStatus());
        return pstm.executeUpdate()>0;
    }

    public List<EventDesignDto> getAllevents() throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM event";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet resultSet= pstm.executeQuery();
        ArrayList<EventDesignDto> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new EventDesignDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getTime(5).toLocalTime(),
                            resultSet.getDate(6).toLocalDate(),
                            resultSet.getString(7),
                            resultSet.getString(8)

                    )
            );
        }
        System.out.println(dtoList);
        return dtoList;
    }

    public boolean updateEvent(EventDesignDto dto) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE event SET type=?,location=?,aId=?,time=?,date=?,theme=?,status=? WHERE eid=?";
        PreparedStatement pstm= connection.prepareStatement(sql);
        pstm.setString(1,dto.getType());
        pstm.setString(2, dto.getLocation());
        pstm.setString(3,dto.getaId());
        pstm.setString(4, String.valueOf(dto.getTime()));
        pstm.setString(5, String.valueOf(dto.getDate()));
        pstm.setString(6, dto.getTheme());
        pstm.setString(7, dto.getStatus());
        pstm.setString(8,dto.getEid());
        return pstm.executeUpdate()>0;
    }

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
    public int countCompletedEvent(String event_status) throws SQLException, ClassNotFoundException {
        int totalCompleted = 0;


        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(eid) AS total_eventCompleted_count FROM event WHERE event_status=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, event_status);



        try (ResultSet resultSet = pstm.executeQuery()) {
            if (resultSet.next()) {
                totalCompleted = resultSet.getInt("total_eventCompleted_count");
            }
        }


        return totalCompleted;
    }




    public String popularEvent() throws SQLException, ClassNotFoundException {
        String type=null;
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT  MAX(type) AS most_popular_event, COUNT(*) AS event_count FROM event GROUP BY type ORDER BY event_count DESC LIMIT 1";
       PreparedStatement pstm= connection.prepareStatement(sql);
        try (ResultSet resultSet = pstm.executeQuery()) {
            if (resultSet.next()) {
                type = resultSet.getString("most_popular_event");
            }
        }catch (Exception e){
            System.out.println(" Popular event: "+e.getMessage());
        }


        return type;
    }

    public List<EventDesignDto> getAllCurrentEvent(LocalDate date) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM event WHERE event_date=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1, String.valueOf(date));

        ResultSet resultSet= pstm.executeQuery();
        ArrayList<EventDesignDto> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new EventDesignDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getTime(5).toLocalTime(),
                            resultSet.getDate(6).toLocalDate(),
                            resultSet.getString(7),
                            resultSet.getString(8)

                    )
            );
        }

        return dtoList;

    }

    public ArrayList<EventDesignDto> getAllCurrentEvents() throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM event ";
        PreparedStatement pstm=connection.prepareStatement(sql);


        ResultSet resultSet= pstm.executeQuery();
        ArrayList<EventDesignDto> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new EventDesignDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getTime(5).toLocalTime(),
                            resultSet.getDate(6).toLocalDate(),
                            resultSet.getString(7),
                            resultSet.getString(8)

                    )
            );
        }

        return dtoList;

    }

    public EventDesignDto showAllAppointments(String serchId) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("select * from customer where cId=?");
        pstm.setString(1,serchId);
        ResultSet resultSet= pstm.executeQuery();

        if (resultSet.next()){

            return new EventDesignDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),

                    resultSet.getTime(5).toLocalTime(),
                    resultSet.getDate(6).toLocalDate(),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );

        }

        return  null;



    }
}





