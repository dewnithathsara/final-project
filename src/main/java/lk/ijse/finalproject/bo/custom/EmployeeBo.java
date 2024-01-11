package lk.ijse.finalproject.bo.custom;

import lk.ijse.finalproject.bo.SuperBo;
import lk.ijse.finalproject.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBo extends SuperBo {
    boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    List<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException;
    int countEmployee() throws SQLException, ClassNotFoundException;
    EmployeeDto search(String id) throws SQLException, ClassNotFoundException;
}
