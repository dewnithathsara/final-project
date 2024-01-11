package lk.ijse.finalproject.dao.impl;

import lk.ijse.finalproject.dao.SqlUtil;
import lk.ijse.finalproject.dao.custom.EventConDao;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.EventConDto;
import lk.ijse.finalproject.entity.Consultation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventConDaoImpl implements EventConDao {
    @Override
    public String generateNextCode() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.testQuery("SELECT conId FROM consulting_fee ORDER BY conId LIMIT 1");
        if (resultSet.next()) {
            String id= resultSet.getString("conId");
            int newCustomerId = Integer.parseInt(id.replace("D00", "")) + 1;
            return String.format("D00%3d", newCustomerId);
        } else {
            return "D001";
        }
    }


    @Override
    public boolean saveConsulting(Consultation dto) throws SQLException, ClassNotFoundException {
        ;
        return SqlUtil.testQuery("INSERT INTO consulting_fee VALUES(?,?,?,?)",dto.getConId(),dto.getFee(),dto.getDescription(),dto.getcId());
    }
    @Override
    public boolean deleteEventCon(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("DELETE FROM consulting_fee WHERE conId=?",id);
    }
    @Override
    public boolean updateConsulting(Consultation dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("UPDATE consulting_fee SET fee=?,description=?,cid=?,conId=?",dto.getFee(),dto.getDescription(),dto.getcId(),dto.getConId());
    }
    @Override
    public double total() throws SQLException, ClassNotFoundException {
        double total_fee = 0.00;
        ResultSet resultSet = SqlUtil.testQuery("SELECT SUM(fee) AS total_fee FROM consulting_fee  ");
            if (resultSet.next()) {
                total_fee = resultSet.getDouble("total_fee");
            }
        return total_fee;
    }

    @Override
    public Consultation getAllDetails(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SqlUtil.testQuery("SELECT * FROM consulting_fee WHERE conId=?",id);
        System.out.println("love song");
        Consultation dto = null;
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
            dto = new Consultation(conId, fee, description, cid);
            System.out.println("megaverse");
        }
        System.out.println("i love you"+dto);
        return dto;

    }
    @Override
    public Consultation search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.testQuery("SELECT * FROM consulting_fee WHERE conId = ?",id);
        System.out.println("Event Model: id--> "+id);
        boolean next = resultSet.next();
        if (next){
            System.out.println("Event Model: if "+resultSet);
            System.out.println("kang young hwa");
            return new Consultation(
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
