module edu.ijse.theserenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires com.jfoenix;



    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens edu.ijse.theserenitymentalhealththerapycenter to org.hibernate.orm.core, jakarta.persistence, javafx.fxml,javafx.base;
    exports edu.ijse.theserenitymentalhealththerapycenter;
    exports edu.ijse.theserenitymentalhealththerapycenter.controller;
    opens edu.ijse.theserenitymentalhealththerapycenter.controller to jakarta.persistence, javafx.base, javafx.fxml, org.hibernate.orm.core;
}