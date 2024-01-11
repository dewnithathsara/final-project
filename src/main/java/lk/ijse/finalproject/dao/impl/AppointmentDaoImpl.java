package lk.ijse.finalproject.dao.impl;

import lk.ijse.finalproject.dao.SqlUtil;
import lk.ijse.finalproject.dao.custom.AppointmentDao;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.entity.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {
    @Override
    public String generateNextAppId() throws SQLException, ClassNotFoundException {
        System.out.println("hi me");
        ResultSet resultSet =  SqlUtil.testQuery("SELECT aid FROM appointment ORDER BY aid DESC LIMIT 1  ");
        System.out.println("oh my my");
        if (resultSet.next()) {
            System.out.println("kiss me");
            String id= resultSet.getString("aid");
            System.out.println("kiss me");

            int newCustomerId = Integer.parseInt(id.replace("A00", "")) + 1;
            System.out.println("pick me");
            return String.format("A00%3d", newCustomerId);

        } else {
            System.out.println("choose me");
            return "A001";
        }
    }
    @Override
    public boolean makeAppointment(Appointment entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("INSERT INTO appointment(aid,fee,status,app_date,app_time,cid) VALUES(?,?,?,?,?,?)",entity.getAid(),entity.getFee(),entity.getStatus(),entity.getApp_date(),entity.getApp_time(),entity.getCid());
    }
    @Override
    public Appointment searchId(String aId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.testQuery("SELECT * FROM appointment WHERE aid=?",aId);
        Appointment entity = null;
        if (resultSet.next()) {
            String aid = resultSet.getString(1);
            double fee = resultSet.getDouble(2);
            String status = resultSet.getString(3);
            LocalDate date = resultSet.getDate(4).toLocalDate();
            LocalTime time = resultSet.getTime(5).toLocalTime();
            String cId = resultSet.getString(6);
            entity = new Appointment(aid,fee,status,time,date,cId);
        }
        return entity;
    }
    @Override
    public List<Appointment> getAllAppointment() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SqlUtil.testQuery("SELECT * FROM appointment");
        ArrayList<Appointment> getAll = new ArrayList<>();
        while (resultSet.next()) {
            System.out.println("her private life");
        getAll.add(new Appointment(resultSet.getString(1), resultSet.getDouble(2), resultSet.getString(3),resultSet.getTime(4).toLocalTime() ,resultSet.getDate(5).toLocalDate(), resultSet.getString(6)));

            System.out.println("ryan gold");
          System.out.println();
        }
        System.out.println("ryan gold");
        System.out.println(getAll.toString());
        return getAll;
    }

    @Override
   public boolean updateAppointment(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("UPDATE appointment SET aid=? fee=?,status=?,app_date=?,app_time=? WHERE cId=?",id);
    }
    @Override
    public boolean deleteAppointment(String aId) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("DELETE FROM appointment WHERE aId=?",aId);
    }
    public boolean deleteClientId(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("DELETE FROM appointment WHERE cid=?",id);
    }
    @Override
    public int countAppointment() throws SQLException, ClassNotFoundException {
        int totalAppointments = 0;
        ResultSet resultSet =SqlUtil.testQuery("SELECT COUNT(aid) AS total_appointment_count FROM appointment");
            if (resultSet.next()) {
                totalAppointments = resultSet.getInt("total_appointment_count");
            }
            return totalAppointments;
    }
    @Override
    public double sumfee() throws SQLException, ClassNotFoundException {
        double sum = 0.00;
        ResultSet resultSet = SqlUtil.testQuery("SELECT SUM(fee) AS total_fee FROM appointment");
            if (resultSet.next()) {
                sum = resultSet.getInt("total_fee");
            }
        return sum;
    }
    @Override
    public int countfullypaid() throws SQLException, ClassNotFoundException {
        String status = " paid";
        int count = 0;
       ResultSet resultSet = SqlUtil.testQuery( "SELECT COUNT(*) AS FullyPaidCount FROM appointment WHERE status = ?",status);
            if (resultSet.next()) {
                count = resultSet.getInt("FullyPaidCount");
            }
        return count;
    }
    @Override
    public int countnotfullypaid() throws SQLException, ClassNotFoundException {
        String status = "not fully paid";
        int count = 0;
        ResultSet resultSet = SqlUtil.testQuery("SELECT COUNT(*) AS NotFullyPaidCount FROM appointment WHERE status = ?",status);
            if (resultSet.next()) {
                count = resultSet.getInt("NotFullyPaidCount");
            }
        return count;
    }
    @Override
    public Appointment showAllAppointments(String serchId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("SELECT * FROM appointment WHERE aId=?",serchId);
        Appointment entity=null;
        if(resultSet.next()) {
            String aid = resultSet.getString(1);
            double fee= resultSet.getDouble(2);
            String status = resultSet.getString(3);
            LocalDate date = resultSet.getDate(4).toLocalDate();
            LocalTime time = resultSet.getTime(5).toLocalTime();
            String cId = resultSet.getString(6);
            entity = new Appointment(aid, fee,status, time, date,cId);
        }
        return entity;
    }
    @Override
    public boolean updateAppointment(Appointment dto) throws SQLException, ClassNotFoundException {
     return SqlUtil.testQuery("UPDATE appointment SET fee=?,status=?,app_date=?,app_time=?,cid=? WHERE aid=?",dto.getFee(),dto.getStatus(),dto.getApp_date(),dto.getApp_time(),dto.getCid(),dto.getAid());
    }
}

