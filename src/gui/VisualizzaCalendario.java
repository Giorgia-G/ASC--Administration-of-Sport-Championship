package gui;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.TestMain;
import seeCalendar.*;

import javax.swing.JButton;

public class VisualizzaCalendario extends MainPanel implements ActionListener{
	private static final long serialVersionUID = 1L; 
	
	private JRadioButton giornate ;
	private JRadioButton giornata ;
	private JRadioButton squadra ;
	private JButton visualizza ;
	private JButton indietro ;
	private JTextField ngiornata ;
	private JTextField nome_squadra ;
	
	private showGiornate sds ;
	private showGiornata sd ;
	private showSquadra st ;
	
	public VisualizzaCalendario (MainFrame f, JPanel old)
	{
		super (f,old);
		
		this.setLayout(new GridLayout(6,2));

		giornate = new JRadioButton ("Visualizza tutte le giornate di campionato");
		giornata = new JRadioButton ("Visualizza una specifica giornata di campionato");
		squadra = new JRadioButton ("Visualizza il calendario di una squadra specifica");
		ngiornata = new JTextField (" ",2);
		ngiornata.setText("");
		nome_squadra = new JTextField (" ",30);
		nome_squadra.setText("");
		visualizza = new JButton (" Visualizza ");
		indietro = new JButton (" HOME ");
		
		this.add(new JLabel (" VISUALIZZA CALENDARIO "));
		this.add(new JLabel ("  "));
		this.add(giornate);
		this.add(new JLabel ("  "));
		this.add(giornata);
		this.add(ngiornata);
		this.add(squadra);
		this.add(nome_squadra);
		this.add(new JLabel ("  "));
		this.add(new JLabel ("  "));
		this.add(visualizza);
		this.add(indietro);

		this.giornate.addActionListener(this);
		this.giornata.addActionListener(this);
		this.ngiornata.addActionListener(this);
		this.squadra.addActionListener(this);
		this.nome_squadra.addActionListener(this);
		this.visualizza.addActionListener(this);
		this.indietro.addActionListener(this);
		
	}
	
public void actionPerformed(ActionEvent e)	{
		

		if (e.getSource() == visualizza)
		{   
			if (giornate.isSelected())
			{ sds = new showGiornate (this.FrameB, this);
			  this.FrameB.goTo(sds,this);
			}
			if (giornata.isSelected())
			{	
				String s = ngiornata.getText().replaceAll("([a-z])", "") ;
				int d = Integer.parseInt(s);
				if (d<0 || d>(NuovoCampionato.getCh().getTeams().size()*2-2))
					JOptionPane.showMessageDialog(this.FrameB, "Numero della giornata non esistente","Errore", JOptionPane.ERROR_MESSAGE);
					//STAMPA DI CONTROLLO, DA ELIMINARE NELLA VERSIONE DEFINITIVA
				sd = new showGiornata (this.FrameB, this, d);
				this.FrameB.goTo(sd, this);
				
			}
				
			if (squadra.isSelected())
			{
			  st = new showSquadra (this.FrameB, this, this.nome_squadra.getText());
			  this.FrameB.goTo(st, this);
			}
		}
		
		if (e.getSource() == indietro)
		{ 
			this.FrameB.goTo(TestMain.Panel, this);
		}

	
	
}


}