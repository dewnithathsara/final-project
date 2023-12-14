package lk.ijse.finalproject.model;

import javafx.geometry.Pos;
import javafx.util.Duration;
import lk.ijse.finalproject.db.DbConnection;
import lk.ijse.finalproject.dto.AppointmentDto;
import lk.ijse.finalproject.dto.ClientDto;
import lk.ijse.finalproject.dto.ServiceDto;
import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ClientModel {
        public AppointmentModel appointmentModel=new AppointmentModel();
        public AppointmentDto appointmentDto=null;




    public boolean saveCustomer(ClientDto dto) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql="INSERT INTO customer(cId,cust_name,email,address,contact) VALUE (?,?,?,?,?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1, dto.getcId());
        pstm.setString(2,dto.getCustName());
        pstm.setString(3,dto.getEmail());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getContact());

        boolean isSaved=pstm.executeUpdate()>0;



        return isSaved;
    }

    public boolean updateClient(ClientDto dto) throws SQLException, ClassNotFoundException {
        Connection connection=null;
        boolean isUpdate=false;
       try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            String sql = "UPDATE customer SET cust_name=?,email=?,address=?,contact=? WHERE cId=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getCustName());
            pstm.setString(2, dto.getEmail());
            pstm.setString(3, dto.getAddress());
            pstm.setString(4, dto.getContact());
            pstm.setString(5, dto.getcId());

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

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
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
    public List<ClientDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM customer";
        PreparedStatement pstm=connection.prepareStatement(sql);
        ResultSet resultSet= pstm.executeQuery();
        ArrayList<ClientDto> dtoList=new ArrayList<>();
        while(resultSet.next()){
            dtoList.add(
                    new ClientDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }

       return dtoList;
    }

    public ClientDto searchId(String id) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM customer WHERE cId=?";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet= pstm.executeQuery();
        ClientDto dto=null;
        if(resultSet.next()) {
            String cId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String address = resultSet.getString(4);
            String contact = resultSet.getString(5);
            dto = new ClientDto(cId, name, email, address, contact);
        }
        return dto;

        }

    public ClientDto search(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("select * from customer where cId=?");
        pstm.setString(1,id);
        ResultSet resultSet= pstm.executeQuery();

        if (resultSet.next()){

            return new ClientDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

        }

        return  null;


    }
}
