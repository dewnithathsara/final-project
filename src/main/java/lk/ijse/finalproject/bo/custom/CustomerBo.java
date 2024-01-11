package lk.ijse.finalproject.bo.custom;

import lk.ijse.finalproject.bo.SuperBo;
import lk.ijse.finalproject.dto.ClientDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBo {
    boolean saveCustomer(ClientDto dto) throws SQLException, ClassNotFoundException;
    boolean updateClient(ClientDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    List<ClientDto> getAllCustomers() throws SQLException, ClassNotFoundException;
    ClientDto searchId(String id) throws SQLException, ClassNotFoundException;
    ClientDto search(String id) throws SQLException, ClassNotFoundException;
}
