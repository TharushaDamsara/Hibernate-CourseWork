package edu.ijse.theserenitymentalhealththerapycenter.util.AlertsPack;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class CustomAlerts extends Alert {
    public CustomAlerts(AlertType alertType) {
        super(alertType);
    }

    public CustomAlerts(AlertType alertType, String s, ButtonType... buttonTypes) {
        super(alertType, s, buttonTypes);
    }

    public static void InvalidPassword(){
        new CustomAlerts(AlertType.WARNING,"Invalid Password").show();
    }

    public static void EmailNotFound(){
        new CustomAlerts(AlertType.WARNING,"This Email Account is not found \nSomething Went Wrong").show();
    }

    public static void isNotValidName(){
        new CustomAlerts(AlertType.WARNING,"Name must contain only letters and valid symbols.\nIts Cannot be Null Ex (Names, Addresses)").show();
    }

    public static void isNotValidEmail(){
        new CustomAlerts(AlertType.WARNING,"Please enter a valid email address.\n(e.g., example@gmail.com).").show();
    }

    public static void isNotValidPassword(){
        new CustomAlerts(AlertType.WARNING,"Password must be between 4 to 30 characters,\ncontaining only letters and numbers.").show();
    }

    public static void isNotValidMobileNumber(){
        new CustomAlerts(AlertType.WARNING,"Enter a valid Sri Lankan mobile number (e.g., 0712345678).").show();
    }

    public static void isNotValidDouble(){
        new CustomAlerts(AlertType.WARNING,"You can only enter the numbers \n(ex - 30,30.5) ").show();
    }

    //SUCCSESS
    public static void saved(){
        new CustomAlerts(AlertType.CONFIRMATION,"Saved Successfully").show();
    }

    public static void update(){
        new CustomAlerts(AlertType.CONFIRMATION,"Updated Successfully").show();
    }

    public static void delete(){
        new CustomAlerts(AlertType.CONFIRMATION,"Deleted").show();
    }

    //CONFORMATION
    public static boolean doYouWantToDelete(){
        Alert alert = new Alert(AlertType.WARNING,"Do you want to delete ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.YES;
    }

    public static void comboboxValueNotSelected(){
        new Alert(AlertType.WARNING,"The combobox value is not selected").show();
    }

    public static void notSelectedTheDate(){
        new CustomAlerts(AlertType.WARNING,"Before save select the date first").show();
    }
}
