package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.EventConDto;
import lk.ijse.finalproject.entity.Consultation;

import java.sql.SQLException;

public interface EventConDao extends CrudDao {
    String generateNextCode() throws SQLException, ClassNotFoundException;

    boolean saveConsulting(Consultation entity) throws SQLException, ClassNotFoundException;
    boolean deleteEventCon(String id) throws SQLException, ClassNotFoundException;
    boolean updateConsulting(Consultation entity) throws SQLException, ClassNotFoundException;
    double total() throws SQLException, ClassNotFoundException;
    Consultation getAllDetails(String id) throws SQLException, ClassNotFoundException;
    Consultation search(String id) throws SQLException, ClassNotFoundException;

}
