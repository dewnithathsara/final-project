package lk.ijse.finalproject.dao.impl;

import lk.ijse.finalproject.dao.SqlUtil;
import lk.ijse.finalproject.dao.custom.EmployeeDao;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.EmployeeDto;
import lk.ijse.finalproject.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    public EventRoleDaoImpl eventRoleDaoImpl =new EventRoleDaoImpl();
    @Override
    public boolean saveEmployee(Employee dto) throws SQLException, ClassNotFoundException {
            return SqlUtil.testQuery( "INSERT INTO employee(empId,name,email,contact,type) VALUES(?,?,?,?,?)", dto.getEmpId(), dto.getName(),dto.getEmail(),dto.getContact(),dto.getType());
    }
    @Override
    public boolean updateEmployee(Employee dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("UPDATE employee SET name=?,email=?,contact=?,type=? WHERE empId=?",dto.getName(),dto.getEmail(),dto.getContact(),dto.getType(),dto.getEmpId());
    }
    //transaction part
    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        Connection connection=null;
        PreparedStatement pstm=null;
        boolean isDelete=false;
        String employeeId=id;
        //connection.setAutoCommit(false);
     try {
         connection = DbConnection.getInstance().getConnection();
         connection.setAutoCommit(false);
         String sql = "DELETE FROM employee where empId=?";

          pstm = connection.prepareStatement(sql);
         pstm.setString(1, id);
          isDelete=pstm.executeUpdate()>0;
         if(isDelete) {
             connection.commit();
             eventRoleDaoImpl.DeleteEventRole(employeeId);

         }
     }catch (RuntimeException e){
         throw new RuntimeException(e);
     }finally{
         connection.setAutoCommit(true);
     }
        return isDelete ;
    }
    @Override
    public List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("SELECT * FROM employee");
        ArrayList<Employee> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new Employee(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return dtoList;
    }
    @Override
    public int countEmployee() throws SQLException, ClassNotFoundException {
        int count=0;
      ResultSet resultSet = SqlUtil.testQuery("SELECT COUNT(empId) AS employee_count FROM employee");
            if (resultSet.next()) {
                count = resultSet.getInt("employee_count");
            }
        return count;
    }
    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("select * from employee where empId=?",id);
        if (resultSet.next()){
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return  null;
    }
}
