package Cetacea;

public class Cetacea {
	private String nama;
	private String habitat;
	
	public Cetacea (String nama, String habitat) {
		this.nama = nama;
		this.habitat = habitat;
	}
	
	public String getNama() {
		return this.nama;
	}
	
	public String getHabitat() {
		return this.habitat;
	}
	
	public void makan() {
		System.out.println("Sedang Mencari Makanan");
	}
	
	public void Info() {
		System.out.println("Nama : " + getNama());
		System.out.println("Habitat :" + getHabitat());
	}
	
	public void bernafas() {
		System.out.println("Perilaku : Bernafas ke permukaan menggunakan lubang sembur yang berada di atas kepala\n");
	}
}
