package Model;

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

    
    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getAlamat() { return alamat; }
    public String getTelepon() { return telepon; }

    public void setId(int id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public void setTelepon(String telepon) { this.telepon = telepon; }
}