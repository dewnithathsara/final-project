package lk.ijse.finalproject.dao.impl;

import lk.ijse.finalproject.dao.SqlUtil;
import lk.ijse.finalproject.dao.custom.UserDao;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.LoginDto;
import lk.ijse.finalproject.dto.RegisterDto;
import lk.ijse.finalproject.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean userLogin(User dto) throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SqlUtil.testQuery("SELECT  * FROM users WHERE username=? AND password=?",dto.getUserName(),dto.getPassword());
            return resultSet.next();
    }
    @Override
    public String generateId() throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SqlUtil.testQuery("SELECT uId FROM users ORDER BY uId DESC LIMIT 1");
        if (resultSet.next()) {
            String id= resultSet.getString("uId");
            int newCustomerId = Integer.parseInt(id.replace("U00", "")) + 1;
            return String.format("U00%3d", newCustomerId);
        } else {
            return "U001";
        }
    }

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("INSERT INTO users(uId,name,email,username,password) VALUES(?,?,?,?,?)",dto.getUid(),dto.getName(),dto.getEmail(),dto.getUserName(),dto.getPassword());
    }

    @Override
    public boolean update(User object) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.testQuery("SELECT * FROM users");
        ArrayList<User> dtoList = new ArrayList<>();
        while (resultSet.next()) {
            dtoList.add(
                    new User(
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
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean matchmails() {
        return true;
    }
    @Override
    public User searchPassword(User dto) {
        return dto;
    }


}
