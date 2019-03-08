package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import gui.MainFrame ;
import gui.AggiungiSq ;
import gui.EliminaSq;
import gui.ModificaSq; 
import gui.VisualizzaSquadre;
import gui.NuovoCampionato;
import main.TestMain;
import risultati.*;
import teampk.*;
import gestione.* ;

public class GestisciCampionato extends MainPanel implements ActionListener{
	private static final long serialVersionUID = 1L;  
	
	private JRadioButton  aggsq, modsq, delsq, savesq, visquadre, creacal, savecal, caricacal, 
						  visualcal, nuovoris, resetris, salvarisfile, visualclassifica, visualris;
	private JButton indietro;
	private Berger berger ;
	private VisualizzaCalendario vc ;
	private VisualizzaSquadre vs ;
	private JFileChooser fileChooser;
	private File output;

	private File input;

	private FileInputStream fis;

	private ObjectInputStream ois;

	private Calendar temp;

	
	private static EliminaSq ds ;
	private static ModificaSq ms ;
	private static AggiungiSq Aggiungi_GC ;
	private static ModRes nr ;
	private static seeClassifica v ;
	private static VisualizzaRis visri ;
		
		
		public GestisciCampionato (MainFrame f, JPanel old)
		{
			super(f,old);
			
			this.setLayout(new GridLayout(19,0));

			aggsq = new JRadioButton ("Aggiungi squadra");
			modsq = new JRadioButton ("Modifica squadra");
			delsq = new JRadioButton ("Elimina squadra");
			savesq = new JRadioButton("Salva squadre");
			visquadre = new JRadioButton("Visualizza tutte le squadre");
			creacal = new JRadioButton ("Genera calendario");
			savecal = new JRadioButton ("Salva calendario su file");
			caricacal = new JRadioButton ("Carica calendario");
			visualcal = new JRadioButton ("Visualizza calendario");
			nuovoris = new JRadioButton ("Nuovo risultato di una partita");
			resetris = new JRadioButton ("Azzera tutti i risultati");
			salvarisfile = new JRadioButton ("Salva i risultati su file");
			visualris = new JRadioButton ("Visualizza risultati");
			visualclassifica = new JRadioButton("Visualizza classifica");
			indietro = new JButton ("Home");
			
			this.add(new JLabel ("Squadre "));
			this.add(aggsq);
			this.add(modsq);
			this.add(delsq); 
			this.add(savesq);
			this.add(visquadre);
			this.add(new JLabel ("Calendario "));
			this.add(creacal);
			this.add(savecal);
			this.add(caricacal);
			this.add(visualcal);
			this.add(new JLabel ("Risultati e classifica "));
			this.add(nuovoris);
			this.add(resetris);
			this.add(visualris);
			this.add(salvarisfile);
			this.add(visualclassifica);
			this.add(new JLabel (" "));
			this.add(indietro);
			

			ButtonGroup bgroup = new ButtonGroup ();	
			  bgroup.add(aggsq);
			  bgroup.add(modsq);
			  bgroup.add(delsq); 
			  bgroup.add(savesq);
			  bgroup.add(visquadre);
			  bgroup.add(creacal);
			  bgroup.add(savecal);
			  bgroup.add(caricacal);
			  bgroup.add(visualcal);
			  bgroup.add(nuovoris);
			  bgroup.add(resetris);
			  bgroup.add(salvarisfile);
			  bgroup.add(visualclassifica);
			  
			  

			this.aggsq.addActionListener(this);
			this.modsq.addActionListener(this);
			this.delsq.addActionListener(this);
			this.savesq.addActionListener(this);
			this.visquadre.addActionListener(this);
			this.creacal.addActionListener(this);
			this.savecal.addActionListener(this);
			this.caricacal.addActionListener(this);
			this.visualcal.addActionListener(this);	
			this.nuovoris.addActionListener(this);
			this.resetris.addActionListener(this);
			this.visualris.addActionListener(this);
			this.salvarisfile.addActionListener(this);
			this.visualclassifica.addActionListener(this);
			this.indietro.addActionListener(this);

		}
		
		
		
		
		
		public void actionPerformed(ActionEvent e)	{
			
			if (aggsq.isSelected())
			{  
				if (NuovoCampionato.getAgg()!=null)
					this.FrameB.goTo(NuovoCampionato.getAgg(),this);
				else
					{	Aggiungi_GC = new AggiungiSq(this.FrameB, this) ;
						this.FrameB.goTo(Aggiungi_GC, this); }
			}
			
			if (modsq.isSelected())
			{  
				ms = new ModificaSq (this.FrameB, this);
				this.FrameB.goTo(ms,this);
			}
			
			if (delsq.isSelected())
			{  
				ds = new EliminaSq (this.FrameB, this);
				this.FrameB.goTo(ds, this);
			}
			
			if (savesq.isSelected())
			{  
				for (int i=0; i<NuovoCampionato.getCh().getTeams().size() ; i=i+1)
					if(!salvaSquadra(i))
						JOptionPane.showMessageDialog(this.FrameB, " Errore salvataggio squadra "," ", JOptionPane.ERROR_MESSAGE);
						
				
			}
			
			if (visquadre.isSelected())
			{  
				vs = new VisualizzaSquadre (this.FrameB,this);
				this.FrameB.goTo(vs, this);
			}
			
			if (creacal.isSelected())
			{   if (NuovoCampionato.getCh().getTeams().size()<3 || NuovoCampionato.getCh().getTeams().size()%2 != 0)
				{ 
					JOptionPane.showMessageDialog(this.FrameB, " Il numero di squadre deve essere pari e maggiore di 2 "," ", JOptionPane.ERROR_MESSAGE);
				}
			
				else
				{ 
					berger = new Berger();
					JOptionPane.showMessageDialog(this, " Il calendario è stato generato");
					//JOptionPane.showMessageDialog(this.FrameB, " Il calendario è stato generato "," ", JOptionPane.PLAIN_MESSAGE);
					int n_day = NuovoCampionato.getCh().getDays().size() ;
					for (int i=0; i<(n_day); i=i+1)
						{ 
							System.out.println("Giornata "+(i+1));
							int n_match = NuovoCampionato.getCh().getDays().get(i).getMatchDay().size() ;
							for (int j=0; j<n_match; j=j+1)
							{	
								System.out.println("Match numero "+(j+1));
								System.out.println(NuovoCampionato.getCh().getDays().get(i).getMatchDay().get(j).getHost().getNameT());
								System.out.println(" VS ");
								System.out.println(NuovoCampionato.getCh().getDays().get(i).getMatchDay().get(j).getGuest().getNameT());
							}
						}
				}
			}
			
			if (savecal.isSelected())
			{  
					Calendar cal = new Calendar (NuovoCampionato.getCh().getDays());
					if(!salvaCal(cal))
						JOptionPane.showMessageDialog(this.FrameB, " Errore salvataggio calendario  "," ", JOptionPane.ERROR_MESSAGE);
						
			}
			
			if (caricacal.isSelected())
			{  
				if(!caricaCal())
					JOptionPane.showMessageDialog(this.FrameB, " Errore salvataggio calendario  "," ", JOptionPane.ERROR_MESSAGE);
				
				// STAMPA DI VERIFICA, DA ELIMINARE NEL DEFINITIVO
				int n_day = NuovoCampionato.getCh().getDays().size() ;
				for (int i=0; i<(n_day); i=i+1)
					{ 
						System.out.println("Giornata "+(i+1));
						int n_match = NuovoCampionato.getCh().getDays().get(i).getMatchDay().size() ;
						for (int j=0; j<n_match; j=j+1)
						{	
							System.out.println("Match numero "+(j+1));
							System.out.println(NuovoCampionato.getCh().getDays().get(i).getMatchDay().get(j).getHost().getNameT());
							System.out.println(" VS ");
							System.out.println(NuovoCampionato.getCh().getDays().get(i).getMatchDay().get(j).getGuest().getNameT());
						}
					}
				}
				//CaricaCalendario carica = new CaricaCalendario (this.FrameB, this) ;
			

			
			if (visualcal.isSelected())
			{  
				vc = new VisualizzaCalendario (this.FrameB, this);
				this.FrameB.goTo(vc,this);
			}
			
			if (nuovoris.isSelected())
			{  
				nr = new ModRes (this.FrameB, this);
				this.FrameB.goTo(nr,this);
			}
			
			if (resetris.isSelected())
			{   
				if (NuovoCampionato.getCh().resetMatches())
					JOptionPane.showMessageDialog(this, "Tutti i risultati dei match sono stati azzerati");
				
				//STAMPA DI CONTROLLO DEI RISULTATI, DA ELIMINARE NELLA VERSIONE DEFINITIVA
				int n_day = NuovoCampionato.getCh().getDays().size() ;
				
				for(int l = 0 ; l < n_day ; l++) 
				{	int n_match = NuovoCampionato.getCh().getDays().get(l).getMatchDay().size() ;
					for (int j=0; j<n_match; j=j+1)
					{	
						System.out.println(NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getHost().getNameT()+
						NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getPMathHost()+
						NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getGuest().getNameT()+
						NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getPMatchGuest());
							
					}
				
				}
				
			}
			
			if (visualris.isSelected())
			{  
				visri = new VisualizzaRis (this.FrameB, this);
				this.FrameB.goTo(visri,this);
			}
			
			if (salvarisfile.isSelected())
			{  
				Calendar cal = new Calendar (NuovoCampionato.getCh().getDays());
				if(!salvaCal(cal))
					JOptionPane.showMessageDialog(this.FrameB, " Errore salvataggio risultati  "," ", JOptionPane.ERROR_MESSAGE);
					
			}
			
			
			
			if (visualclassifica.isSelected())
			{  
				v = new seeClassifica (this.FrameB, this);
				this.FrameB.goTo(v,this);
			}
			
			if (e.getSource() == (indietro))
			{ 
				this.FrameB.goTo(TestMain.Panel, this);
			}
			
			
			
		}
		

		/**
		 * Funzione che permette all'utente di salvare sul FileSystem una squadra che potra' caricare in seguito in un altro campionato(dello stesso sport).
		 * @param index indice della squadra a salvare.
		 * @return	vero o false in base alla riuscita della funzione.
		 */
		@SuppressWarnings({ "resource", "static-access" })
		public boolean salvaSquadra(int idx) {
			
			this.fileChooser = new JFileChooser();
			int n = this.fileChooser.showOpenDialog(this.FrameB);
			
			if(n == this.fileChooser.APPROVE_OPTION) {
				this.output = this.fileChooser.getSelectedFile() ;
			}
			else {
				System.out.println("Errore selezione file");
				return false ;
			}
	 
			FileOutputStream fos = null;
			ObjectOutputStream oos = null ;
			
			try 
			{
				fos = new FileOutputStream(this.output);
			} catch (FileNotFoundException e1) 
			{
				e1.printStackTrace();
				return false ;
			}
			try 
			{
				oos = new ObjectOutputStream(fos) ;
				
			} catch (IOException e) 
			{
				e.printStackTrace();
				return false ;
			}
			
			try 
			{   
				oos.writeObject(NuovoCampionato.getCh().getTeams().get(idx));
			} catch (IOException e) 
			{
				e.printStackTrace();
				return false ;
			}
					
			JOptionPane.showMessageDialog(this, "Squadra salvata con successo");
			return true;
		}
		
		/**
		 * Funzione che permette all'utente di salvare sul FileSystem una squadra che potra' caricare in seguito in un altro campionato(dello stesso sport).
		 * @param index indice della squadra a salvare.
		 * @return	vero o false in base alla riuscita della funzione.
		 */
		@SuppressWarnings({ "resource", "static-access" })
		public boolean salvaCal(Calendar cal){
			
			this.fileChooser = new JFileChooser();
			int n = this.fileChooser.showOpenDialog(this.FrameB);
			
			if(n == this.fileChooser.APPROVE_OPTION) {
				this.output = this.fileChooser.getSelectedFile() ;
			}
			else {
				System.out.println("Errore selezione file");
				return false ;
			}
	 
			FileOutputStream fos = null;
			ObjectOutputStream oos = null ;
			
			try 
			{
				fos = new FileOutputStream(this.output);
			} catch (FileNotFoundException e1) 
			{
				e1.printStackTrace();
				return false ;
			}
			try 
			{
				oos = new ObjectOutputStream(fos) ;
				
			} catch (IOException e) 
			{
				e.printStackTrace();
				return false ;
			}
			
			try 
			{   
				oos.writeObject(cal);
			} catch (IOException e) 
			{
				e.printStackTrace();
				return false ;
			}
					
			JOptionPane.showMessageDialog(this, "Giornata di campionato salvata con successo");
			return true;
		}
		
		
		@SuppressWarnings("static-access")
		public boolean caricaCal ()
		{ 
			this.fileChooser = new JFileChooser() ;
			int n = this.fileChooser.showOpenDialog(this.FrameB);
			
			
			if(n == this.fileChooser.APPROVE_OPTION) 
			{
				this.input = this.fileChooser.getSelectedFile() ;
			}
			else 
			{
				System.out.println("Errore selezione file");
				return false ;
			}
			
			
			try 
			{
				this.fis = new FileInputStream(this.input);
			} 
			catch (FileNotFoundException e) 
			{
				JOptionPane.showMessageDialog(this.FrameB, "Errore nell'importazione della squadra", "Errore import squadra", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return false ;
			}
			try 
			{
				this.ois = new ObjectInputStream(fis);
			} 
			catch (IOException e) 
			{
				JOptionPane.showMessageDialog(this.FrameB, "Errore nell'importazione della squadra", "Errore import squadra", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return false ;
			}
			
			try 
			{
				this.temp = (Calendar) ois.readObject() ;
			} 
			catch (ClassNotFoundException | IOException e) 
			{
				JOptionPane.showMessageDialog(this.FrameB, "Errore nell'importazione della squadra", "Errore import squadra", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return false;
			}
			
			NuovoCampionato.getCh().addCal(temp) ;
			return true;
			
			
		} 
		
		
		}


