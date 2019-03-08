package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Collections;

import main.TestMain;
import teampk.Team;

public class seeClassifica extends MainPanel implements ActionListener{
	private static final long serialVersionUID = 1L; 
	
	private JButton indietro;
	private Vector<JLabel> NomeSq = null;
	private Vector<JLabel> Punti = null;
	//private Vector<JLabel> Pareggi = null;
	//private Vector<JLabel> Sconfitte = null;
	//private Vector<JLabel> Vittorie = null;
	
	private GridLayout Layout ;
	@SuppressWarnings("rawtypes")
	private java.util.ArrayList Sq_Ord ;
	
	public seeClassifica (MainFrame f, JPanel old) {
		
		super(f,old);
		
		int n = NuovoCampionato.getCh().getTeams().size();
		
		//funziona, aggiungere tasto indietro e migliorare grafica
		if(n == 0) {
		
		JLabel Sq_Null = new JLabel("Non ci sono squadre, classifica non disponibile") ;
		
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

		
		this.makePoints();
		this.makeClassifica();
		ArrayList<Team> SqOrd = new ArrayList<Team>();
		SqOrd = this.ordina();

		
		NomeSq = new Vector<JLabel>() ;
		Punti = new Vector<JLabel>() ;
		indietro = new JButton ("Home");
		
		for(int i = n -1 ; i >= 0 ; i--) 
		{
		this.NomeSq.add(new JLabel(SqOrd.get(i).getNameT())) ;
		int Pun = (SqOrd.get(i)).getPoints() ;
		this.Punti.add(new JLabel(Integer.toString(Pun))) ;
		//int cod = NuovoCampionato.getCh().getTeams().elementAt(i).getCod();
		//this.id.add(new JLabel(Integer.toString(cod))) ;	
		}
	

		for(int j = 0 ; j < n ; j++)
		{
		this.add(NomeSq.elementAt(j)) ;
		this.add(Punti.elementAt(j)) ;
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
	
	/** Metodo che calcola la classifica del campionato. 
	 *  Grazie al polimorfismo implementato la classifica viene calcolata diversamente in base allo sport.
	 */
	public void makePoints ()
	{
		/** Porto i punti di tutte le squadre a 0 */
		for(int l = 0 ; l < NuovoCampionato.getCh().getTeams().size() ; l++) 
		{	
			NuovoCampionato.getCh().getTeams().get(l).resetPoints();
			NuovoCampionato.getCh().getTeams().get(l).resetWin();
			NuovoCampionato.getCh().getTeams().get(l).resetLose();
			NuovoCampionato.getCh().getTeams().get(l).resetDraw();
		}
		int n_day = NuovoCampionato.getCh().getDays().size() ;
		
		for(int l = 0 ; l < n_day ; l++) 
		{	int n_match = NuovoCampionato.getCh().getDays().get(l).getMatchDay().size() ;
			for (int j=0; j<n_match; j=j+1)
			{	
				String h = NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getHost().getNameT() ;
				String g = NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getGuest().getNameT() ;
				
				int ph = NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getPMathHost() ;
				int pg = NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getPMatchGuest() ;
				
				int idxh = NuovoCampionato.getCh().findObj(h);
				int idxg = NuovoCampionato.getCh().findObj(g);
				
				if ( ph > pg )
				{ 	
					NuovoCampionato.getCh().getTeams().get(idxh).addWin();
					NuovoCampionato.getCh().getTeams().get(idxg).addLose();
				}
				
				if  (ph < pg )
				{ 	
					NuovoCampionato.getCh().getTeams().get(idxg).addWin();
					NuovoCampionato.getCh().getTeams().get(idxh).addLose();
				}
				
				if ( ph == pg )
				{ 
					NuovoCampionato.getCh().getTeams().get(idxg).addDraw();
					NuovoCampionato.getCh().getTeams().get(idxh).addDraw();
				}
				
					
			}
		
		}
		
			
	}
	
	
	public void makeClassifica ()
	{
		int n_day = NuovoCampionato.getCh().getDays().size() ;
		for(int l = 0 ; l < n_day ; l++) 
		{	int n_match = NuovoCampionato.getCh().getDays().get(l).getMatchDay().size() ;
			for (int j=0; j<n_match; j=j+1)
			{	String h = NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getHost().getNameT() ;
				String g = NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getGuest().getNameT() ;
				int idxh = NuovoCampionato.getCh().findObj(h);
				int idxg = NuovoCampionato.getCh().findObj(g);
				
				NuovoCampionato.getCh().getTeams().get(idxg).calcPoints();
				NuovoCampionato.getCh().getTeams().get(idxh).calcPoints();
			}
	
		}
	}
	

	
	@SuppressWarnings("unchecked")
	private java.util.ArrayList<Team> ordina() 
	{
		this.Sq_Ord  = new java.util.ArrayList<Team>() ;
		for(int x = 0 ; x < NuovoCampionato.getCh().getTeams().size() ; x++)
			Sq_Ord.add(NuovoCampionato.getCh().getTeams().elementAt(x)) ;
			
			for(int i = 1; i < Sq_Ord.size(); i++) 
			{	int x = i;
		        int j = i-1;
		        for(; j >= 0; j--) {
		             if(((Team) Sq_Ord.get(j)).getPoints() > ((Team) Sq_Ord.get(x)).getPoints()) {
		                  Team k = (Team) Sq_Ord.get(x);
		                  Collections.swap(Sq_Ord, x, j);
		                  Sq_Ord.set(j, k) ;
		                  x = j; 
		               } else break;
		                             
		           }
		        }
			return Sq_Ord ;
		}
}
