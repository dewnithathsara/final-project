package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.ServiceDto;
import lk.ijse.finalproject.entity.Service;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDao extends CrudDao {
    String generateSid() throws SQLException, ClassNotFoundException;

    boolean saveService(Service dto) throws SQLException, ClassNotFoundException;
    List<Service> getAllServices() throws SQLException, ClassNotFoundException;
    boolean deleteService(String id) throws SQLException, ClassNotFoundException;
    boolean updateService(Service dto) throws SQLException, ClassNotFoundException;
    List<Service> getservices() throws SQLException, ClassNotFoundException;
    Service search(String id) throws SQLException, ClassNotFoundException;
}
