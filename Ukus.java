package zadatak;

import java.awt.Color;

public class Ukus {

	private String naziv;
	private Color boja;
	
	public Ukus(String n, Color b) {
		naziv = new String(n);
		boja = b;
	}
	
	public String dohvatiNaziv() {
		return naziv;
	}
	public Color dohvatiBoju() {
		return boja;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boja == null) ? 0 : boja.hashCode());
		result = prime * result + ((naziv == null) ? 0 : naziv.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof Ukus)) return false;
		
		Ukus u = (Ukus)obj;
		
		return this.naziv.equals(u.naziv);
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(naziv).append("]");
		return sb.toString();
	}
	
}
