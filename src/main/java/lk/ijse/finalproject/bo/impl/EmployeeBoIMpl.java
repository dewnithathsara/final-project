package lk.ijse.finalproject.bo.impl;

import lk.ijse.finalproject.bo.custom.EmployeeBo;
import lk.ijse.finalproject.dao.custom.EmployeeDao;
import lk.ijse.finalproject.dao.impl.EmployeeDaoImpl;
import lk.ijse.finalproject.dao.impl.EventRoleDaoImpl;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.EmployeeDto;
import lk.ijse.finalproject.entity.Appointment;
import lk.ijse.finalproject.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBoIMpl implements EmployeeBo {
    public EventRoleDaoImpl eventRoleDaoImpl =new EventRoleDaoImpl();
    private EmployeeDao dao =new EmployeeDaoImpl();
    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Employee(dto.getEmpId(), dto.getName(), dto.getEmail(),dto.getContact(),dto.getType()));
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return dao.update(new Employee(dto.getEmpId(), dto.getName(), dto.getEmail(),dto.getContact(),dto.getType()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = (ArrayList<Employee>) dao.getAll();
        ArrayList<EmployeeDto> dto = new ArrayList<>();
        for (Employee c : employees) {
            dto.add(new EmployeeDto(c.getEmpId(), c.getName(), c.getEmail(), c.getContact(), c.getType()));

        }
        return dto;
    }
    @Override
    public int countEmployee() throws SQLException, ClassNotFoundException {
        return dao.countEmployee();
    }

    @Override
    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        Employee entity=dao.search(id);
        return new EmployeeDto(entity.getEmpId(), entity.getName(), entity.getEmail(), entity.getContact(), entity.getType());
    }
}
