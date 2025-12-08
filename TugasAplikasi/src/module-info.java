module TugasAplikasi {
    // 1. DAFTAR KEBUTUHAN (Barang bawaan yang wajib ada)
	requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires java.sql;
    
    exports Application;

    opens Controller to javafx.fxml;
}