package zadatak;

import java.awt.Color;
import java.awt.Rectangle;

public class PravougaonikBoja {

	private Rectangle r;
	private Color c;

	public PravougaonikBoja(Rectangle re, Color co) {
		r = re;
		c = co;
	}
	public Rectangle vratiPravoug() {
		return r;
	}
	public Color vratiBoju() {
		return c;
	}
	
}
