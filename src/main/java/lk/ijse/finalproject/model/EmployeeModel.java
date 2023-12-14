package lk.ijse.finalproject.model;

import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    public EventRoleModel eventRoleModel=new EventRoleModel();
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();

           String sql= "INSERT INTO employee(empId,name,email,contact,type) VALUES(?,?,?,?,?)";
            PreparedStatement pstm=connection.prepareStatement(sql);
            pstm.setString(1, dto.getEmpId());
            pstm.setString(2, dto.getName());
            pstm.setString(3, dto.getEmail());
            pstm.setString(4, dto.getContact());
            pstm.setString(5, dto.getType());
            boolean isSaved=pstm.executeUpdate()>0;
            return isSaved;

    }

    public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE employee SET name=?,email=?,contact=?,type=? WHERE empId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,dto.getName());
        pstm.setString(2, dto.getEmail());
        pstm.setString(3, dto.getContact());
        pstm.setString(4, dto.getType());
        pstm.setString(5,dto.getEmpId());
        boolean isUpdated=pstm.executeUpdate()>0;
        return isUpdated;
    }

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
             eventRoleModel.DeleteEventRole(employeeId);

         }
     }catch (RuntimeException e){
         throw new RuntimeException(e);
     }finally{
         connection.setAutoCommit(true);
     }
        return isDelete ;
    }

    public List<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM employee";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet resultSet= pstm.executeQuery();
        ArrayList<EmployeeDto> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new EmployeeDto(
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

    public int countEmployee() throws SQLException, ClassNotFoundException {
        int count=0;
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT COUNT(empId) AS employee_count FROM employee";
        PreparedStatement pstm= connection.prepareStatement(sql);
        try (ResultSet resultSet = pstm.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getInt("employee_count");
            }
        }


        return count;
    }

    public EmployeeDto search(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("select * from employee where empId=?");
        pstm.setString(1,id);
        ResultSet resultSet= pstm.executeQuery();

        if (resultSet.next()){

            return new EmployeeDto(
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
