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

public class showSquadra extends MainPanel implements ActionListener
{
	private static final long serialVersionUID = 1L; 
			
	private JButton indietro;
	private GridLayout Layout ;
	
	/** Costruttore della classe, i parametri passati sono
	 * 
	 * @param f, frame a cui appartiene e su cui si succedono i vari panel
	 * @param old, panel precedente a cui si può risalire (per eventuali implementazioni diverse)
	 * @param sq, squadra di cui si vogliono cercare le partite
	 */
	public showSquadra (MainFrame f, JPanel old, String sq) 
	{
		super(f,old);
		
		int i = NuovoCampionato.getCh().findObj(sq) ;
		int n_day = NuovoCampionato.getCh().getDays().size() ;
				
	if(n_day == 0 || i>NuovoCampionato.getCh().getTeams().size() ) 
	{		
		JLabel Sq_Null = new JLabel("Non è possibile mostrare le partite della squadra") ;
				
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
					
		this.add(new JLabel ("CALENDARIO DELLA SQUADRA : "+(sq)));
		this.add(new JLabel (" "));
		
		for(int l = 0 ; l < n_day ; l++) 
		{	
			
			this.add(new JLabel ("GIORNATA N."+(l+1)));
			this.add(new JLabel (" "));
			
			int n_match = NuovoCampionato.getCh().getDays().get(l).getMatchDay().size() ;
			
			for (int j=0; j<n_match; j=j+1)
			{	String n_h = NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getHost().getNameT();
				String n_g = NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getGuest().getNameT();
				System.out.println(" Sono dentro il for dei match, prima delle stampe ");
				
				if (sq.equals(n_h) || sq.equals(n_g))
				{ 
				 System.out.println(" Sono dentro l'if delle stampe ");
				 this.add(new JLabel( (n_h)+" VS "+(n_g))) ;
				 this.add(new JLabel (" "));
				}
			
			}
			
		}
				
			
		this.add(indietro);
		this.indietro.addActionListener(this);
			
		this.Layout = new GridLayout((n_day*2+2), 1) ;
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

	




