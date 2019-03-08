package seeCalendar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.MainFrame;
import gui.MainPanel;
import gui.NuovoCampionato;
import main.TestMain;

/**Classe che ha il compito di creare un pannello che mostri tutte le partite della gionata richiesta del calendario attuale.
 * Vengono mostrati tutti gli scontri di ogni giornata. 
 * 
 * @author Giorgia Gibellini
 *
 */
public class showGiornata extends MainPanel implements ActionListener
{
	private static final long serialVersionUID = 1L; 
			
	private JButton indietro;
			
	private GridLayout Layout ;
	
	/** Costruttore della classe, i parametri passati sono
	 * 
	 * @param f, frame a cui appartiene e su cui si succedono i vari panel
	 * @param old, panel precedente a cui si può risalire (per eventuali implementazioni diverse)
	 * @param d, numero della giornata richiesta dall'utente prelevato dal JTextField "ngiornata" in @VisualizzaClendario
	 */
	public showGiornata (MainFrame f, JPanel old, int d) 
	{
		super(f,old);
				
		int n_day = NuovoCampionato.getCh().getDays().size() ;
				
		if(n_day == 0 || d>= n_day) {
				
		JLabel Sq_Null = new JLabel("La giornata richiesta non esiste") ;
				
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
					
		indietro = new JButton ("Home");
					
		this.add(new JLabel ("GIORNATA N."+(d)));
		this.add(new JLabel (" "));
		
		/**
		 * Se la giornata richiesta dall'utente è la seconda essa in realtà nell'array è la 1 perchè l'array parte da 0.
		 * Per questo decremento d per accedere all'array.
		 */
		d = d-1 ;
				
		int n_match = NuovoCampionato.getCh().getDays().get(d).getMatchDay().size() ;
					
		for (int j=0; j<n_match; j=j+1)
			{	
				this.add(new JLabel("MATCH N."
									 +(j+1)+" "
									 +NuovoCampionato.getCh().getDays().get(d).getMatchDay().get(j).getHost().getNameT()
									 +" VS "
									 +NuovoCampionato.getCh().getDays().get(d).getMatchDay().get(j).getGuest().getNameT())) ;
				this.add(new JLabel (" "));
					
					}
			
				
			
		this.add(indietro);
		this.indietro.addActionListener(this);
			
		this.Layout = new GridLayout((n_match+2), 1) ;
		this.setLayout(this.Layout);

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



