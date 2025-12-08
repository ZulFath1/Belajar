package Controller;

import Application.Main;
import Model.Customer;
import Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;

public class TransactionController {

    @FXML private ComboBox<Customer> comboCustomer;
    @FXML private TableView<Item> tabelStok;
    @FXML private TableView<Item> tabelKeranjang;
    @FXML private TextField inputKuantitas;
    @FXML private Label labelTotal;


    @FXML private TableColumn<Item, String> keranjangKolomNama;

    private ObservableList<Item> stokData = FXCollections.observableArrayList();
    private ObservableList<Item> keranjangData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        try {
            comboCustomer.setItems(FXCollections.observableArrayList(Customer.getAllCustomers()));
            stokData.addAll(Item.getAllItems());
            tabelStok.setItems(stokData);

        } catch (SQLException e) {
        }
    }

    @FXML
    private void aksiTambahItem() {
    }

    @FXML
    private void aksiSelesaikanTransaksi() {
    }

    @FXML
    private void aksiKembali() {
        Main.tampilkanDashboard();
    }
   
}