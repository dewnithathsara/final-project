package lk.ijse.finalproject.dao.custom;

import lk.ijse.finalproject.dao.CrudDao;
import lk.ijse.finalproject.dto.LoginDto;
import lk.ijse.finalproject.dto.RegisterDto;
import lk.ijse.finalproject.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends CrudDao <User>{
    boolean userLogin(User dto) throws SQLException, ClassNotFoundException;

    boolean matchmails();
    User searchPassword(User dto);
}
