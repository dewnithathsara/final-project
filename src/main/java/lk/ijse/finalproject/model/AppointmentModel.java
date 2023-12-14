package lk.ijse.finalproject.model;

import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.ClientDto;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel {
    public String generateNextAppId() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT aid FROM appointment ORDER BY aid DESC LIMIT 1  ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitAppointmentId(resultSet.getString(1));
        }
        return splitAppointmentId(null);
    }

    private String splitAppointmentId(String currentAid) {
        if (currentAid != null) {
            String[] split = currentAid.split("A0");
            int id = Integer.parseInt(split[1]);
            id++;
            return "A00" + id;
        } else {
            return "A001";
        }
    }

    public boolean makeAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        // Connection connection = null;

        Connection connection = DbConnection.getInstance().getConnection();
        //ocnnection.setAutoCommit(false);
        String sql = "INSERT INTO appointment(aid,fee,status,app_date,app_time,cid) VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getaId());
        System.out.println(dto.getaId());
        pstm.setString(2, String.valueOf(dto.getFee()));
        pstm.setString(3, dto.getStatus());
        pstm.setDate(4, Date.valueOf(dto.getDate()));
        pstm.setTime(5, Time.valueOf(dto.getTime()));
        pstm.setString(6, dto.getcId());
        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;


    }


    public AppointmentDto searchId(String aId) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM appointment WHERE aid=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, aId);
        ResultSet resultSet = pstm.executeQuery();
        AppointmentDto appointmentDto = null;
        if (resultSet.next()) {
            String aid = resultSet.getString(1);

            double fee = resultSet.getDouble(2);
            String status = resultSet.getString(3);
            LocalDate date = resultSet.getDate(4).toLocalDate();
            LocalTime time = resultSet.getTime(5).toLocalTime();
            String cId = resultSet.getString(6);

            appointmentDto = new AppointmentDto(aid, fee, status, date, time, cId);
        }
        return appointmentDto;
    }

    public List<AppointmentDto> getAllAppointment() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM appointment";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<AppointmentDto> dtos = new ArrayList<>();
        while (resultSet.next()) {

            dtos.add(new AppointmentDto(resultSet.getString(1), resultSet.getDouble(2), resultSet.getString(3), resultSet.getDate(4).toLocalDate(), resultSet.getTime(5).toLocalTime(), resultSet.getString(6)));
            System.out.println();
        }
        System.out.println(dtos.toString());
        return dtos;
    }

    public ClientDto getCustomerInfo(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer where cId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        ClientDto dto = null;
        if (resultSet.next()) {
            String cId = resultSet.getString(1);
            String cust_name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String address = resultSet.getString(4);
            String contact = resultSet.getString(5);

            dto = new ClientDto(cId, cust_name, email, address, contact);
        }
        return dto;
    }

   public boolean updateAppointment(String id) throws SQLException, ClassNotFoundException {
       Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE appointment SET aid=? fee=?,status=?,app_date=?,app_time=? WHERE cId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate() > 0;
    }

    public boolean deleteAppointment(String aId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM appointment WHERE aId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, aId);
        return pstm.executeUpdate() > 0;

    }
    public boolean deleteClientId(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM appointment WHERE cid=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;
    }

    public int countAppointment() throws SQLException, ClassNotFoundException {
        int totalAppointments = 0;

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(aid) AS total_appointment_count FROM appointment");


        try (ResultSet resultSet = pstm.executeQuery()) {
            if (resultSet.next()) {
                totalAppointments = resultSet.getInt("total_appointment_count");
            }
        }


        return totalAppointments;
    }

    public double sumfee() throws SQLException, ClassNotFoundException {
        double sum = 0.00;
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT SUM(fee) AS total_fee FROM appointment";
        PreparedStatement pstm = connection.prepareStatement(sql);
        try (ResultSet resultSet = pstm.executeQuery()) {
            if (resultSet.next()) {
                sum = resultSet.getInt("total_fee");
            }
        }


        return sum;
    }

    public int countfullypaid() throws SQLException, ClassNotFoundException {
        String status = " paid";
        int count = 0;
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) AS FullyPaidCount FROM appointment WHERE status = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, status);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getInt("FullyPaidCount");
            }
        }
        return count;
    }

    public int countnotfullypaid() throws SQLException, ClassNotFoundException {
        String status = "not fully paid";
        int count = 0;
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) AS NotFullyPaidCount FROM appointment WHERE status = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, status);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getInt("NotFullyPaidCount");
            }
        }
        return count;
    }

    public AppointmentDto showAllAppointments(String serchId) throws SQLException, ClassNotFoundException {

        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM appointment WHERE aId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,serchId);
        ResultSet resultSet= pstm.executeQuery();
        AppointmentDto dto=null;
        if(resultSet.next()) {
            String aid = resultSet.getString(1);
            double fee= resultSet.getDouble(2);

            String status = resultSet.getString(3);
            LocalDate date = resultSet.getDate(4).toLocalDate();
            LocalTime time = resultSet.getTime(5).toLocalTime();
            String cId = resultSet.getString(6);

            dto = new AppointmentDto(aid, fee, status, date, time, cId);
        }
        return dto;


    }

    public boolean updateAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE appointment SET fee=?,status=?,app_date=?,app_time=?,cid=? WHERE aid=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

       // pstm.setString(1, dto.getaId());
        //pstm.setString(1, dto.getStatus());
        pstm.setDouble(1, dto.getFee());
        pstm.setString(2, dto.getStatus());
        pstm.setDate(3, Date.valueOf(dto.getDate()));
        pstm.setTime(4, Time.valueOf(dto.getTime()));
        pstm.setString(5, dto.getcId());
        pstm.setString(6, dto.getaId());
     boolean   isUpdate = pstm.executeUpdate() > 0;
     return isUpdate;
    }
}

