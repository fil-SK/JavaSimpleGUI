package zadatak;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MestoZaTocenje extends Canvas implements Runnable{

	private AparatZaTocenje owner;
	Sladoled sladoled;
	
	private long sleepTime = 500;	// milis
	Thread nitCanvas;
	
	
	public MestoZaTocenje(AparatZaTocenje owner) {
		this.owner = owner;
		sladoled = new Sladoled(200);
	}
	
	public Sladoled dohvatiSladoled() {
		return sladoled;
	}

	
	@Override
	public void paint(Graphics g) {
		
		nitCanvas = new Thread(this);		// kreiramo nit svaki put kad se pozove metoda paint
		nitCanvas.start();
		
	}
	
	
	@Override
	public void run() {
		
		Graphics g = this.getGraphics();
		Color boja = owner.pritisnutoDugme.getBackground();							// dohvati pozadinu odabranog dugmeta		
		//g.translate(0, this.getHeight()); 										// postavljamo koordinatni pocetak cetkice
																				// da ti koord. pocetak bude donji levi ugao
		g.setColor(boja);
		
		// Dodao sam
		for(PravougaonikBoja pb : owner.listaPravougaonika) {
		
			g.setColor(pb.vratiBoju());
			
			g.fillRect((int)pb.vratiPravoug().getX(), (int)pb.vratiPravoug().getY(), (int)pb.vratiPravoug().getWidth(), (int)pb.vratiPravoug().getHeight());
			this.revalidate();
		}
		
		if(owner.prodajPushed == true) {
			g.setColor(Color.WHITE);
			g.clearRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			this.revalidate();
		}
		
		
		nitCanvas.interrupt();
		try {
			nitCanvas.join();
		} catch (InterruptedException e) {}
		
	}
	
}
