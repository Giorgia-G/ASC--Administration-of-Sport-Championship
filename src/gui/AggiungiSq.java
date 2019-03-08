package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser ;
import javax.swing.JOptionPane;
import java.io.File ;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon ;

import teampk.*;
import main.TestMain;

public class AggiungiSq extends MainPanel implements ActionListener{
	private static final long serialVersionUID = 1L; 
	
	private JTextField nome ;
	private JTextField citta ;
	private JButton aggiungi ;
	private JButton indietro ;
	private Team temp ;
	private String temp_path = "C:\\Users\\utente\\Desktop\\ASC3\\IMG\\UnknownTeam.jpg" ;
	private ImageIcon temp_img ;
	private JFileChooser fileChooser ;
	private File input ;
	
	public AggiungiSq (MainFrame f, JPanel old )
	{
		super (f,old);
		
		this.setLayout(new GridLayout(7,3));
		
		
		nome = new JTextField (" ", 30);
		nome.setText("");
		citta = new JTextField (" ", 30);
		citta.setText("");
		indietro = new JButton ("Home");
		aggiungi = new JButton (" OK ") ;
			
		this.add(new JLabel (" "));
		this.add(new JLabel (" "));
		this.add(new JLabel ("Nome"));
		this.add(nome);
		this.add(new JLabel ("Città "));
		this.add(citta);
		this.add(new JLabel (" "));
		this.add(new JLabel (" "));
		this.add(new JLabel (" "));
		this.add(new JLabel (" "));
		this.add(aggiungi); 
		this.add(indietro);
		
		this.nome.addActionListener(this);
		this.citta.addActionListener(this);
		this.aggiungi.addActionListener(this);
		this.indietro.addActionListener(this);
		
		
	}
	

	public void actionPerformed(ActionEvent e)	{
		

		if (e.getSource() == aggiungi)
		{   
			temp_path = this.caricaLogo();
			if (this.datiSq(temp_path))
				JOptionPane.showMessageDialog(null,"Squadra caricata con successo");
			
			//Stampa di prova, serve per vedere se ho salvato la squadra bene
			int i = NuovoCampionato.getCh().getTeams().size() ;
			for (int k=0; k<i; k++)
			{	System.out.println(NuovoCampionato.getCh().getTeams().get(k).getNameT());
				System.out.println(NuovoCampionato.getCh().getTeams().get(k).getCity());
				System.out.println(NuovoCampionato.getCh().getTeams().get(k).getImg());		
				System.out.println(" -------------------------------------------------");
			}
			
		}
		
		if (e.getSource().equals(indietro))
		{ 
			this.FrameB.goTo(TestMain.Panel, this);
		}
		
		
	}
	
	/** Aggiunge una squadra al campionato.
	 *  I dati forniti dall'utente sono :
	 * @param path percorso del logo della squadra passato come parametro.
	 * @return true o false a seconda della riuscita o fallimento dell'operazione.
	 */
	public boolean datiSq (String path)
	{	
		this.temp_img = new ImageIcon (path) ;
		if(this.nome.getText().length() < 1) {
			JOptionPane.showMessageDialog(this.FrameB, "Il nome della squadra non puo essere nullo.","Inane error", JOptionPane.ERROR_MESSAGE);
			return false ;
		}
		if(this.citta.getText().length() < 1) {
			JOptionPane.showMessageDialog(this.FrameB, "La città della squadra non puo essere nulla.","Inane error", JOptionPane.ERROR_MESSAGE);
			return false ;
		}
		
		switch(NuovoCampionato.getCh().getSportCh()) {
		
			case 0 :
				this.temp = new TeamBasket(this.nome.getText(), this.citta.getText(), temp_img) ;
				break ;
				
			case 1:
				this.temp = new TeamSoccer(this.nome.getText(),this.citta.getText(), temp_img) ;
				break ;
				
			case 2:
				this.temp = new TeamRugby(this.nome.getText(), this.citta.getText(), temp_img) ;
				break ;
				
			default :
				System.out.println("Errore imprevisto");
				this.FrameB.dispose();
				System.exit(1);
				break ;
		}
		
		if(NuovoCampionato.getCh().addTeam(temp)) 
		{   
			return true ;
		}
		
		return false ;
	}
	
	@SuppressWarnings("static-access")
	public String caricaLogo ()
	{ 
		this.fileChooser = new JFileChooser() ;
		int n = this.fileChooser.showOpenDialog(AggiungiSq.this);
		
		if(n == this.fileChooser.APPROVE_OPTION) {
			this.input = this.fileChooser.getSelectedFile() ;
		}
		else {
			System.out.println("Selezione annullata");
			return "C:\\Users\\utente\\Desktop\\ASC3\\IMG\\UnknownTeam.jpg";
		}
		return (this.input.getPath());
	
	
	}

}
