package controllers;

import Application.Main;
import models.Customer;
import models.Item;
import models.Transaction; 
import models.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;

public class TransactionController {

    @FXML private ComboBox<Customer> comboCustomer;
    @FXML private ComboBox<Item> comboItem; 
    @FXML private TextField inputKuantitas;
    

    @FXML
    public void initialize() {
        try { 
            comboCustomer.setItems(FXCollections.observableArrayList(Customer.getAllCustomers()));
            comboItem.setItems(FXCollections.observableArrayList(Item.getAllItems())); 

        } catch (SQLException e) {
            tampilkanError("DB Error", "Gagal memuat data awal: " + e.getMessage());
        }
    }

    @FXML
    private void aksiTambahItem() {
        // Dihilangkan, karena kita langsung menyimpan di aksiSelesaikanTransaksi
        aksiSelesaikanTransaksi(); 
    }

    @FXML
    private void aksiSelesaikanTransaksi() {
        Customer customerDipilih = comboCustomer.getValue();
        Item itemDipilih = comboItem.getValue();

        if (customerDipilih == null || itemDipilih == null) {
            tampilkanError("Peringatan", "Pilih Pelanggan dan Item terlebih dahulu!");
            return;
        }

        int kuantitas;
        try {
            kuantitas = Integer.parseInt(inputKuantitas.getText());
            if (kuantitas <= 0) {
                tampilkanError("Input Salah", "Kuantitas harus lebih dari nol.");
                return;
            }
        } catch (NumberFormatException e) {
            tampilkanError("Input Salah", "Kuantitas harus berupa angka.");
            return;
        }

        try {
            int userId = User.getActiveUser().getId();
            
            Transaction.simpanTransaksiItem(
                customerDipilih.getId(), 
                userId, 
                itemDipilih.getId(), 
                kuantitas
            );

            tampilkanPesan("Sukses", "Transaksi penjualan item berhasil dicatat.");
            
            inputKuantitas.clear();
            Main.tampilkanDashboard();

        } catch (SQLException e) {
            tampilkanError("Database Error", "Gagal menyimpan transaksi: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
             tampilkanError("Validasi Error", e.getMessage());
        } catch (Exception e) {
             tampilkanError("Error Aplikasi", "Terjadi kesalahan yang tidak terduga.");
            e.printStackTrace();
        }
    }

    @FXML
    private void aksiKembali() {
        Main.tampilkanDashboard();
    }
    
    // --- METHOD HELPER ---
    private void tampilkanPesan(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void tampilkanError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}