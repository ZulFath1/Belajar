package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private static Stage mainStage; 

    @Override
    public void start(Stage stage) {
        mainStage = stage;
        mainStage.setTitle("Aplikasi Toko Penjualan");
        
        tampilkanLogin();
        
        mainStage.show();
    }


    public static void tampilkanLogin() {
        try {
            FXMLLoader pemuat = new FXMLLoader(Main.class.getResource("/views/Login.fxml"));
            Parent akar = pemuat.load();
            
            mainStage.setScene(new Scene(akar));
            mainStage.centerOnScreen();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tampilkanDashboard() {
        try {
            FXMLLoader pemuat = new FXMLLoader(Main.class.getResource("/views/Dashboard.fxml"));
            Parent akar = pemuat.load();
            
            mainStage.setScene(new Scene(akar));
            mainStage.centerOnScreen(); 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static void tampilkanUser() { 
        try {
            FXMLLoader pemuat = new FXMLLoader(Main.class.getResource("/views/User.fxml")); 
            Parent akar = pemuat.load();
            
            mainStage.setScene(new Scene(akar));
            mainStage.centerOnScreen(); 
            
        } catch (IOException e) {
             System.err.println("Error memuat User.fxml.");
             e.printStackTrace();
        }
    }


    public static void tampilkanItems() {
        try {
            FXMLLoader pemuat = new FXMLLoader(Main.class.getResource("/views/Items.fxml"));
            Parent akar = pemuat.load();
            
            mainStage.setScene(new Scene(akar));
            mainStage.centerOnScreen(); 
            
        } catch (IOException e) {
             System.err.println("Error memuat Items.fxml.");
             e.printStackTrace();
        }
    }
    
    // Method Helper: Controller memerlukan akses ke Stage untuk menyimpan UserData	
    public static Stage getMainStage() {
        return mainStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}