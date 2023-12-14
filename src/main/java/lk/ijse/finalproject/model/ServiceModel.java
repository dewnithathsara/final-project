package lk.ijse.finalproject.model;

import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.dto.EmployeeDto;
import lk.ijse.finalproject.dto.ServiceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceModel {
    public String generateSid() throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql="SELECT sid FROM service ORDER BY sid LIMIT 1";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet resultSet= pstm.executeQuery();
        if(resultSet.next()){
            return splitSid(resultSet.getString(1));
        }
        return splitSid(null);
    }

    private String splitSid(String currentSid) {
        if (currentSid != null) {
            String[] split = currentSid.split("S0");
            int id = Integer.parseInt(split[1]);
            id++;
            return "S00" + id;
        } else {
            return "S001";
        }
    }

    public boolean saveService(ServiceDto dto) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="INSERT INTO service(sid,package_name,description,price) VALUES(?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,dto.getSid());
        pstm.setString(2,dto.getPackageName());
        pstm.setString(3,dto.getDescription());
        pstm.setString(4, String.valueOf(dto.getPrice()));
        return pstm.executeUpdate() > 0;
    }

    public List<ServiceDto> getAllServices() throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM service";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet resultSet= pstm.executeQuery();
        ArrayList<ServiceDto> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new ServiceDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4)


                    )
            );
        }

        return dtoList;
    }

    public boolean deleteService(String id) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM service WHERE sid=?";
        PreparedStatement pstm= connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }

    public boolean updateService(ServiceDto dto) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="UPDATE service SET package_name=?,description=?,price=? WHERE sid=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,dto.getPackageName());
        pstm.setString(2,dto.getDescription());
        pstm.setDouble(3, dto.getPrice());
        pstm.setString(4, dto.getSid());
        System.out.println(dto.getSid()+" "+dto.getPackageName()+" "+dto.getDescription()+" "+dto.getPrice());
        int executeUpdate = pstm.executeUpdate();
        System.out.println(executeUpdate);


        return executeUpdate>0;
    }

    public List<ServiceDto> getservices() throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM service";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet resultSet= pstm.executeQuery();
        ArrayList<ServiceDto> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new ServiceDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4)

                    )
            );
        }

        return dtoList;
    }

    public ServiceDto search(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("select * from service where sId=?");
        pstm.setString(1,id);
        ResultSet resultSet= pstm.executeQuery();

        if (resultSet.next()){

            return new ServiceDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );

        }

        return  null;

    }

}
