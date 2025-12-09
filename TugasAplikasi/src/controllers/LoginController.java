package controllers;

import Application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;

import java.sql.SQLException;

public class LoginController {
    @FXML private TextField inputUsername;
    @FXML private PasswordField inputPassword;
    @FXML private Label labelPesan;

    @FXML
    private void aksiLogin() {
        String username = inputUsername.getText();
        String password = inputPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            labelPesan.setText("Username dan Password tidak boleh kosong.");
            return;
        }

        try {
            User userValid = User.validateLogin(username, password);

            if (userValid != null) {
                User.setActiveUser(userValid);

                Main.tampilkanDashboard();
                
            } else {
                labelPesan.setText("Username atau Password salah.");
            }

        } catch (SQLException e) {
            tampilkanError("Koneksi Database Gagal", 
                           "Terjadi kesalahan saat mencoba koneksi ke database.");
            e.printStackTrace();
        }
    }
    
    private void tampilkanError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}