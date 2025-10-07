package Cetacea;

public class Main {

	public static void main(String[] args) {
		pausBiru paus = new pausBiru ("Paus Biru", 30);
		Orca Orca = new Orca	("Orcinus Orca", "Paus Pembunuh");
		PesutMahakam pesut = new PesutMahakam("Pesut Mahakam","Sangat Terancam Punah");
		
		paus.Info();
		paus.makan();
		paus.bernafas();
		
		Orca.Info();
		Orca.makan();
		Orca.bernafas();
		
		pesut.Info();
		pesut.makan();
		pesut.bernafas();

	}

}
