package gui;

import javax.swing.* ;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainFrame;
import gui.MainPanel;
import gui.GestisciCampionato;
import gui.NuovoCampionato;
import gestione.Championship; 

/** Panel di inizio del programma.
 *  Mostra il logo del porgramma ASC nel centro e le tre opzioni
 *  di scelta.
 *  Le opzioni sono :
 *  Nuovo Campionato di classe {@link NuovoCampionato} apre il panel che permette di 
 *  creare un nuovo {@link Championship} sia aggiungendo le squadre da file che aggiungendole
 *  manualmente.
 *  Gestisci campionato {@link GestisciCampionato} apre il panel per compiere tutte le rimanenti
 *  opzioni su un campionato e tutto ciò che contiene.
 *  
 *  @author Giorgia Gibellini
 *
 */
public class PageHome extends MainPanel implements ActionListener{
	private static final long serialVersionUID = 1L; 
	
	private JLabel labelsfondo ;
	private JButton b1 ;
	private JButton b2 ;
	/* String che contiene il percorso assoluto dell'immagine di sfondo. 
	 * Perchè non gli va bene ? */
	private String path = "\\Users\\utente\\Desktop\\ASC3\\IMG\\ASC.jpg" ;
	
	private NuovoCampionato NuovoC ;
	private GestisciCampionato GestisciC ;
	
	public PageHome(MainFrame f)
    { super (f,null);  
      
      this.setLayout(new GridLayout(2,3));
	 
	  ImageIcon jpg = new ImageIcon(path);
	  this.labelsfondo = new JLabel(jpg);
	  this.b1 = new JButton ("Nuovo campionato");
	  this.b2 = new JButton ("Gestisci campionato");

	  this.add(new JLabel (" "));
	  this.add(labelsfondo);
	  this.add(new JLabel (" "));
	  this.add(this.b1);
	  this.add(new JLabel (" "));
	  this.add(this.b2);
  
	  this.b1.addActionListener(this);
	  this.b2.addActionListener(this);
	   
	  this.setBackground(new java.awt.Color(189, 48, 57));
	  
	}
			
		
	public void actionPerformed(ActionEvent e)	{
		
		if (e.getSource()==this.b1)
		{ 
		   this.NuovoC = new NuovoCampionato(this.FrameB, this) ;
		   this.FrameB.goTo(NuovoC,this);
		}
		
		if(e.getSource() == this.b2) 
		{
			if (NuovoCampionato.getCh() == null)
				JOptionPane.showMessageDialog(this.FrameB, "Per accedere a queste funzionalità bisogna prima creare un campionato","Errore", JOptionPane.ERROR_MESSAGE);
			else
				{ this.GestisciC = new GestisciCampionato(this.FrameB, this) ;
				  this.FrameB.goTo(GestisciC, this);
				}
		}
		
	}
	
	

}

