package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser ;
import java.io.File ;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.TestMain;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class ModificaSq extends MainPanel implements ActionListener{
    //Controllare la serlialversion UID
	private static final long serialVersionUID = 1L;
	
	private JTextField nome, nomenew,cittanew;
	private JRadioButton cnome, ccitta,clogo;
	private JButton indietro,salva;
    private JFileChooser fileChooser ;
    private File input ;
	
	
	public ModificaSq (MainFrame f, JPanel old)
	{ 
		super(f,old);
	    
	    this.setLayout(new GridLayout(8,2));
	    

		this.nome = new JTextField("Inserire qui il nome della squadra da modificare",30);
		this.nome.setEditable(true); 
		nome.setText("");
	    cnome = new JRadioButton("Nome : ");
		this.nomenew = new JTextField("Inserire qui il nuovo nome della squadra",30);
		this.nomenew.setEditable(true);
		nomenew.setText("");
	    ccitta = new JRadioButton("Citt‡† : ");
		this.cittanew = new JTextField("Inserire qui la nuova citt√† della squadra",30);
		this.cittanew.setEditable(true); 
		cittanew.setText("");
	    clogo = new JRadioButton("Logo : ");
		salva = new JButton("Modifica");
		indietro = new JButton("Home");
	    
	    this.add(new JLabel (" MODIFICA SQUADRA"));
	    this.add(new JLabel (" "));
	    this.add(new JLabel (" Nome della squadra da modificare"));
	    this.add(nome);
	    this.add(new JLabel (" Campi da modificare"));
	    this.add(new JLabel (" "));
	    this.add(cnome);
	    this.add(nomenew);
	    this.add(ccitta); 
	    this.add(cittanew);
	    this.add(clogo);
	    this.add(new JLabel (" "));
	    this.add(new JLabel (" "));
	    this.add(new JLabel (" "));
	    this.add(salva);
	    this.add(indietro);
	    
	    /* Bgroup per raggruppare i vari radio button. Va bene qua? */
		  ButtonGroup bgroup = new ButtonGroup ();	
		  bgroup.add(cnome);
		  bgroup.add(ccitta);
		  bgroup.add(clogo); 

	    nome.addActionListener(this);
	    cnome.addActionListener(this);
	    nomenew.addActionListener(this);
	    ccitta.addActionListener(this);
	    cittanew.addActionListener(this);
	    clogo.addActionListener(this);
	    salva.addActionListener(this);
	    indietro.addActionListener(this);
	    
	}


	public void actionPerformed(ActionEvent e)	{
		
		if (e.getSource() == salva )
		{	
			boolean val = this.changeTeam();
			System.out.println(val);
			if (val)
				JOptionPane.showMessageDialog(null,"Modifica effettuata");
			else
				JOptionPane.showMessageDialog(null,"Modifica non permessa");
				
		}
		
		if (e.getSource() == indietro)
		{ 
			this.FrameB.goTo(TestMain.Panel, this);
		}
		
		
	}
	
	@SuppressWarnings("static-access")
	public String cambiaLogo ()
	{ 
		this.fileChooser = new JFileChooser() ;
		int n = this.fileChooser.showOpenDialog(ModificaSq.this);
		
		if(n == this.fileChooser.APPROVE_OPTION) {
			this.input = this.fileChooser.getSelectedFile() ;
		}
		else {
			System.out.println("Selezione annullata");
			return " ";
		}
		
		return (this.input.getPath());
	}
	
	public boolean changeTeam ()
	{ 	
	
		if (cnome.isSelected() == true)
		{   if (this.nomenew.getText().equals(""))
				{   JOptionPane.showMessageDialog(this.FrameB, "ERRORE : Campo nome nullo","Error", JOptionPane.ERROR_MESSAGE);
					return false ;
				}
			else if (this.nomenew.getText().equals(this.nomenew.getText()))
				{ 	JOptionPane.showMessageDialog(this.FrameB, "ERRORE : Il nuovo nome Ë uguale a quello precedente","Error", JOptionPane.ERROR_MESSAGE);
					return false ;
				}
			else if (NuovoCampionato.getCh().findObj(this.nome.getText()) == -1)
				{ 	JOptionPane.showMessageDialog(this.FrameB, "ERRORE : Squadra non esistente","Error", JOptionPane.ERROR_MESSAGE);
					return false ;
				}
		
			int i = NuovoCampionato.getCh().findObj(this.nome.getText()) ;
		    NuovoCampionato.getCh().getTeams().get(i).modN(this.nome.getText(),this.nomenew.getText() );
		    return true ;
		}
		
		else if (ccitta.isSelected() == true)
		{ 	int i = NuovoCampionato.getCh().findObj(this.nome.getText()) ;
		
			if (this.cittanew.getText().equals(null))
				{	JOptionPane.showMessageDialog(this.FrameB, "ERRORE : Campo citt‡ nullo","Error", JOptionPane.ERROR_MESSAGE);
					return false ;
				}
			if (this.cittanew.getText().equals(NuovoCampionato.getCh().getTeams().get(i).getCity()))
			{	JOptionPane.showMessageDialog(this.FrameB, "ERRORE : Campo citt‡ uguale al precedente","Error", JOptionPane.ERROR_MESSAGE);
				return false ;
			}
			else if (NuovoCampionato.getCh().findObj(this.nome.getText()) == -1)
				{ 	JOptionPane.showMessageDialog(this.FrameB, "ERRORE : Squadra non esistente","Error", JOptionPane.ERROR_MESSAGE);
					return false ;
				}
			
			NuovoCampionato.getCh().getTeams().get(i).modC(this.nome.getText(),this.cittanew.getText() );

			return true ;
		}
		
		else if (clogo.isSelected() == true)
		{   String npath = cambiaLogo() ;
		
			if (NuovoCampionato.getCh().findObj(this.nome.getText()) == -1)
			{ 	JOptionPane.showMessageDialog(this.FrameB, "ERRORE : Squadra non esistente","Error", JOptionPane.ERROR_MESSAGE);
				return false ;
			}
			
			int i = NuovoCampionato.getCh().findObj(this.nome.getText()) ;
			NuovoCampionato.getCh().getTeams().get(i).modL(this.nome.getText(), npath );
			
			return true ;
		    
		}
		
		else return false ;
		
	}
	
	
			
}
