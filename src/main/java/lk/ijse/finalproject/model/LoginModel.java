package lk.ijse.finalproject.model;

import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.LoginDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginModel {
    public boolean userLogin(LoginDto dto) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        try{
        String sql="SELECT  * FROM users WHERE username=? AND password=?" ;
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1,dto.getUserName());

        pstm.setString(2,dto.getPassword());



        ResultSet resultSet=pstm.executeQuery();
        return resultSet.next();
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}






}
