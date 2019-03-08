package risultati;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.MainFrame;
import gui.MainPanel;
import gui.NuovoCampionato;
import main.TestMain;

import javax.swing.JButton;

public class ModRes extends MainPanel implements ActionListener
{
	    //Controllare la serlialversion UID
		private static final long serialVersionUID = 1L;
		
		private JTextField n_host, n_guest, p_host, p_guest;
		private JButton indietro, ok;

		public ModRes (MainFrame f, JPanel old)
		{ 
			super(f,old);
		    
		    this.setLayout(new GridLayout(8,2));
		   
			this.n_host = new JTextField("NOME ",30);
			this.n_host.setEditable(true); 
			this.p_host = new JTextField("PUNTI",3);
			this.p_host.setEditable(true); 
			this.n_guest = new JTextField("NOME",30);
			this.n_guest.setEditable(true);
			this.p_guest = new JTextField("PUNTI",3);
			this.p_guest.setEditable(true);
			ok = new JButton("OK");
			indietro = new JButton("Home");
		    
			
			this.add(new JLabel (" NUOVO RISULTATO - MODIFICA RISULTATO"));
		    this.add(new JLabel (" ")); 
		    this.add(new JLabel (" Squadra di casa : "));
		    this.add(new JLabel (" "));
		    this.add(n_host);
		    this.add(p_host);
		    this.add(new JLabel (" Squadra ospite : "));
		    this.add(new JLabel (" "));
		    this.add(n_guest);
		    this.add(p_guest);
		    this.add(new JLabel (" "));
		    this.add(new JLabel (" "));
		    this.add(new JLabel (" "));
		    this.add(new JLabel (" "));
		    this.add(ok);
		    this.add(indietro); 

		    n_host.addActionListener(this);
		    p_host.addActionListener(this);
		    n_guest.addActionListener(this);
		    p_guest.addActionListener(this);
		    ok.addActionListener(this);
		    indietro.addActionListener(this);
		    
		}

		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == (ok))
			{ 	
				this.setMatch();
			}
		
			if (e.getSource() == (indietro))
			{ 
				this.FrameB.goTo(TestMain.Panel, this);
			}
			
			
		}
		
		public boolean setMatch ()
		{
			int s = NuovoCampionato.getCh().getSportCh();
			String h = this.n_host.getText();
			String g = this.n_guest.getText();
			String ph = this.p_host.getText().replaceAll("([a-z])", "");
			String pg = this.p_guest.getText().replaceAll("([a-z])", "");
			int hp = Integer.parseInt(ph);
			int gp = Integer.parseInt(pg);
			
			if (s == 0 && hp == gp)			//basket
			{
				JOptionPane.showMessageDialog(this.FrameB, "Le partite di basket non ammettono pareggi.","Errore punteggio", JOptionPane.ERROR_MESSAGE);
				return false ;
			}
			
			if (s == 0 && ( hp>200 || gp>200) )
			{   
				JOptionPane.showMessageDialog(this.FrameB, "Il massimo di punti realizzati in una partita di basket è stato 186 nel match NBA Pistons VS Denver del 1983, è veramente poco probabile farne di più..","Errore punteggio", JOptionPane.ERROR_MESSAGE);
				return false ;
			}
			
			if (s == 1 && (hp>149 || gp>149) )					//calcio
			{
				JOptionPane.showMessageDialog(this.FrameB, "Il massimo di punti realizzati in una partita di calcio è stato 149 nel match-protesta AS Adema VS SO De l'Emyme del 2002, è veramente poco probabile farne di più, anche perchè erano tutti autogol..","Errore punteggio", JOptionPane.ERROR_MESSAGE);
				return false ;
			}
			
			if (s == 1 && (hp>149 || gp>149) )					//calcio
			{
				JOptionPane.showMessageDialog(this.FrameB, "Il massimo di punti realizzati in una partita di rugby è stato 142 nel match dei mondiali Australia VS Namibia del 2003, è veramente poco probabile farne di più..","Errore punteggio", JOptionPane.ERROR_MESSAGE);
				return false ;
			}
			
			if (NuovoCampionato.getCh().findObj(h) == -1)
			{
				JOptionPane.showMessageDialog(this.FrameB, "Non esiste nessuna squadra di casa con questo nome ","Squadra non trovata", JOptionPane.ERROR_MESSAGE);
				return false ;
			}
			
			if (NuovoCampionato.getCh().findObj(g) == -1)
			{
				JOptionPane.showMessageDialog(this.FrameB, "Non esiste nessuna squadra ospite con questo nome ","Squadra non trovata", JOptionPane.ERROR_MESSAGE);
				return false ;
			}
			
			if ( NuovoCampionato.getCh().setXMatch(h, g, hp, gp) )
				JOptionPane.showMessageDialog(this, "RISULTATO AGGIORNATO");
			
			//STAMPA DI CONTROLLO, DA ELIMINARE NELLA VERSIONE DEFINITIVA
			System.out.println("RISULTATO MATCH SEGNALATO :");
			int n_day = NuovoCampionato.getCh().getDays().size() ;
			for(int l = 0 ; l < n_day ; l++) 
			{	int n_match = NuovoCampionato.getCh().getDays().get(l).getMatchDay().size() ;
				for (int j=0; j<n_match; j=j+1)
				{	String n_h = NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getHost().getNameT();
					String n_g = NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getGuest().getNameT();
				
					if (h.equals(n_h) && g.equals(n_g))
					{ 	System.out.println(NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getHost().getNameT()+
							NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getPMathHost()+
							NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getGuest().getNameT()+
							NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getPMatchGuest());
						
					}
			
				}
			}
			return true ;
		}
		
		
		
		
		

}
