package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.EmployeeDto;
import lk.ijse.finalproject.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao extends CrudDao<Employee> {

    int countEmployee() throws SQLException, ClassNotFoundException;
    Employee search(String id) throws SQLException, ClassNotFoundException;
}
