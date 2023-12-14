package lk.ijse.finalproject.model;

import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.CollabaratingDto;
import lk.ijse.finalproject.dto.EventRoleDto;
import lk.ijse.finalproject.dto.ServiceDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CollabaratingModel {
    public boolean saveCollabaring(CollabaratingDto dto) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql="INSERT INTO vendor_collaborating(eid,Sid,Vid,description,time,date,price) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,dto.geteId());
        pstm.setString(2,dto.getsId());
        pstm.setString(3,dto.getvId());
        pstm.setString(4,dto.getDesc());
        pstm.setTime(5, Time.valueOf((dto.getTime())));
        System.out.println("hhhhh");
        pstm.setDate(6, Date.valueOf(dto.getDate()));
        System.out.println("tttt");

        pstm.setDouble(7,dto.getPrice());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteCollabaration(String id) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM vendor_collaborating WHERE eid=?";
        PreparedStatement pstm= connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }

    public boolean updateCollobarating(CollabaratingDto dto) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql="UPDATE vendor_collaborating SET Sid=?,Vid=?,description=?,time=?,date=?,price=? , eid=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,dto.getsId());
        pstm.setString(2,dto.getvId());
        pstm.setString(3,dto.getDesc());
        pstm.setTime(4, Time.valueOf((dto.getTime())));
        pstm.setDate(5, Date.valueOf(dto.getDate()));
        pstm.setDouble(6,dto.getPrice());
        pstm.setString(7,dto.geteId());

        return pstm.executeUpdate() > 0;


    }

    public List<CollabaratingDto> getAllCollabarting(String eid) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM vendor_collaborating WHERE eid=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1, eid);

        ResultSet resultSet= pstm.executeQuery();
        ArrayList<CollabaratingDto> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new CollabaratingDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getTime(5).toLocalTime(),
                            resultSet.getDate(6).toLocalDate(),
                            resultSet.getDouble(7)

                    )
            );
        }

        return dtoList;

    }

    public CollabaratingDto search(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("select * from vendor_collaborating WHERE eid=?");
        pstm.setString(1,id);
        ResultSet resultSet= pstm.executeQuery();

        if (resultSet.next()){

            return new CollabaratingDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getTime(5).toLocalTime(),
                    resultSet.getDate(6).toLocalDate(),
                    resultSet.getDouble(7)
            );

        }

        return  null;

    }

}
