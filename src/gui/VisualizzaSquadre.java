package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.TestMain;

public class VisualizzaSquadre extends MainPanel implements ActionListener{
	private static final long serialVersionUID = 1L; 
	
	private JButton indietro;
	private Vector<JLabel> Loghi = null;
	private Vector<JLabel> Nomi = null;
	private Vector<JLabel> Citta = null;
	//private Vector<JLabel> id = null;
	
	private GridLayout Layout ;
	
	public VisualizzaSquadre (MainFrame f, JPanel old) {
		
		super(f,old);
		
		int n = NuovoCampionato.getCh().getTeams().size();
		
		//funziona, aggiungere tasto indietro e migliorare grafica
		if(n == 0) {
		
		JLabel Sq_Null = new JLabel("Non ci sono squadre") ;
		
		indietro = new JButton ("Home");
		
		this.add(Sq_Null) ;
		this.add(indietro);
		this.invalidate();
		this.validate();
		this.repaint();
		
		this.indietro.addActionListener(this);
		
		return ;
					}
	
		else {
			
		Loghi = new Vector<JLabel>() ;
		Nomi = new Vector<JLabel>() ;
		Citta = new Vector<JLabel>() ;
		indietro = new JButton ("Home");
		
		for(int i = 0 ; i < n ; i++) {
		
		this.Loghi.add(new JLabel());
		this.Loghi.lastElement().setIcon(NuovoCampionato.getCh().getTeams().elementAt(i).getImg());
		this.Nomi.add(new JLabel(NuovoCampionato.getCh().getTeams().elementAt(i).getNameT())) ;
		this.Citta.add(new JLabel(NuovoCampionato.getCh().getTeams().elementAt(i).getCity())) ;
		//int cod = NuovoCampionato.getCh().getTeams().elementAt(i).getCod();
		//this.id.add(new JLabel(Integer.toString(cod))) ;	
	
	}
	
	for(int i = 0 ; i < n ; i++)
	{
	this.add(Loghi.elementAt(i)) ;
	this.add(Nomi.elementAt(i)) ;
	this.add(Citta.elementAt(i)) ;
	
	//this.add(id.elementAt(i)) ;
	}
	
	this.add(indietro);
	this.indietro.addActionListener(this);
	
	this.Layout = new GridLayout(n+1, 2, 10,10) ;
	this.setLayout(this.Layout);

	
	/**
	 * RICARICO IL PANNELLO
	 */
	this.invalidate();
	this.validate();
	this.repaint();
		}
}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == (indietro))
		{ 
			this.FrameB.goTo(TestMain.Panel, this);
		}
		
	}
}
