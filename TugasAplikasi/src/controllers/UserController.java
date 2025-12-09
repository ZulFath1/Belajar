package controllers;

import Application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.User;

public class UserController {

    @FXML
    private TextField inputUsername;
    @FXML
    private TextField inputPassword; 

    @FXML
    public void initialize() {
        User activeUser = User.getActiveUser();

        if (activeUser != null) {
            inputUsername.setText(activeUser.getUsername());
            inputPassword.setText(activeUser.getPassword());
            
            inputUsername.setEditable(false); 
            inputPassword.setEditable(false);
        }
    }

    @FXML
    private void aksiKembali() {
        Main.tampilkanDashboard();
    }
}