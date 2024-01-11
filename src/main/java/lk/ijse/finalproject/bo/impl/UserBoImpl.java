package lk.ijse.finalproject.bo.impl;

import lk.ijse.finalproject.bo.custom.UserBo;
import lk.ijse.finalproject.dao.DAOFactory;
import lk.ijse.finalproject.dao.custom.AppointmentDao;
import lk.ijse.finalproject.dao.custom.UserDao;
import lk.ijse.finalproject.dao.impl.UserDaoImpl;
import lk.ijse.finalproject.dto.LoginDto;
import lk.ijse.finalproject.dto.RegisterDto;
import lk.ijse.finalproject.dto.ServiceDto;
import lk.ijse.finalproject.entity.Service;
import lk.ijse.finalproject.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    UserDao dao=(UserDao) DAOFactory.getDaoFactory().getDaoTypes(DAOFactory.DaoTypes.USER);
    @Override
    public boolean userLogin(LoginDto dto) throws SQLException, ClassNotFoundException {
        return dao.userLogin(new User(dto.getUserName(),dto.getPassword()));
    }

    @Override
    public String generateUserId() throws SQLException, ClassNotFoundException {
        return dao.generateUserId();
    }

    @Override
    public boolean registerUser(RegisterDto dto) throws SQLException, ClassNotFoundException {
        return dao.registerUser(new User(dto.getUid(),dto.getName(),dto.getUserName(),dto.getEmail(),dto.getPassword()));
    }

    @Override
    public List<RegisterDto> getAllusers() throws SQLException, ClassNotFoundException {
        ArrayList<User> users = (ArrayList<User>) dao.getAllusers();
        ArrayList<RegisterDto> dto = new ArrayList<>();
        for(User u:users) {
            dto.add(new RegisterDto(u.getUid(),u.getName(),u.getUserName(),u.getEmail(),u.getPassword()));
        }
        return dto;
    }

    @Override
    public boolean matchmails() {
        return dao.matchmails();
    }

    @Override
    public RegisterDto searchPassword(RegisterDto dto) {

      return null;
    }
}
