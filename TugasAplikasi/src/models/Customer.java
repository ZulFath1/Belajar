package models;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Customer {

    private int id;
    private String nama;
    private String alamat;
    private String telepon;

    public Customer(int id, String nama, String alamat, String telepon) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
    }
    
    
    public static List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        String query = "SELECT * FROM customer";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("telepon")
                );
                customerList.add(customer);
            }
        }
        return customerList;
    }
    
    public static void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO customer (nama, alamat, telepon) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, customer.getNama());
            stmt.setString(2, customer.getAlamat());
            stmt.setString(3, customer.getTelepon());
            
            stmt.executeUpdate();
        }
    }
    
    public static void updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE customer SET nama = ?, alamat = ?, telepon = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, customer.getNama());
            stmt.setString(2, customer.getAlamat());
            stmt.setString(3, customer.getTelepon());
            stmt.setInt(4, customer.getId()); // WHERE ID
            
            stmt.executeUpdate();
        }
    }
    
    public static void deleteCustomer(int id) throws SQLException {
        String query = "DELETE FROM customer WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        }
    }

    
    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getAlamat() { return alamat; }
    public String getTelepon() { return telepon; }

    public void setId(int id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public void setTelepon(String telepon) { this.telepon = telepon; }
}