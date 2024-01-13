package lk.ijse.finalproject.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDao<T> extends SuperDao{
    List<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T object) throws SQLException, ClassNotFoundException;
    boolean update(T object) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String generateId() throws SQLException, ClassNotFoundException;
}
