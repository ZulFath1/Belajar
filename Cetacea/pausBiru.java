package Cetacea;

public class pausBiru extends Cetacea{
	private int panjangMaksimal;
	
	public pausBiru(String nama,int panjangMaksimal) {
		super(nama, "Samudra Terbuka");
		this.panjangMaksimal = panjangMaksimal;
	}
	
	public int getpanjangMaksimal() {
		return this.panjangMaksimal;
	}
	
	public void makan() {
		System.out.println("Perilaku : Menyaring krill dan plankton dari air laut");
	}
	
	public void Info() {
		super.Info();
		System.out.println("Fakta : Merupakan mamalia laut terbersar yang panjangnya mencapai " + getpanjangMaksimal() + " Meter");
	}
}
