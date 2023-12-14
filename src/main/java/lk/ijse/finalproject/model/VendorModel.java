package lk.ijse.finalproject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.dto.ServiceDto;
import lk.ijse.finalproject.dto.VendorDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendorModel {
    public static VendorDto search(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("select * from vendors where id=?");
        pstm.setString(1,id);
        ResultSet resultSet= pstm.executeQuery();

        if (resultSet.next()){

            return new VendorDto(
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

    public String generateNextVendorId() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT id FROM vendors ORDER BY id DESC LIMIT 1  ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitVendorId(resultSet.getString(1));
        }
        return splitVendorId(null);
    }

    private String splitVendorId(String currentVendorId) {
        if (currentVendorId != null) {
            String[] split = currentVendorId.split("V0");
            int id = Integer.parseInt(split[1]);
            id++;
            return "V00" + id;
        } else {
            return "V001";
        }

    }

    public boolean saveVendor(VendorDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO vendors(id,uid,category,name,email,contactInfo) VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getUid());
        pstm.setString(3, dto.getCategory());
        pstm.setString(4, dto.getName());
        pstm.setString(5, dto.getEmail());
        pstm.setString(6, dto.getContact());
        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }

    public List<VendorDto> getAllVendors() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM vendors";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<VendorDto> dtoList = new ArrayList<>();
        while (resultSet.next()) {
            dtoList.add(
                    new VendorDto(
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

    public boolean deleteVendor(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM vendors WHERE id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;
    }

    public boolean updateVendor(VendorDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE vendors SET uid=?,category=?,name=?,email=?,contactInfo=? WHERE id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getUid());
        pstm.setString(2, dto.getCategory());
        pstm.setString(3, dto.getName());
        pstm.setString(4, dto.getEmail());
        pstm.setString(5, dto.getContact());
        pstm.setString(6, dto.getId());

        return pstm.executeUpdate() > 0;
    }

    public int countVendors() throws SQLException, ClassNotFoundException {
        int totalVendors = 0;

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(id) AS total_vendor_count FROM vendors");


        try (ResultSet resultSet = pstm.executeQuery()) {
            if (resultSet.next()) {
                totalVendors = resultSet.getInt("total_vendor_count");
            }
        }


        return totalVendors;
    }

    public List<String> getAllVendorsByCatgory(String category) throws SQLException, ClassNotFoundException {
        List<String> vendorNames = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT name FROM vendors WHERE category=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, category);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String vendorName = resultSet.getString("name");


            vendorNames.add(vendorName);


        }
        return vendorNames;

    }

    public int countphotographers(String category) throws SQLException, ClassNotFoundException {
        int totalPhotographers = 0;

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(category) AS total_photographers FROM vendors WHERE category=? ");
        pstm.setString(1, category);
        try (ResultSet resultSet = pstm.executeQuery()) {
            if (resultSet.next()) {
                totalPhotographers = resultSet.getInt("total_photographers");
            }

        }
        return totalPhotographers;
    }

    public int countflowerists(String category) throws SQLException, ClassNotFoundException {
        int totalFlowerists = 0;

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(category) AS total_flowerists FROM vendors WHERE category=?");
        pstm.setString(1, category);
        try (ResultSet resultSet = pstm.executeQuery()) {
            if (resultSet.next()) {
                totalFlowerists = resultSet.getInt("total_flowerists");
            }

        }
        return totalFlowerists;
    }

    public int countCategories() throws SQLException, ClassNotFoundException {
        int total= 0;

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(category) AS total_categories FROM vendors  ");
        try (ResultSet resultSet = pstm.executeQuery()) {
            if (resultSet.next()) {
                total= resultSet.getInt("total_categories");
            }

        }
        return total;
    }
}




