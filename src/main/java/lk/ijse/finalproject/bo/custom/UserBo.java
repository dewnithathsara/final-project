package lk.ijse.finalproject.bo.custom;

import lk.ijse.finalproject.bo.SuperBo;
import lk.ijse.finalproject.dto.LoginDto;
import lk.ijse.finalproject.dto.RegisterDto;

import java.sql.SQLException;
import java.util.List;

public interface UserBo extends SuperBo {
    boolean userLogin(LoginDto dto) throws SQLException, ClassNotFoundException;
    String generateUserId() throws SQLException, ClassNotFoundException;
    boolean registerUser(RegisterDto dto) throws SQLException, ClassNotFoundException;
    List<RegisterDto> getAllusers() throws SQLException, ClassNotFoundException;
    boolean matchmails();
    RegisterDto searchPassword(RegisterDto dto);
}
