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

/**Classe che ha il compito di creare un pannello che mostri tutte le partite del calendario attuale.
 * Vengono mostrati tutti gli scontri di ogni giornata. 
 * 
 * @author Giorgia Gibellini
 *
 */
public class showGiornate extends MainPanel implements ActionListener
{
		private static final long serialVersionUID = 1L; 
		
		private JButton indietro;
		
		private GridLayout Layout ;
		
		public showGiornate (MainFrame f, JPanel old) {
			
			super(f,old);
			
			int n_day = NuovoCampionato.getCh().getDays().size() ;
			
			if(n_day == 0) {
			
			JLabel Sq_Null = new JLabel("Non ci sono squadre, il calendario non esiste") ;
			
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
			
			for(int i = 0 ; i < n_day ; i++) 
			{	
				
				this.add(new JLabel ("GIORNATA N."+(i+1)));
				this.add(new JLabel (" "));
				//this.add(new JLabel (" "));
				//this.add(new JLabel (" "));
				
				int n_match = NuovoCampionato.getCh().getDays().get(i).getMatchDay().size() ;
				
				for (int j=0; j<n_match; j=j+1)
				{	
					this.add(new JLabel("MATCH N."
										+(j+1)+" "
										+NuovoCampionato.getCh().getDays().get(i).getMatchDay().get(j).getHost().getNameT()
										+" VS "
										+NuovoCampionato.getCh().getDays().get(i).getMatchDay().get(j).getGuest().getNameT())) ;
					this.add(new JLabel (" "));
				
				}
		
			
		
		this.add(indietro);
		this.indietro.addActionListener(this);
		
		this.Layout = new GridLayout(2*(n_day*n_match+(1+n_day)), 3) ;
		this.setLayout(this.Layout);

		
		/**
		 * RICARICO IL PANNELLO
		 */
		this.invalidate();
		this.validate();
		this.repaint();
			}
	}
			}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == (indietro))
			{ 
				this.FrameB.goTo(TestMain.Panel, this);
			}
			
		}

}
