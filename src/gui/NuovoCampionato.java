package gui;

import java.awt.GridLayout;
import java.io.File ;
import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.io.IOException ;
import java.io.ObjectInputStream ;
import java.awt.event.ActionListener;

import javax.swing.* ;
import java.awt.event.ActionEvent;
import gui.MainFrame;
import main.TestMain;
import gestione.Championship;
import teampk.Team;

public class NuovoCampionato extends MainPanel implements ActionListener
{
		private static final long serialVersionUID = 1L; 
		
		private JLabel l1,l2 ;
		private JTextField 	NomeC ;
		private JRadioButton basket, calcio, rugby ;
		private JButton CaricaSq, AggiungiSq, Salva, Indietro ;
		private JFileChooser fileChooser ;
		private File input ;
		private FileInputStream fis ;
		private ObjectInputStream ois ;
		private Team temp ;
		String name ;
		/** s indica lo sport del campionato :
		 * 0 = basket 
		 * 1 = calcio
		 * 2 = rugby
		 *  type indica il tipo di caricamento da fare :
		 * 1 = carica campionato
		 * 2 = carica squadre
		 * 3 = carica calendario
		 */
		private int s;
		private static Championship Champ ;
		//Provo a mettere il panel static per riusarlo in gestisci campioanti a vedere se non 
		//sclera più
		private static AggiungiSq Aggiungi = null ;
		
		public NuovoCampionato(MainFrame f, JPanel old)
	    { 
		  super(f,old);  
	    
	      this.setLayout(new GridLayout(8,3));
	      
	      l1 = new JLabel ("Nome campionato :")	;
		  this.NomeC = new JTextField("Inserire il nome del campionato",30);
		  this.NomeC.setEditable(true); 
		  NomeC.setText("");
	      l2 = new JLabel ("Sport : ") ; 
	      basket = new JRadioButton ("Basket");
		  calcio = new JRadioButton ("Calcio");
		  rugby = new JRadioButton ("Rugby");
		  CaricaSq = new JButton("Carica squadre da file");
		  AggiungiSq = new JButton("Aggiungi squadre manualmente");
		  Salva = new JButton ("Salva");
		  Indietro = new JButton("Indietro");

		  this.add(new JLabel (" "));
		  this.add(new JLabel (" "));
		  this.add(new JLabel (" "));
	      this.add(l1);
		  this.add(NomeC);
		  this.add(new JLabel (" "));
		  this.add(l2);	  
		  this.add(basket);
		  this.add(new JLabel (" "));
		  this.add(new JLabel (" "));
		  this.add(calcio);
		  this.add(new JLabel (" "));
		  this.add(new JLabel (" "));
		  this.add(rugby);
		  this.add(new JLabel (" "));
		  this.add(Salva);
		  this.add(new JLabel (" "));
		  this.add(new JLabel (" "));
		  this.add(new JLabel (" "));
		  this.add(new JLabel (" "));
		  this.add(new JLabel (" "));
		  this.add(CaricaSq);
		  this.add(AggiungiSq);
		  this.add(Indietro);
		  
		  /* Bgroup per raggruppare i vari radio button. Va bene qua? */
		  ButtonGroup bgroup = new ButtonGroup ();	
		  bgroup.add(basket);
		  bgroup.add(calcio);
		  bgroup.add(rugby); 
		  
		  /* ActionListener */
		  this.Salva.addActionListener(this);
		  this.NomeC.addActionListener(this);
		  this.basket.addActionListener(this);
		  this.calcio.addActionListener(this);
		  this.rugby.addActionListener(this);  
		  this.CaricaSq.addActionListener(this);
		  this.AggiungiSq.addActionListener(this);
		  this.Indietro.addActionListener(this);

		  
	    }
		
		public void actionPerformed(ActionEvent e) 
		  { 
			if (e.getSource() == Salva)
				
			{   
			if (basket.isSelected() == true)
			{ s = 0 ;
			  if (this.newChamp(s)) 
				  JOptionPane.showMessageDialog(null,"Campionato di basket creato");  
			}
			if (calcio.isSelected() == true)
		    { s = 1 ;
		      if (this.newChamp(s))
		    	  JOptionPane.showMessageDialog(null,"Campionato di calcio creato");
		    }
			if (rugby.isSelected() == true)
			{ s = 2 ; 
			  if (this.newChamp(s))
				  JOptionPane.showMessageDialog(null,"Campionato di rugby creato");
			}
				
		    }
			
			if (e.getSource() == CaricaSq)
			{ 
				if (this.CaricaSq())
					JOptionPane.showMessageDialog(null,"Caricamento avvenuto");	
			}
			
			if (e.getSource() == AggiungiSq)
			{ 	
				Aggiungi = new AggiungiSq(this.FrameB, this) ;
				this.FrameB.goTo(Aggiungi, this);	
			}
			
			if (e.getSource() == Indietro)
			{ 
				this.FrameB.goTo(TestMain.Panel, this);
			}
			
			}
		
		/** Crea il campionato desiderato, prende in input
		 * @param s che rappresenta lo sport (0,1,2) 
		 * poi ricava dal textfield NomeC il nome del campionato 
		 * e passa entrambi i dati al costruttore di campionati.
		 */
		public boolean newChamp (int s)
		{
			name = this.NomeC.getText();
			if (name.equals(""))
				{ JOptionPane.showMessageDialog(this.FrameB, "Il nome del campionato non puo essere nullo.","Inane error", JOptionPane.ERROR_MESSAGE);
				  return false ;
				}
			else
				{ Champ = new Championship (name, s);
				  return true ;
				}
	
		}
		
		/** Getter per accedere a campionato 
		 * 
		 * @return Championship
		 */
		public static Championship getCh ()
		{ 
			return Champ ;
		}
		
		/** Getter per accedere a Aggiungi, schermata di aggiunta di una squadra 
		 * 
		 * @return AggiungiSq
		 */
		public static AggiungiSq getAgg ()
		{ 
			return Aggiungi ;
		}
		
		@SuppressWarnings("static-access")
		public boolean CaricaSq ()
		{ 
			this.fileChooser = new JFileChooser() ;
			int n = this.fileChooser.showOpenDialog(NuovoCampionato.this);
			
			
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
				this.temp = (Team) ois.readObject() ;
			} 
			catch (ClassNotFoundException | IOException e) 
			{
				JOptionPane.showMessageDialog(this.FrameB, "Errore nell'importazione della squadra", "Errore import squadra", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return false;
			}
			
			if(this.temp.getSport() != NuovoCampionato.getCh().getSportCh()) {			
				JOptionPane.showMessageDialog(this.FrameB, "La squadra scelta non coincide con lo sport del campionato, selezionare un'altra squadra", "Errore sport squadra", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if(this.temp.getSport() == NuovoCampionato.getCh().getSportCh()) {
				NuovoCampionato.getCh().addTeam(temp);
				return true;
			}
			return false;
			
			
		} 
			
}
		  



