package lk.ijse.finalproject.dao;

import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.RegisterDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterModel {

    public String generateUserId() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT uId FROM users ORDER BY uId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitUserId(resultSet.getString(1));
        }
        System.out.println("*********");
        return splitUserId(null);
    }


    private String splitUserId(String currentUserId) {
        if (currentUserId != null) {
            String[] split = currentUserId.split("E0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            System.out.println("=====");
            return "E00" + id;
        } else {
            return "E001";
        }
    }

    public boolean registerUser(RegisterDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO users(uId,name,email,username,password) VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getUid());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getEmail());
        pstm.setString(4, dto.getUserName());
        pstm.setString(5, dto.getPassword());
        // pstm.setString(6,dto.getConfirmPassword());

        boolean isRegistered = pstm.executeUpdate() > 0;
        System.out.println(isRegistered);
        return isRegistered;
    }

    public List<RegisterDto> getAllusers() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM users";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<RegisterDto> dtoList = new ArrayList<>();
        while (resultSet.next()) {
            dtoList.add(
                    new RegisterDto(
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

    public boolean matchmails() {
        return true;
    }

    public RegisterDto searchPassword(RegisterDto dto) {
        return dto;
    }
}
