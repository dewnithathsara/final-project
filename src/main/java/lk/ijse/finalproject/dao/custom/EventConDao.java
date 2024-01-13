package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.EventConDto;
import lk.ijse.finalproject.entity.Consultation;

import java.sql.SQLException;

public interface EventConDao extends CrudDao<Consultation> {

    double total() throws SQLException, ClassNotFoundException;
    Consultation getAllDetails(String id) throws SQLException, ClassNotFoundException;
    Consultation search(String id) throws SQLException, ClassNotFoundException;

}
