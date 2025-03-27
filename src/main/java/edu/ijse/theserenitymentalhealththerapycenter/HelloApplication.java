package edu.ijse.theserenitymentalhealththerapycenter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/view/PaymentInvoiceManagementForm.fxml"));
        Scene scene = new Scene(load);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}