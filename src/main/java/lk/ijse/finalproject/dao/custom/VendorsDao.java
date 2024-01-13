package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.VendorDto;
import lk.ijse.finalproject.entity.Vendors;

import java.sql.SQLException;
import java.util.List;

public interface VendorsDao extends CrudDao<Vendors> {
    Vendors search(String id) throws SQLException, ClassNotFoundException;

    int countVendors() throws SQLException, ClassNotFoundException;
    List<String> getAllVendorsByCatgory(String category) throws SQLException, ClassNotFoundException;
    int countphotographers(String category) throws SQLException, ClassNotFoundException;
    int countflowerists(String category) throws SQLException, ClassNotFoundException;
    int countCategories() throws SQLException, ClassNotFoundException;

}
