package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Item {
    
    private int id;
    private String nama;
    private String kategori;
    private double harga;
    private int stok;

    public Item(int id, String nama, String kategori, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }
    
    public static List<Item> getAllItems() throws SQLException {
        List<Item> itemList = new ArrayList<>();
        String query = "SELECT * FROM barang";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Item item = new Item(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("kategori"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                );
                itemList.add(item);
            }
        }
        return itemList;
    }
    
    public static void addItem(Item item) throws SQLException {
        String query = "INSERT INTO barang (nama, kategori, harga, stok) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, item.getNama());
            stmt.setString(2, item.getKategori());
            stmt.setDouble(3, item.getHarga());
            stmt.setInt(4, item.getStok());
            
            stmt.executeUpdate();
        }
    }
    
    public static void updateItem(Item item) throws SQLException {
        String query = "UPDATE barang SET nama = ?, kategori = ?, harga = ?, stok = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, item.getNama());
            stmt.setString(2, item.getKategori());
            stmt.setDouble(3, item.getHarga());
            stmt.setInt(4, item.getStok());
            stmt.setInt(5, item.getId()); // WHERE ID
            
            stmt.executeUpdate();
        }
    }
    
    public static void deleteItem(int id) throws SQLException {
        String query = "DELETE FROM barang WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        }
    }


    // --- GETTER dan SETTER (Wajib untuk TabelView) ---
    
    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getKategori() { return kategori; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }

    public void setId(int id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public void setHarga(double harga) { this.harga = harga; }
    public void setStok(int stok) { this.stok = stok; }
}