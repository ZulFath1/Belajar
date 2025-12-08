package Model;

import java.time.LocalDateTime;

public class Transaction {
    
    private int id;
    private int customerId;
    private int userId;     
    private LocalDateTime tanggal;
    private double totalHarga;
    

    public Transaction(int id, int customerId, int userId, LocalDateTime tanggal, double totalHarga) {
        this.id = id;
        this.customerId = customerId;
        this.userId = userId;
        this.tanggal = tanggal;
        this.totalHarga = totalHarga;
    }

    
    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public int getUserId() { return userId; }
    public LocalDateTime getTanggal() { return tanggal; }
    public double getTotalHarga() { return totalHarga; }

    public void setId(int id) { this.id = id; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setTanggal(LocalDateTime tanggal) { this.tanggal = tanggal; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }
}