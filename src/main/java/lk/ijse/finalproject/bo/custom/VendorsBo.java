package lk.ijse.finalproject.bo.custom;

import lk.ijse.finalproject.bo.SuperBo;
import lk.ijse.finalproject.dto.VendorDto;

import java.sql.SQLException;
import java.util.List;

public interface VendorsBo extends SuperBo {
    VendorDto search(String id) throws SQLException, ClassNotFoundException;
    String generateNextVendorId() throws SQLException, ClassNotFoundException;
    boolean saveVendor(VendorDto dto) throws SQLException, ClassNotFoundException;
    List<VendorDto> getAllVendors() throws SQLException, ClassNotFoundException;
    boolean deleteVendor(String id) throws SQLException, ClassNotFoundException;
    boolean updateVendor(VendorDto dto) throws SQLException, ClassNotFoundException;
    int countVendors() throws SQLException, ClassNotFoundException;
    List<String> getAllVendorsByCatgory(String category) throws SQLException, ClassNotFoundException;
    int countphotographers(String category) throws SQLException, ClassNotFoundException;
    int countflowerists(String category) throws SQLException, ClassNotFoundException;
    int countCategories() throws SQLException, ClassNotFoundException;
}
