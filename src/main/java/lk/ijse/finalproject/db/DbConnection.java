package lk.ijse.finalproject.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private  static Connection connection;
    private static DbConnection dbConnection;


    private DbConnection() throws SQLException, ClassNotFoundException {
       // Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/enchanted","root","Ijse@1234");
    }
    public static DbConnection getInstance() throws SQLException, ClassNotFoundException {
        return(null==dbConnection)? dbConnection=new DbConnection():dbConnection;
    }



    public Connection getConnection(){
        return connection;
    }
}

