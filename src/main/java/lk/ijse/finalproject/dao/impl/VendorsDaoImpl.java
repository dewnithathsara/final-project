package lk.ijse.finalproject.dao.impl;

import lk.ijse.finalproject.dao.SqlUtil;
import lk.ijse.finalproject.dao.custom.VendorsDao;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.VendorDto;
import lk.ijse.finalproject.entity.Vendors;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendorsDaoImpl implements VendorsDao {
    @Override
    public Vendors search(String id) throws SQLException, ClassNotFoundException {
      ResultSet resultSet  =SqlUtil.testQuery("select * from vendors where id=?",id);


        if (resultSet.next()){

            return new Vendors(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );

        }

        return  null;




    }
    @Override
    public String generateNextVendorId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =  SqlUtil.testQuery("SELECT id FROM vendors ORDER BY id DESC LIMIT 1  ");
        if (resultSet.next()) {
            String id= resultSet.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("V00", "")) + 1;
            return String.format("V00%3d", newCustomerId);
        } else {
            return "V001";
        }
    }

    @Override
    public boolean saveVendor(Vendors dto) throws SQLException, ClassNotFoundException {
        boolean isSaved =SqlUtil.testQuery("INSERT INTO vendors(id,uid,category,name,email,contactInfo) VALUES(?,?,?,?,?,?)",dto.getId(),dto.getUid(),dto.getCategory(),dto.getName(),dto.getEmail(),dto.getContact());
        return isSaved;
    }
    @Override
    public List<Vendors> getAllVendors() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SqlUtil.testQuery( "SELECT * FROM vendors");
        ArrayList<Vendors> dtoList = new ArrayList<>();
        while (resultSet.next()) {
            dtoList.add(
                    new Vendors(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)


                    )
            );
        }

        return dtoList;
    }
    @Override
    public boolean deleteVendor(String id) throws SQLException, ClassNotFoundException {

        return SqlUtil.testQuery("DELETE FROM vendors WHERE id=?",id);
    }
    @Override
    public boolean updateVendor(Vendors dto) throws SQLException, ClassNotFoundException {


        return SqlUtil.testQuery("UPDATE vendors SET uid=?,category=?,name=?,email=?,contactInfo=? WHERE id=?",dto.getId(),dto.getUid(),dto.getCategory(),dto.getName(),dto.getEmail(),dto.getContact());
    }
    @Override
    public int countVendors() throws SQLException, ClassNotFoundException {
        int totalVendors = 0;
        ResultSet resultSet=SqlUtil.testQuery("SELECT COUNT(id) AS total_vendor_count FROM vendors");

            if (resultSet.next()) {
                totalVendors = resultSet.getInt("total_vendor_count");
            }
        return totalVendors;
    }
    @Override
    public List<String> getAllVendorsByCatgory(String category) throws SQLException, ClassNotFoundException {
        List<String> vendorNames = new ArrayList<>();
        ResultSet resultSet = SqlUtil.testQuery("SELECT name FROM vendors WHERE category=?",category);
        while (resultSet.next()) {
            String vendorName = resultSet.getString("name");
            vendorNames.add(vendorName);
        }
        return vendorNames;
    }
    @Override
    public int countphotographers(String category) throws SQLException, ClassNotFoundException {
        int totalPhotographers = 0;
        ResultSet resultSet = SqlUtil.testQuery("SELECT COUNT(category) AS total_photographers FROM vendors WHERE category=? ",category);
            if (resultSet.next()) {
                totalPhotographers = resultSet.getInt("total_photographers");
            }
        return totalPhotographers;
    }
    @Override
    public int countflowerists(String category) throws SQLException, ClassNotFoundException {
        int totalFlowerists = 0;
        ResultSet resultSet = SqlUtil.testQuery("SELECT COUNT(category) AS total_flowerists FROM vendors WHERE category=?",category);
            if (resultSet.next()) {
                totalFlowerists = resultSet.getInt("total_flowerists");
            }
        return totalFlowerists;
    }
    @Override
    public int countCategories() throws SQLException, ClassNotFoundException {
        int total= 0;
        ResultSet resultSet = SqlUtil.testQuery("SELECT COUNT(category) AS total_categories FROM vendors  ") ;
            if (resultSet.next()) {
                total= resultSet.getInt("total_categories");
            }
        return total;
    }
}




