package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.CollabaratingDto;
import lk.ijse.finalproject.entity.VendorCollabaration;

import java.sql.SQLException;
import java.util.List;

public interface CollabaratingDao extends CrudDao<VendorCollabaration> {
    List<VendorCollabaration> getAllCollabarting(String eid) throws SQLException, ClassNotFoundException;
    VendorCollabaration search(String id) throws SQLException, ClassNotFoundException;

}
