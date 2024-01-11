package lk.ijse.finalproject.bo.custom;

import lk.ijse.finalproject.bo.SuperBo;
import lk.ijse.finalproject.dto.ServiceDto;

import java.sql.SQLException;
import java.util.List;

public interface ServiceBo extends SuperBo {
    String generateSid() throws SQLException, ClassNotFoundException;

    boolean saveService(ServiceDto dto) throws SQLException, ClassNotFoundException;
    List<ServiceDto> getAllServices() throws SQLException, ClassNotFoundException;
    boolean deleteService(String id) throws SQLException, ClassNotFoundException;
    boolean updateService(ServiceDto dto) throws SQLException, ClassNotFoundException;
    List<ServiceDto> getservices() throws SQLException, ClassNotFoundException;
    ServiceDto search(String id) throws SQLException, ClassNotFoundException;
}
