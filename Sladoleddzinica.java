package zadatak;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Sladoleddzinica extends Frame {
	
	private AparatZaTocenje aparatZaTocenje;
	
	Panel bottomPanel = new Panel();
	private Label nazivLabel = new Label("Naziv: ");
	TextField nazivTextField = new TextField(10);
	private Label bojaLabel = new Label("Boja: ");
	TextField bojaTextField = new TextField(10);
	Button dodajUkusButton = new Button("Dodaj ukus");
	
	
	
	private void popuniProzor() {
		
		nazivLabel.setFont(new Font("Arial", Font.BOLD, 20));
		bojaLabel.setFont(new Font("Arial", Font.BOLD, 20));
		bottomPanel.setBackground(Color.CYAN);
		bottomPanel.add(nazivLabel);
		bottomPanel.add(nazivTextField);
		bottomPanel.add(bojaLabel);
		bottomPanel.add(bojaTextField);
		bottomPanel.add(dodajUkusButton);
		
		aparatZaTocenje = new AparatZaTocenje(this);
		this.add(aparatZaTocenje, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	
	
	public Sladoleddzinica() {
		
		setBounds(700, 200, 400, 300);
		setResizable(false);
		setTitle("Sladoleddzinica");
		
		popuniProzor();
		
		pack();
		
		setVisible(true);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
	}
	
	public static void main(String[] args) {
		new Sladoleddzinica();
	}

}
