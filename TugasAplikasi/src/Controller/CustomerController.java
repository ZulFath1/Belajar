package Controller;

import Application.Main;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;

public class CustomerController {

    @FXML private TableView<Customer> tabelCustomers;
    @FXML private TableColumn<Customer, Integer> kolomId;
    @FXML private TableColumn<Customer, String> kolomNama;
    @FXML private TableColumn<Customer, String> kolomAlamat;
    @FXML private TableColumn<Customer, String> kolomTelepon;

    @FXML private TextField inputNama;
    @FXML private TextField inputAlamat;
    @FXML private TextField inputTelepon;

    private ObservableList<Customer> customerData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        kolomId.setCellValueFactory(new PropertyValueFactory<>("id"));
        kolomNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kolomAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        kolomTelepon.setCellValueFactory(new PropertyValueFactory<>("telepon"));

        muatDataCustomers();

        tabelCustomers.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> tampilkanDetailCustomer(newValue));
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
    }
    

    @FXML private void aksiTambah() {
    }
    
    @FXML private void aksiUpdate() {}
    
    @FXML private void aksiHapus() {}

    @FXML
    private void aksiKembali() {
        Main.tampilkanDashboard();
    }

    private void tampilkanError(String title, String content) {
    }
}