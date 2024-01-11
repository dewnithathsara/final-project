package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.entity.Appointment;
import lk.ijse.finalproject.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentDao extends CrudDao {
    String generateNextAppId() throws SQLException, ClassNotFoundException;

    boolean makeAppointment(Appointment entity) throws SQLException, ClassNotFoundException;
    Appointment searchId(String aId) throws SQLException, ClassNotFoundException;
    List<Appointment> getAllAppointment() throws SQLException, ClassNotFoundException;
   // ClientDto getCustomerInfo(String id) throws SQLException, ClassNotFoundException;
    boolean updateAppointment(String id) throws SQLException, ClassNotFoundException;
    boolean deleteAppointment(String aId) throws SQLException, ClassNotFoundException;
   // boolean deleteClientId(String id) throws SQLException, ClassNotFoundException;
   double sumfee() throws SQLException, ClassNotFoundException;
    int countfullypaid() throws SQLException, ClassNotFoundException;
    int countnotfullypaid() throws SQLException, ClassNotFoundException;
    Appointment showAllAppointments(String serchId) throws SQLException, ClassNotFoundException;
    boolean updateAppointment(Appointment entity) throws SQLException, ClassNotFoundException;

    int countAppointment() throws SQLException, ClassNotFoundException;
}
