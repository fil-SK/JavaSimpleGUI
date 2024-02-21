package zadatak;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class AparatZaTocenje extends Panel {

	public static int buttonCnt = 0;
	Button pritisnutoDugme = new Button();								// pomocna promenljiva koja cuva podatak o odabranom dugmetu
	Thread nitPressed;
	ArrayList<PravougaonikBoja> listaPravougaonika = new ArrayList<>();
	Rectangle pravougaonik;
	Color bojaIspisa;
	boolean prodajPushed = false;
	
	

	private Sladoleddzinica owner;
	
	// Komponente
	MestoZaTocenje mestoZaTocenje = new MestoZaTocenje(this);	// Ovo je kanvas
	private Button prodajButton = new Button("Prodaj");					// Dugme za prodaju
	private ArrayList<Button> ukusButtonList = new ArrayList<>();
	private Button ukusButton;											// Dugme pojedinacnog ukusa
	private Label sladoledLabel = new Label("Sladoled:");
	private Label sladoledSastojciLabel = new Label("");				// Labela na kojoj ce se ispisivati sastojci sladoleda
	
	
	// Paneli
	// Aparat za tocenje je centralni panel
	private Panel centerCenterPanel = new Panel();
	private Panel centerBottomPanel = new Panel();
	private Panel centerLeftPanel = new Panel();
	private Panel centerRightPanel = new Panel();
	private Panel rightUpPanel = new Panel();
	private Panel rightDownPanel = new Panel();
	
	/* Hijerarhija panela:
	 * Aparat za tocenje (centerPanel)
	 * 		centerCenterPanel
	 * 				centerLeftPanel
	 * 				centerRightPanel
	 * 					rightUpPanel
	 * 					rightDownPanel
	 * 		centerBottomPanel
	*/
	
	//public void postaviLabeluZaTocenjeSladoleda() {
	//	sladoledSastojciLabel.setText(nekiTekst);
	//}
	
	private void postaviPanel() {
		
		pritisnutoDugme.setBackground(Color.WHITE);			// pocetna boja kanvasa
		
		// Dugme inicijalno neaktivno
				prodajButton.setEnabled(false);
				
				// Podesavanje layouta za panele
				this.setLayout(new BorderLayout(0, 0));
				centerCenterPanel.setLayout(new BorderLayout(0, 0));
				centerBottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
				centerLeftPanel.setLayout(new GridLayout(1, 1));	 			// Da bi jedno dugme zauzelo ceo panel
				centerRightPanel.setLayout(new BorderLayout(0, 0));
				rightUpPanel.setLayout(new BorderLayout(0, 0));
				rightDownPanel.setLayout(new BorderLayout(0, 0));
				
				// Podesavanje fonta labele
				sladoledLabel.setFont(new Font("Arial", Font.BOLD, 20));		
				
				// Podesavanje pozadine
				centerBottomPanel.setBackground(Color.LIGHT_GRAY);
				
				Color boja = Color.decode("#d9d9d9");
				centerLeftPanel.setBackground(boja);
				
				// Priprema dimenzija za komponente
				
				// Npr sirina centerLeft panela ce biti 60% glavnog prozora
				int sirina = owner.getWidth() * 6/10;
				int sirinaOstatak = owner.getWidth() * 4/10;
				
				// Hocemo da visina panela za ukuse bude najveca moguca (od visine prozora oduzmes visinu dna)
				int visinaDna = centerBottomPanel.getHeight() * 2;	// visine ova dva dna su iste
				int visina = owner.getHeight() - visinaDna;
				
				centerLeftPanel.setPreferredSize(new Dimension(sirina, visina));
				rightUpPanel.setPreferredSize(new Dimension(sirinaOstatak, visina/2));
				rightDownPanel.setPreferredSize(new Dimension(sirinaOstatak, visina/2));
				revalidate();
				
				
				
				// Gnezdenje panela i postavljanje elemenata
				
				// Popunjavanje centerRight panela
				rightUpPanel.add(prodajButton);
				//rightDownPanel.setBackground(Color.WHITE);
				rightDownPanel.add(mestoZaTocenje);
				centerRightPanel.add(rightUpPanel, BorderLayout.NORTH);
				centerRightPanel.add(rightDownPanel, BorderLayout.SOUTH);
				
				// Dodaj panele u centerCenter panel
				centerCenterPanel.add(centerLeftPanel, BorderLayout.CENTER);
				centerCenterPanel.add(centerRightPanel, BorderLayout.EAST);
				
				// Dodaj komponente u centerBottom panel
				centerBottomPanel.add(sladoledLabel);
				centerBottomPanel.add(sladoledSastojciLabel);
				
				// Dodaj panele u centerPanel tj. u AparatZaTocenje
				this.add(centerCenterPanel, BorderLayout.CENTER);
				this.add(centerBottomPanel, BorderLayout.SOUTH);
		
				
				
				// Osluskivac za dodavanje dugmeta sa ukusom
				owner.dodajUkusButton.addActionListener( (ae) -> {
					
	// TODO				// Provera da li ukus vec postoji
					//String nazivUkusa = new String(owner.nazivTextField.getText());
					//Color bojaUkusa = Color.decode("#" + owner.bojaTextField.getText());
				//	Ukus u = new Ukus(nazivUkusa, bojaUkusa);
					
					
					ukusButton = new Button(owner.nazivTextField.getText());
					Color pozadinaDugmeta = Color.decode("#" + owner.bojaTextField.getText());
					ukusButton.setBackground(pozadinaDugmeta);
					
					// Dodajemo dugme u ArrayList 
					ukusButtonList.add(ukusButton);
					
					// Za novonapravljeni ukus tj. novonapravljeno dugme dodajemo osluskivac buttonListener (kojeg pravimo dole)
					//ukusButtonList.get(buttonCnt).addActionListener(buttonListener);
					ukusButtonList.get(buttonCnt).addMouseListener(buttonList);
					
					// Povecavamo promenljivu koja prati koliko dugmati imamo
					buttonCnt++;
					
					
					
					// Dodajemo dugme na ekran tj. prikazujemo ga u panelu
					dodajDugmeNaEkran();
					
				});
				
				
				
				// Osluskivac za dugme za prodaju
				prodajButton.addActionListener( (ae) -> {
					
					prodajPushed = true;
					
					// Ukloni sve elemente iz liste
					listaPravougaonika.clear();
					
					// Ocisti kanvas
					mestoZaTocenje.repaint();
					try {
						mestoZaTocenje.nitCanvas.join();
					} catch (InterruptedException e) {}
					
					
					
					// Ocisti spisak sastojaka
					System.out.println(sladoledSastojciLabel.getText());	// posalji rezultat na konzolu
					sladoledSastojciLabel.setText("");
					
					mestoZaTocenje.sladoled = new Sladoled(200);
						
					
					// Deaktiviraj dugme
					prodajButton.setEnabled(false);
					
					prodajPushed = false;
					
					
				});
	}
	

	
	private void dodajDugmeNaEkran() {
		
		for(Button b : ukusButtonList) {
			
			if(buttonCnt == 2) {
			// Postavimo ovaj layout da moze da se ceo panel popuni tako da su dva dugmeta jedno ispod drugog
				centerLeftPanel.setLayout(new GridLayout(2, 1));
				centerLeftPanel.add(b);
			}
			if(buttonCnt == 3) {
			// Da se lepo prikaze kad je 3 dugmeta
				centerLeftPanel.setLayout(new GridLayout(0, 2));
				centerLeftPanel.add(b);
			}
			else {
			// Ovde je gridlayout lepo postavljen
				centerLeftPanel.add(b);
			}
			
		}
		
		repaint();
		centerLeftPanel.validate();		// prikazi komponente nakon izmene
		
	}

	MouseListener buttonList = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			
			pritisnutoDugme = (Button)e.getSource();
		    // odavde sam dodao
			
			
			int velicinaCase = mestoZaTocenje.dohvatiSladoled().dohvatiVelicinuCase(); //x
			double procenat = 20* 100.0 / velicinaCase; //x 
			//int visina = (int)(procenat/100 * mestoZaTocenje.getHeight() /2); //x
			//int visina = Math.round(mestoZaTocenje.getHeight() / 10) / 2;
			int visina = (int) Math.round(procenat/100 * mestoZaTocenje.getHeight());	// bilo je int visina = (int) Math.round(procenat/100 * mestoZaTocenje.getHeight() /2 );	ali je to bila greska
			
			
			
			
			nitPressed = new Thread( () ->{
				try {	
					while(!(Thread.interrupted())) {
					
						Thread.sleep(500);
						
						// Dodajes element u listu //x
						
						// Samo jedan u listi - postavi pocetne parametre //x
						if(listaPravougaonika.isEmpty()) { //x
							int x = 0; //x
							int y = mestoZaTocenje.getHeight() - visina; //x
							int width = mestoZaTocenje.getWidth(); //x
							int height = visina; //x
							
							pravougaonik = new Rectangle(x, y, width, height); //x
							PravougaonikBoja pb = new PravougaonikBoja(pravougaonik, pritisnutoDugme.getBackground());
							listaPravougaonika.add(pb); //x
						//	System.out.println("PRITISNUTO JE DUGME " + pritisnutoDugme.getLabel());
							
							
							// Dodavanje sladoleda
							String naziv = new String(pritisnutoDugme.getLabel());
							Color ukusBoja = pritisnutoDugme.getBackground();
							Ukus u = new Ukus(naziv, ukusBoja);
							mestoZaTocenje.sladoled.dodajUkus(u, 20);
							
					//		System.out.println(mestoZaTocenje.sladoled);
							
							
						}
						else { //x
							// Postavi parametre u odnosu na poslednji iz liste
							int x = 0; //x 
							int y = ( (int)(( listaPravougaonika.get(listaPravougaonika.size() - 1) ).vratiPravoug().getY() ) ) - visina; //x
							int width = mestoZaTocenje.getWidth(); //x
							int height = visina; //x
							
							if(listaPravougaonika.size() < 10) {		// FIKSIRANO NA 10 jer toliko staje ako je casa 200ml a uzima se 20ml izmeni ovo
								pravougaonik = new Rectangle(x, y, width, height); //x
								PravougaonikBoja pb = new PravougaonikBoja(pravougaonik, pritisnutoDugme.getBackground());
								listaPravougaonika.add(pb); //x
							//	System.out.println("PRITISNUTO JE DUGME " + pritisnutoDugme.getLabel());
								
								// Dodavanje sladoleda
								String naziv = new String(pritisnutoDugme.getLabel());
								Color ukusBoja = pritisnutoDugme.getBackground();
								Ukus u = new Ukus(naziv, ukusBoja);
								mestoZaTocenje.sladoled.dodajUkus(u, 20);
								
					//			System.out.println(mestoZaTocenje.sladoled);
							}
						}
						
					
						//System.out.println("U nizu je " + listaPravougaonika.size() + " elemenata");
					
						mestoZaTocenje.repaint();
						
						mestoZaTocenje.nitCanvas.join();
						
						String sastojci = mestoZaTocenje.dohvatiSladoled().toString();
						sladoledSastojciLabel.setText(sastojci);
						
						mestoZaTocenje.revalidate();
						
						// Kada popunis casu (namesteno je da se puni po 20ml do ukupno 200ml sto znaci 10 kugli)
						if(listaPravougaonika.size() == 10) {
							prodajButton.setEnabled(true);
							
						}
						
					}
				} catch (InterruptedException e1) {}		
			});
			nitPressed.start();
			 
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
			nitPressed.interrupt();
			try {
				nitPressed.join();
			} catch (InterruptedException e1) {}
			
		}
	};
	
	
/*
	// Osluskivac za ispis na labeli
	ActionListener buttonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof Button) {
				
				// Ovde ubaci funkcionalnost kada se klikne na neko dugme sta se radi
				
				pritisnutoDugme = (Button)e.getSource();
				
				
				
				mestoZaTocenje.repaint();
				mestoZaTocenje.revalidate();
				
				sladoledSastojciLabel.setText("Pritisnuto je " + pritisnutoDugme.getLabel() );	// testiranje da li se upisuje na labelu
				sladoledSastojciLabel.revalidate(); 	// preracunaj velicinu komponente
			}
		}
	};
	*/
	
	
	
	public AparatZaTocenje(Sladoleddzinica owner) {
		
		this.owner = owner;
		
		postaviPanel();
		
	}
}
