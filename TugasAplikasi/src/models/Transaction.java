package models;

import java.sql.*;
import java.time.LocalDateTime;

public class Transaction {
    
    private int id;
    private int customerId; 
    private int userId; // Menggantikan buyerId, agar konsisten
    private int itemId;
    private int quantity;
    private double total;
    private LocalDateTime date; 

    public Transaction(int id, int customerId, int userId, int itemId, int quantity, double total, LocalDateTime date) {
        this.id = id;
        this.customerId = customerId;
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
    }
    
    public Transaction(int customerId, int userId, int itemId, int quantity, double total, LocalDateTime date) {
        this.customerId = customerId;
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
    }

    public static void simpanTransaksiItem(int customerId, int userId, int itemId, int kuantitas) 
        throws SQLException, IllegalArgumentException {
        
        double hargaSatuan = getItemHarga(itemId);
        double totalHarga = hargaSatuan * kuantitas;
        
        String query = "INSERT INTO transaksi (customerId, userId, itemId, quantity, totalHarga, tanggal) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, customerId);
            stmt.setInt(2, userId);
            stmt.setInt(3, itemId);
            stmt.setInt(4, kuantitas);
            stmt.setDouble(5, totalHarga);
            stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now())); 
            
            stmt.executeUpdate();
        }
    }
    
    private static double getItemHarga(int itemId) throws SQLException, IllegalArgumentException {
        String query = "SELECT harga FROM barang WHERE id = ?"; 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("harga");
                }
            }
        }
        throw new IllegalArgumentException("Item dengan ID tersebut tidak ditemukan.");
    }
    
    // --- GETTER dan SETTER (tetap sama) ---
    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public int getUserId() { return userId; }
    public int getItemId() { return itemId; }
    public int getQuantity() { return quantity; }
    public double getTotal() { return total; }
    public LocalDateTime getDate() { return date; }

    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setTotal(double total) { this.total = total; }
    public void setDate(LocalDateTime date) { this.date = date; }
}