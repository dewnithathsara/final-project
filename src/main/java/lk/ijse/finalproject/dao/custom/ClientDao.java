package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao extends CrudDao<Customer> {

    Customer searchId(String id) throws SQLException, ClassNotFoundException;
    Customer search(String id) throws SQLException, ClassNotFoundException;
}
