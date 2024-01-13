package lk.ijse.finalproject.dao.impl;

import javafx.geometry.Pos;
import javafx.util.Duration;
import lk.ijse.finalproject.dao.SqlUtil;
import lk.ijse.finalproject.dao.custom.ClientDao;
import lk.ijse.finalproject.dao.impl.AppointmentDaoImpl;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.entity.Customer;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {


    public AppointmentDaoImpl appointmentModel=new AppointmentDaoImpl();
    public AppointmentDto appointmentDto=null;
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.testQuery("INSERT INTO customer(cId,cust_name,email,address,contact) VALUE (?,?,?,?,?)",entity.getcId(),entity.getCustName(),entity.getEmail(),entity.getAddress(),entity.getContact());
    }
    // transaction part
    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        Connection connection=null;
        boolean isUpdate=false;
       try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            String sql = "UPDATE customer SET cust_name=?,email=?,address=?,contact=? WHERE cId=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, entity.getCustName());
            pstm.setString(2, entity.getEmail());
            pstm.setString(3, entity.getAddress());
            pstm.setString(4, entity.getContact());
            pstm.setString(5, entity.getcId());

            isUpdate = pstm.executeUpdate() > 0;
            System.out.println("no one");
        if (isUpdate) {
            System.out.println("every one");

            appointmentModel.updateAppointment(appointmentDto.getcId());
            System.out.println("any one");
            Notifications.create().title("Reminder")
                    .text("The related appointment will also be updated.")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .graphic(null)
                    .show();

            connection.commit();

        }else{
            connection.rollback();
            System.out.println("no body");

        }
    }catch (Exception e){
        e.printStackTrace();
        System.out.println("any body");

    }finally {
        System.out.println("every one");

        connection.setAutoCommit(true);
    }
        return isUpdate;
}
//transaction part
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        boolean isDeleted = false;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            String sql = "DELETE FROM customer WHERE cId=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);
            isDeleted = pstm.executeUpdate() > 0;
            if (isDeleted) {
                appointmentModel.deleteClientId(id);
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return isDeleted;
    }
    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet= SqlUtil.testQuery("SELECT * FROM customer");
        ArrayList<Customer> entity=new ArrayList<>();
        while(resultSet.next()){
            entity.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
       return entity;
    }
    @Override
    public Customer searchId(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("SELECT * FROM customer WHERE cId=?",id);
        Customer entity=null;
        if(resultSet.next()) {
            String cId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String address = resultSet.getString(4);
            String contact = resultSet.getString(5);
            entity = new Customer(cId, name, email, address, contact);
        }
        return entity;
        }
    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.testQuery("select * from customer where cId=?",id);
        if (resultSet.next()){
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return  null;
    }
    @Override
    public String generateId(){
        return null;
    }
}
