package lk.ijse.finalproject.bo.custom;

import lk.ijse.finalproject.bo.SuperBo;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.ClientDto;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentBo  extends SuperBo {
    String generateNextAppId() throws SQLException, ClassNotFoundException;

    boolean makeAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException;
    AppointmentDto searchId(String aId) throws SQLException, ClassNotFoundException;
    List<AppointmentDto> getAllAppointment() throws SQLException, ClassNotFoundException;
    // ClientDto getCustomerInfo(String id) throws SQLException, ClassNotFoundException;
    boolean updateAppointment(String id) throws SQLException, ClassNotFoundException;
    boolean deleteAppointment(String aId) throws SQLException, ClassNotFoundException;
    // boolean deleteClientId(String id) throws SQLException, ClassNotFoundException;
    double sumfee() throws SQLException, ClassNotFoundException;
    int countfullypaid() throws SQLException, ClassNotFoundException;
    int countnotfullypaid() throws SQLException, ClassNotFoundException;
    AppointmentDto showAllAppointments(String serchId) throws SQLException, ClassNotFoundException;
    boolean updateAppointment(AppointmentDto dto) throws SQLException, ClassNotFoundException;
   // ClientDto getCustomerInfo(String id) throws SQLException, ClassNotFoundException;
    int countAppointment() throws SQLException, ClassNotFoundException;
}
