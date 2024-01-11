package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.LoginDto;
import lk.ijse.finalproject.dto.RegisterDto;
import lk.ijse.finalproject.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends CrudDao {
    boolean userLogin(User dto) throws SQLException, ClassNotFoundException;
    String generateUserId() throws SQLException, ClassNotFoundException;
    boolean registerUser(User dto) throws SQLException, ClassNotFoundException;
    List<User> getAllusers() throws SQLException, ClassNotFoundException;
    boolean matchmails();
    User searchPassword(User dto);
}
