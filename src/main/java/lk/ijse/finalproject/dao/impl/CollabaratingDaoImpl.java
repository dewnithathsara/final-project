package lk.ijse.finalproject.dao.impl;

import lk.ijse.finalproject.dao.SqlUtil;
import lk.ijse.finalproject.dao.custom.CollabaratingDao;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.CollabaratingDto;
import lk.ijse.finalproject.entity.VendorCollabaration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CollabaratingDaoImpl implements CollabaratingDao {
    @Override
    public List<VendorCollabaration> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(VendorCollabaration entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("INSERT INTO vendor_collaborating(eid,Sid,Vid,description,time,date,price) VALUES(?,?,?,?,?,?,?)",entity.geteId(),entity.getsId(),entity.getvId(),entity.getDesc(),entity.getTime(),entity.getDate(),entity.getPrice());
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("DELETE FROM vendor_collaborating WHERE eid=?",id);
    }
    @Override
    public boolean update(VendorCollabaration entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("UPDATE vendor_collaborating SET Sid=?,Vid=?,description=?,time=?,date=?,price=? , eid=?",entity.getsId(),entity.getvId(),entity.getDesc(),entity.getTime(),entity.getDate(),entity.getPrice(),entity.geteId());
    }
    @Override
    public List<VendorCollabaration> getAllCollabarting(String eid) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("SELECT * FROM vendor_collaborating WHERE eid=?",eid);
        ArrayList<VendorCollabaration> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new VendorCollabaration(
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
    @Override
    public VendorCollabaration search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("select * from vendor_collaborating WHERE eid=?",id);
        if (resultSet.next()){
            return new VendorCollabaration(
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
    public String generateId(){
        return null;
    }
}
