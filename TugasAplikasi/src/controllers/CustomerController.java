package controllers;

import Application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Customer;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;

import java.sql.SQLException;

public class CustomerController {

    @FXML private TableView<Customer> tabelCustomers;
    @FXML private TableColumn<Customer, Integer> kolomId;
    @FXML private TableColumn<Customer, String> kolomNama;
    @FXML private TableColumn<Customer, String> kolomEmail;
    @FXML private TableColumn<Customer, String> kolomTelepon;

    @FXML private TextField inputNama;
    @FXML private TextField inputEmail;
    @FXML private TextField inputTelepon;

    private ObservableList<Customer> customerData = FXCollections.observableArrayList();
    private int selectedCustomerId = -1;

    @FXML
    public void initialize() {
        kolomId.setCellValueFactory(new PropertyValueFactory<>("id"));
        kolomNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kolomEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        kolomTelepon.setCellValueFactory(new PropertyValueFactory<>("telepon"));

        muatDataCustomers();

        tabelCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    tampilkanDetailCustomer(newValue);
                    if (newValue != null) {
                        selectedCustomerId = newValue.getId();
                    } else {
                        selectedCustomerId = -1;
                    }
                });
    }

    private void muatDataCustomers() {
        try {
            customerData.clear();
            customerData.addAll(Customer.getAllCustomers()); 
            tabelCustomers.setItems(customerData);
            
        } catch (SQLException e) {
            tampilkanError("Gagal Memuat Data", "Kesalahan saat mengambil data pelanggan dari database.");
            e.printStackTrace();
        }
    }
    
    private void tampilkanDetailCustomer(Customer customer) {
        if (customer != null) {
           inputNama.setText(customer.getNama());
           inputEmail.setText(customer.getEmail());
           inputTelepon.setText(customer.getTelepon());
       } else {
           // Clear form
            inputNama.setText("");
            inputEmail.setText("");
            inputTelepon.setText("");
       }
   }
    

    @FXML
    private void aksiTambah() {
        try {
            String nama = inputNama.getText();
            String alamat = inputEmail.getText();
            String telepon = inputTelepon.getText();

            if (nama.isEmpty()) {
                tampilkanError("Input Error", "Nama pelanggan tidak boleh kosong.");
                return;
            }

            Customer newCustomer = new Customer(0, nama, alamat, telepon);
            Customer.addCustomer(newCustomer);
            
            muatDataCustomers();
            tampilkanPesan("Sukses", "Pelanggan baru berhasil ditambahkan.");

        } catch (SQLException e) {
            tampilkanError("DB Error", "Gagal menambahkan pelanggan.");
            e.printStackTrace();
        }
    }
    
    @FXML
    private void aksiUpdate() {
        if (selectedCustomerId == -1) {
            tampilkanPesan("Peringatan", "Pilih pelanggan dari tabel yang ingin diupdate.");
            return;
        }
        
        try {
            String nama = inputNama.getText();
            String alamat = inputEmail.getText();
            String telepon = inputTelepon.getText();
            
            Customer updatedCustomer = new Customer(selectedCustomerId, nama, alamat, telepon);
            Customer.updateCustomer(updatedCustomer);
            
            muatDataCustomers();
            tampilkanPesan("Sukses", "Data pelanggan berhasil diupdate.");

        } catch (SQLException e) {
            tampilkanError("DB Error", "Gagal mengupdate pelanggan.");
            e.printStackTrace();
        }
    }
    
    @FXML
    private void aksiHapus() {
        if (selectedCustomerId == -1) {
            tampilkanPesan("Peringatan", "Pilih pelanggan dari tabel yang ingin dihapus.");
            return;
        }

        Alert konfirmasi = new Alert(AlertType.CONFIRMATION, "Yakin hapus pelanggan ini?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> hasil = konfirmasi.showAndWait();

        if (hasil.isPresent() && hasil.get() == ButtonType.YES) {
            try {
                Customer.deleteCustomer(selectedCustomerId);
                muatDataCustomers();
                tampilkanPesan("Sukses", "Pelanggan berhasil dihapus.");
                tampilkanDetailCustomer(null);
                selectedCustomerId = -1;

            } catch (SQLException e) {
                tampilkanError("DB Error", "Gagal menghapus pelanggan.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void aksiKembali() {
        Main.tampilkanDashboard();
    }

    private void tampilkanError(String title, String content) {
    }
    
    private void tampilkanPesan(String title, String content) {
    }
    
}