package lk.ijse.finalproject.bo.impl;

import lk.ijse.finalproject.bo.custom.AppointmentBo;
import lk.ijse.finalproject.dao.DAOFactory;
import lk.ijse.finalproject.dao.custom.AppointmentDao;
import lk.ijse.finalproject.dao.custom.ClientDao;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.dto.CollabaratingDto;
import lk.ijse.finalproject.entity.Appointment;
import lk.ijse.finalproject.entity.Customer;
import lk.ijse.finalproject.entity.VendorCollabaration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AppointmentBoImpl implements AppointmentBo {
    AppointmentDao dao=(AppointmentDao) DAOFactory.getDaoFactory().getDaoTypes(DAOFactory.DaoTypes.APPOINTMENT);
    @Override
    public String generateNextAppId() throws SQLException, ClassNotFoundException {
        return dao.generateNextAppId();
    }

    @Override
    public boolean makeAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        return dao.makeAppointment(new Appointment(dto.getaId(),dto.getFee(),dto.getStatus(),dto.getTime(),dto.getDate(),dto.getcId()));
    }

    @Override
    public AppointmentDto searchId(String aId) throws SQLException, ClassNotFoundException {
        Appointment dto= dao.searchId(aId);
        return  new AppointmentDto(dto.getAid(),dto.getFee(),dto.getStatus(),dto.getApp_date(),dto.getApp_time(), dto.getCid());
    }
  @Override
    public List<AppointmentDto> getAllAppointment() throws SQLException, ClassNotFoundException {
        ArrayList<Appointment> appointments= (ArrayList<Appointment>) dao.getAllAppointment();
        ArrayList<AppointmentDto> dto=new ArrayList<>();
        for(Appointment c:appointments) {
            dto.add(new AppointmentDto(c.getAid(),c.getFee(),c.getStatus(),c.getApp_date(),c.getApp_time(), c.getCid()));
        }
        return  dto;
    }

    @Override
    public boolean updateAppointment(String id) throws SQLException, ClassNotFoundException {
        return dao.updateAppointment(id);
    }

    @Override
    public boolean deleteAppointment(String aId) throws SQLException, ClassNotFoundException {
        return dao.deleteAppointment(aId);
    }

    @Override
    public double sumfee() throws SQLException, ClassNotFoundException {
        return dao.sumfee();
    }

    @Override
    public int countfullypaid() throws SQLException, ClassNotFoundException {
        return dao.countfullypaid();
    }

    @Override
    public int countnotfullypaid() throws SQLException, ClassNotFoundException {
        return dao.countnotfullypaid();
    }

    @Override
    public AppointmentDto showAllAppointments(String serchId) throws SQLException, ClassNotFoundException {
        Appointment entity=dao.showAllAppointments(serchId);
        return new AppointmentDto(entity.getAid(),entity.getFee(),entity.getStatus(),entity.getApp_date(),entity.getApp_time(),entity.getCid());
        }

    @Override
    public boolean updateAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException {
        return dao.updateAppointment(new Appointment(dto.getaId(),dto.getFee(),dto.getStatus(),dto.getTime(),dto.getDate(),dto.getcId()));
    }



    @Override
    public int countAppointment() throws SQLException, ClassNotFoundException {
        return dao.countAppointment();
    }
}
