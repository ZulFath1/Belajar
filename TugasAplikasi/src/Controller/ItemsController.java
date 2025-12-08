package Controller;

import Application.Main;
import Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import java.util.Optional;

public class ItemsController {
    @FXML private TableView<Item> tabelItems;
    @FXML private TableColumn<Item, Integer> kolomId;
    @FXML private TableColumn<Item, String> kolomNama;
    @FXML private TableColumn<Item, String> kolomKategori;
    @FXML private TableColumn<Item, Double> kolomHarga;
    @FXML private TableColumn<Item, Integer> kolomStok;

    @FXML private TextField inputNama;
    @FXML private TextField inputKategori;
    @FXML private TextField inputHarga;
    @FXML private TextField inputStok;

    private ObservableList<Item> itemData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        kolomId.setCellValueFactory(new PropertyValueFactory<>("id"));
        kolomNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kolomKategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        kolomHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        kolomStok.setCellValueFactory(new PropertyValueFactory<>("stok"));

        muatDataItems();

        tabelItems.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> tampilkanDetailItem(newValue));
    }

    private void muatDataItems() {
        try {
            itemData.clear();
            itemData.addAll(Item.getAllItems()); 
            tabelItems.setItems(itemData);
            
        } catch (SQLException e) {
            tampilkanError("Gagal Memuat Data", "Kesalahan saat mengambil data dari database.");
            e.printStackTrace();
        }
    }

    private void tampilkanDetailItem(Item item) {
        if (item != null) {
            inputNama.setText(item.getNama());
            inputKategori.setText(item.getKategori());
            inputHarga.setText(String.valueOf(item.getHarga()));
            inputStok.setText(String.valueOf(item.getStok()));
        } else {
            inputNama.setText("");
        }
    }

    @FXML
    private void aksiTambah() {
        tampilkanPesan("Status", "Fungsi Tambah belum terhubung ke database.");
    }
    
    @FXML
    private void aksiUpdate() {
        tampilkanPesan("Status", "Fungsi Update belum terhubung ke database.");
    }
    
    @FXML
    private void aksiHapus() {
        tampilkanPesan("Status", "Fungsi Hapus belum terhubung ke database.");
    }

    @FXML
    private void aksiKembali() {
        Main.tampilkanDashboard();
    }

    private void tampilkanError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void tampilkanPesan(String title, String content) {
    }
}