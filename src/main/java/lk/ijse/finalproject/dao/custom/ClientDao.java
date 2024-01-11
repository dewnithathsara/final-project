package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao extends CrudDao {
    boolean saveCustomer(Customer entity) throws SQLException, ClassNotFoundException;
    boolean updateClient(Customer entity) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    List<Customer> getAllCustomers() throws SQLException, ClassNotFoundException;
    Customer searchId(String id) throws SQLException, ClassNotFoundException;
    Customer search(String id) throws SQLException, ClassNotFoundException;
}
