package Cetacea;

public class Orca extends Cetacea{
	private String julukan;
	
	public Orca(String nama,String julukan) {
		super(nama, "Semua Samudra (dari kutub ke troppis)");
		this.julukan = julukan;
	}
	
	public String getJulukan() {
		return this.julukan;
	}
	
	public void makan() {
		System.out.println("Perilaku : Berburu dalam bentuk kelompok atau disebut pod dengan posisi sebagai predator puncak");
	}
	
	public void Info() {
		super.Info();
		System.out.println("Fakta : Dikenal juga sebagai " + getJulukan());
	}
}
