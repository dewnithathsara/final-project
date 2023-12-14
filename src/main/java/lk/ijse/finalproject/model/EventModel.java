package lk.ijse.finalproject.model;

import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.dto.EventConDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventModel {
    public String generateNextCode() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT conId FROM consulting_fee ORDER BY conId LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitConId(resultSet.getString(1));
        }
        return splitConId(null);
    }

    private String splitConId(String currentConId) {
        if (currentConId != null) {
            String[] split = currentConId.split("D0");
            int id = Integer.parseInt(split[1]);
            id++;
            return "D00" + id;
        } else {
            return "D001";
        }
    }


    public boolean saveConsulting(EventConDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO consulting_fee VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getConId());
        pstm.setDouble(2, dto.getFee());
        pstm.setString(3, dto.getDescription());
        pstm.setString(4, dto.getcId());

        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }

    public boolean deleteEventCon(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM consulting_fee WHERE conId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;
    }

    public boolean updateConsulting(EventConDto dto) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE consulting_fee SET fee=?,description=?,cid=?,conId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setDouble(1, dto.getFee());
        pstm.setString(2, dto.getDescription());
        pstm.setString(3, dto.getcId());
        pstm.setString(4, dto.getConId());
        //pstm.setString(5,dto.getType());
        return pstm.executeUpdate() > 0;
    }

    public double total() throws SQLException, ClassNotFoundException {
        double total_fee = 0.00;
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT SUM(fee) AS total_fee FROM consulting_fee  ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        try (ResultSet resultSet = pstm.executeQuery()) {
            if (resultSet.next()) {
                total_fee = resultSet.getDouble("total_fee");
            }
        }
        return total_fee;
    }


    public EventConDto getAllDetails(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        System.out.println("i love you");
        String sql = "SELECT * FROM consulting_fee WHERE conId=?";
        System.out.println("i hate you");
        PreparedStatement pstm = connection.prepareStatement(sql);
        System.out.println("iu");
        pstm.setString(1, id);
        System.out.println("youtiful");
        ResultSet resultSet = pstm.executeQuery();
        System.out.println("love song");
        EventConDto dto = null;
        System.out.println("no body knows");
        if (resultSet.next()) {
            String conId = resultSet.getString(1);
            double fee = Double.parseDouble((resultSet.getString(2)));
            System.out.println("Ren");
            String description = resultSet.getString(3);
            System.out.println("she would never know");
            String cid = resultSet.getString(4);
            System.out.println("be mine");

            System.out.println("arcade");
            dto = new EventConDto(conId, fee, description, cid);
            System.out.println("megaverse");
        }
        System.out.println("i love you"+dto);
        return dto;

    }

    public EventConDto search(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm =DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM consulting_fee WHERE conId = ?");
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();
        System.out.println("Event Model: id--> "+id);
        boolean next = resultSet.next();
        if (next){
            System.out.println("Event Model: if "+resultSet);
            System.out.println("kang young hwa");
            return new EventConDto(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }else{
            System.out.println("Event Model: else "+next);
        }
       return null;
    }
}
