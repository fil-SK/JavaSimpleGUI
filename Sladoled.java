package zadatak;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Sladoled {

	private HashMap<Ukus, Integer> ukusi;
	private int velicinaCase;
	private int trenutnoUCasi;
	
	public Sladoled(int velicina) {
		ukusi = new HashMap<>();
		velicinaCase = velicina;
	}
	
	public int dohvatiVelicinuCase() {
		return velicinaCase;
	}
	
	public HashMap<Ukus, Integer> dohvatiSveUkuse(){
		return ukusi;
	}
	
	public void dodajUkus(Ukus ukus, int kolicina) {
		
		int dodaj;
		
		// Proveravamo da li je casa puna, ako jeste samo izadjemo
		if(trenutnoUCasi == velicinaCase) {
			return;
		}
		
		// Proveravamo da li ta kolicina prevazilazi velicinu case
		// Ako da, onda dodaj taman toliko da se napuni casa do kraja
		if(trenutnoUCasi + kolicina > velicinaCase) {
			dodaj = velicinaCase - trenutnoUCasi;
			trenutnoUCasi += dodaj;
		}
		else {
		// Ako ne, onda imas mesta da dodas celu kolicinu
			dodaj = kolicina;
			trenutnoUCasi += dodaj;
		}
		
		// Proveravamo da li je taj ukus ranije dodat u casu
		if(ukusi.containsKey(ukus)) {
			
			// Ako taj ukus postoji, treba mu povecati kolicinu
			int staraKolicina = ukusi.get(ukus);
			int novaKolicina = staraKolicina + dodaj;
			ukusi.replace(ukus, novaKolicina);
			
			return;
		}
		else {
			// Ako ukus ne postoji, samo ga dodajemo
			ukusi.put(ukus, dodaj);
			return;
		}
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		Iterator<Map.Entry<Ukus, Integer>> iter = ukusi.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry<Ukus, Integer> entry = iter.next();
			sb.append(entry.getValue()).append("ml").append(entry.getKey()).append(" "); // kolicina ml ukus
		}
		
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		
		/*
		 * Sladoled s1 = new Sladoled(100);
		Ukus u1 = new Ukus("Cokolada", Color.BLACK);
		Ukus u2 = new Ukus("Vanila", Color.WHITE);
		Ukus u3 = new Ukus("Visnja", Color.RED);
		Ukus u4 = new Ukus("Sumsko voce", Color.GREEN);
		s1.dodajUkus(u1, 20);
		s1.dodajUkus(u1, 20);
		s1.dodajUkus(u1, 20);
		s1.dodajUkus(u1, 20);
		s1.dodajUkus(u1, 20);
		s1.dodajUkus(u1, 20);
		//s1.dodajUkus(u2, 20);
		//s1.dodajUkus(u3, 50);
		//s1.dodajUkus(u4, 30);
		//s1.dodajUkus(u1, 100);
		//s1.dodajUkus(u3, 50);
		//s1.dodajUkus(u2, 100);
		
		System.out.println(s1);
		 * */
		
		
	}
}
