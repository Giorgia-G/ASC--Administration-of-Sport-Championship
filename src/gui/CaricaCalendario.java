package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import gestione.Championship;
import gestione.ChampionshipDay;
import gui.MainFrame;
import main.TestMain;


/**
 * Classe che serve per il caricamento del calendario.
 * @author Nicola Baccarani.
 * @version 1.0
 */
public class CaricaCalendario extends MainPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private 		Championship			NuovoCampionato ;
	//private			Vector<ChampionshipDay>	Calendario ;
	private 		GridLayout 				Layout ;
	private 		JButton					Carica ;
	private 		JButton 				Indietro ;
	private 		JFileChooser 			fileChooser ;
	private 		File					input ;
	private 		FileInputStream			fis ;
	private 		ObjectInputStream 		ois ;
	private 		Thread					thread ;
	private static 	MainFrame					Barra ;
	private static 	JProgressBar			progressBar ;
	private static 	int 					percentuale ;
	@SuppressWarnings("unused")
	private thread.CaricaCalendario	CaricaCal ;
	
	/**
	 * Costruttore della CaricaCalendario
	 * @param f 	MyFrame principale.
	 * @param old	JPanel Precendente, serve quando l'utente clicca il pulsante "Indietro".
	 */
	public CaricaCalendario(MainFrame f, JPanel old) {
		
		super			(f, old);
		
		percentuale 	= 0 ;
		progressBar 	= new JProgressBar() ;
		progressBar.	setMinimum(0);
	    progressBar.	setMaximum(100);
	    progressBar.	setStringPainted(true);
		
		this.Layout 	= new GridLayout(2,1) ;
		this.			setLayout(this.Layout);
		
		this.Carica 	= new JButton("Carica Calendario") ;
		this.Indietro 	= new JButton("Indietro") ;
		
		this.Carica.	setBackground(Color.GREEN);
		this.Indietro.	setBackground(Color.RED);
		
		this.Carica.	addActionListener(this);
		this.Indietro.	addActionListener(this);
		
		this.add(this.Carica) ;
		this.add(this.Indietro) ;
	}
	
	
	/**
	 * Funzione che permette all'utente di scegliere dal FileSystem il file dove risiede il calendario.
	 * @return	Vero o false a sencoda della riuscita o fallimento del caricamento
	 */
	@SuppressWarnings("static-access")
	private boolean caricaCalendario() {
		
		
		this.fileChooser = new JFileChooser() ;
		int n = this.fileChooser.showOpenDialog(CaricaCalendario.this);
		
		
		if(n == this.fileChooser.APPROVE_OPTION) {
			this.input = this.fileChooser.getSelectedFile() ;
		}
		else {
			System.out.println("Errore selezione file");
			return false;
		}
		
		
		try {
			this.fis = new FileInputStream(this.input);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		try {
			this.ois = new ObjectInputStream(fis);
		} 
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			this.NuovoCampionato = (gestione.Championship) ois.readObject() ;
		} 
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return false;
		}
		
		this.Barra = new MainFrame() ;
		this.Barra.setVisible(true);
		this.Barra.setSize(700, 150);
		this.Barra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.Barra.add(this.progressBar) ;
		this.Barra.invalidate();
		this.Barra.validate();
		this.Barra.repaint();
		this.thread = new thread.CaricaCalendario(this.NuovoCampionato) ;
		this.thread.start();
		
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Barra.dispose();
		return true;
		
	}
	
	/**
	 * Funzione che permette l'avanzamento della progressBar.
	 * @param n	Intero che dice di quanto avanzare il caricamento.
	 */
	public static void caricamento(int n) {
		
		//Incremento
		percentuale =n;
		System.out.println("Percentuale: " + percentuale);
		//Imposto il valore della barra al valore della percentuale
		progressBar.setValue(percentuale);
	
	}

	/**
	 * Come in ogni Pannello anche qui risiede un actionlistener.
	 * @param e	Action event da processare.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.Carica) {
			if(!this.caricaCalendario())
				JOptionPane.showMessageDialog(this.FrameB, "Errore caricamento Calendario","Inane error", JOptionPane.ERROR_MESSAGE);
		}
		
		if(e.getSource() == this.Indietro)
			this.FrameB.goTo(TestMain.Panel, this);
		
	}

}
