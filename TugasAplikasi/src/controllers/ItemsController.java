package controllers;

import Application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Item;

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
    private int selectedItemId = -1;

    @FXML
    public void initialize() {
        kolomId.setCellValueFactory(new PropertyValueFactory<>("id"));
        kolomNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kolomKategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        kolomHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        kolomStok.setCellValueFactory(new PropertyValueFactory<>("stok"));

        muatDataItems();

        tabelItems.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    tampilkanDetailItem(newValue);
                    if (newValue != null) {
                        selectedItemId = newValue.getId();
                    } else {
                        selectedItemId = -1;
                    }
                });
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
        try {
            String nama = inputNama.getText();
            String kategori = inputKategori.getText();
            double harga = Double.parseDouble(inputHarga.getText());
            int stok = Integer.parseInt(inputStok.getText());

            Item newItem = new Item(0, nama, kategori, harga, stok);
            Item.addItem(newItem);
            
            muatDataItems();
            tampilkanPesan("Sukses", "Barang baru berhasil ditambahkan.");

        } catch (NumberFormatException e) {
            tampilkanError("Input Salah", "Harga dan Stok harus berupa angka yang valid.");
        } catch (SQLException e) {
            tampilkanError("DB Error", "Gagal menambahkan barang ke database.");
            e.printStackTrace();
        }
    }
    
    @FXML
    private void aksiUpdate() {
        if (selectedItemId == -1) {
            tampilkanPesan("Peringatan", "Pilih barang dari tabel yang ingin diupdate.");
            return;
        }
        
        try {
            String nama = inputNama.getText();
            String kategori = inputKategori.getText();
            double harga = Double.parseDouble(inputHarga.getText());
            int stok = Integer.parseInt(inputStok.getText());
            
            Item updatedItem = new Item(selectedItemId, nama, kategori, harga, stok);
            Item.updateItem(updatedItem);
            
            muatDataItems();
            tampilkanPesan("Sukses", "Data barang berhasil diupdate.");

        } catch (NumberFormatException e) {
            tampilkanError("Input Salah", "Harga dan Stok harus berupa angka yang valid.");
        } catch (SQLException e) {
            tampilkanError("DB Error", "Gagal mengupdate barang.");
            e.printStackTrace();
        }
    }
    
    @FXML
    private void aksiHapus() {
        if (selectedItemId == -1) {
            tampilkanPesan("Peringatan", "Pilih barang dari tabel yang ingin dihapus.");
            return;
        }

        Alert konfirmasi = new Alert(Alert.AlertType.CONFIRMATION, "Yakin hapus item ini?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> hasil = konfirmasi.showAndWait();

        if (hasil.isPresent() && hasil.get() == ButtonType.YES) {
            try {
                Item.deleteItem(selectedItemId);
                muatDataItems();
                tampilkanPesan("Sukses", "Barang berhasil dihapus.");
                tampilkanDetailItem(null);
                selectedItemId = -1;

            } catch (SQLException e) {
                tampilkanError("DB Error", "Gagal menghapus barang.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void aksiKembali() {
        Main.tampilkanDashboard();
    }

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