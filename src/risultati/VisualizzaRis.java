package risultati;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.MainFrame;
import gui.MainPanel;
import gui.NuovoCampionato;
import main.TestMain;

public class VisualizzaRis extends MainPanel implements ActionListener{
	private static final long serialVersionUID = 1L; 
	
	private JButton indietro;
	private Vector<JLabel> Host = null;
	private Vector<JLabel> Guest = null;
	private Vector<JLabel> PH = null;
	private Vector<JLabel> PG = null;
	private int mtch = 0;
	
	private GridLayout Layout ;
	
	public VisualizzaRis (MainFrame f, JPanel old) {
		
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
			int n_day = NuovoCampionato.getCh().getDays().size() ;
			for(int i = 0 ; i < n_day ; i++) 
			{	
				int n_match = NuovoCampionato.getCh().getDays().get(i).getMatchDay().size() ;
				
				for (int j=0; j<n_match; j=j+1)
				{	String H = NuovoCampionato.getCh().getDays().get(i).getMatchDay().get(j).getHost().getNameT() ;
					String G = NuovoCampionato.getCh().getDays().get(i).getMatchDay().get(j).getGuest().getNameT();
					int pg = NuovoCampionato.getCh().getDays().get(i).getMatchDay().get(j).getPMathHost();
					int ph = NuovoCampionato.getCh().getDays().get(i).getMatchDay().get(j).getPMatchGuest();
					
					if (!(pg == 0 && ph == 0))
						{ this.add(new JLabel( H+" "+ph+" - "+G+" "+pg ));			
					      this.add(new JLabel (" ")); 
					      mtch++;
					      }
				
				}
			
		
		indietro = new JButton ("Home");
		
	
			}
			}
		
	this.add(indietro);
	this.indietro.addActionListener(this);
	
	this.Layout = new GridLayout(mtch+1, 2, 10,10) ;
	this.setLayout(this.Layout);

	
	/**
	 * RICARICO IL PANNELLO
	 */
	this.invalidate();
	this.validate();
	this.repaint();
		}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == (indietro))
		{ 
			this.FrameB.goTo(TestMain.Panel, this);
		}
		
	}
}

