package lk.ijse.finalproject.dao.impl;

import lk.ijse.finalproject.dao.SqlUtil;
import lk.ijse.finalproject.dao.custom.ServiceDao;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.ServiceDto;
import lk.ijse.finalproject.entity.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl implements ServiceDao {
    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("SELECT sid FROM service ORDER BY sid LIMIT 1");
        if(resultSet.next()){
            String id= resultSet.getString("sid");
            int newCustomerId = Integer.parseInt(id.replace("S00", "")) + 1;
            return String.format("S00%3d", newCustomerId);
        } else {
            return "S001";
        }
    }

    @Override
    public boolean save(Service dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("INSERT INTO service(sid,package_name,description,price) VALUES(?,?,?,?)",dto.getSid(),dto.getPackageName(),dto.getPackageName(),dto.getPrice());
    }
    @Override
    public List<Service> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("SELECT * FROM service");
        ArrayList<Service> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new Service(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4)
                    )
            );
        }

        return dtoList;
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("DELETE FROM service WHERE sid=?",id);
    }
    @Override
    public boolean update(Service dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("UPDATE service SET package_name=?,description=?,price=? WHERE sid=?",dto.getPackageName(),dto.getDescription(),dto.getPrice(),dto.getSid());
    }
    @Override
    public List<Service> getservices() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("SELECT * FROM service");
        ArrayList<Service> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new Service(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4)
                    )
            );
        }
        return dtoList;
    }
    @Override
    public Service search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("select * from service where sId=?",id);
        if (resultSet.next()){
            return new Service(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return  null;
    }
}
