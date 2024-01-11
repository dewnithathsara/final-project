module lk.ijse.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires mysql.connector.j;
    requires jasperreports;
    requires org.controlsfx.controls;
    requires javax.mail.api;


    opens lk.ijse.finalproject to javafx.fxml;
    exports lk.ijse.finalproject;
    exports lk.ijse.finalproject.controller;
    exports lk.ijse.finalproject.dao;
    exports lk.ijse.finalproject.dto.tm ;
    exports lk.ijse.finalproject.dto ;

    opens lk.ijse.finalproject.controller to javafx.fxml;
    opens lk.ijse.finalproject.dto.tm to javafx.fxml;
    opens lk.ijse.finalproject.dao to javafx.fxml;
    exports lk.ijse.finalproject.dao.impl;
    opens lk.ijse.finalproject.dao.impl to javafx.fxml;

}