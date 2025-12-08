package Controller;

import Application.Main;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label labelSapaan;

    @FXML
    public void initialize() {
        User activeUser = User.getActiveUser();
        if (activeUser != null) {
            labelSapaan.setText("Selamat Datang, " + activeUser.getUsername() + "!");
        }
    }

    @FXML
    private void aksiTampilkanUser() {
        Main.tampilkanUser();
    }

    @FXML
    private void aksiTampilkanItems() {
        Main.tampilkanItems();
    }

    @FXML
    private void aksiTampilkanCustomer() {
        tampilkanPesan("Info", "Fitur Kelola Pelanggan belum diimplementasikan.");
    }

    @FXML
    private void aksiTampilkanTransaction() {

        tampilkanPesan("Info", "Fitur Transaksi belum diimplementasikan.");
    }

    @FXML
    private void aksiLogout() {
        User.setActiveUser(null); 
        
        Main.tampilkanLogin();
    }

    private void tampilkanPesan(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}