package Cetacea;

public class PesutMahakam extends Cetacea {
	private String statusKonservasi;
	
	public PesutMahakam (String nama, String statusKonservasi) {
		super(nama,"Sungai Mahakam (Air Tawar)");
		this.statusKonservasi = statusKonservasi;
	}
	
	public String getstatusKonservasi() {
		return this.statusKonservasi;
	}
	
	public void makan() {
		System.out.println("Perilaku : Memakan ikan kecil dan udang yang ada di dasar sungai");
	}
	
	public void Info() {
		super.Info();
		System.out.println("Fakat : Status Konservasi " + getstatusKonservasi());
	}
}
