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
    private String email;
    private String telepon;

    public Customer(int id, String nama, String email, String telepon) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.telepon = telepon;
    }
    
    
    public static List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        String query = "SELECT id, nama, email, telepon FROM pelanggan";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("email"), // <-- DIGANTI
                    rs.getString("telepon")
                );
                customerList.add(customer);
            }
        }
        return customerList;
    }
    
    public static void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO pelanggan (nama, email, telepon) VALUES (?, ?, ?)"; // <-- DIGANTI
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, customer.getNama());
            stmt.setString(2, customer.getEmail()); // <-- DIGANTI
            stmt.setString(3, customer.getTelepon());
            
            stmt.executeUpdate();
        }
    }
    
    public static void updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE pelanggan SET nama = ?, email = ?, telepon = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, customer.getNama());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getTelepon());
            stmt.setInt(4, customer.getId());
            
            stmt.executeUpdate();
        }
    }
    
    public static void deleteCustomer(int id) throws SQLException {
        String query = "DELETE FROM pelanggan WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        }
    }

    
    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getEmail() { return email; }
    public String getTelepon() { return telepon; }

    public void setId(int id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setEmail(String email) { this.email = email; }
    public void setTelepon(String telepon) { this.telepon = telepon; }
}