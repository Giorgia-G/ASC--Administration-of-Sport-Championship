package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.TestMain;

public class EliminaSq extends MainPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JTextField nomedel;
	private JButton salva, indietro ;
	
	public EliminaSq(MainFrame f, JPanel old)
	{
		super(f,old);
	
	    this.setLayout(new GridLayout(4,2));
	    
	    nomedel = new JTextField ("Inserire qui il nome della squadra da eliminare ",30);
	    this.nomedel.setEditable(true); 
	    salva = new JButton ("OK");
	    indietro = new JButton ("Home");
	    
	    this.add(new JLabel (" ELIMINA SQUADRA "));
	    this.add(new JLabel (" "));
	    this.add(new JLabel (" Nome della squadra da eliminare"));
	    this.add(nomedel);
	    this.add(new JLabel ("  "));
	    this.add(new JLabel ("  "));
	    this.add(salva);
	    this.add(indietro);
	    
	    /*ActionListener*/
		this.nomedel.addActionListener(this);
		this.salva.addActionListener(this);
		this.indietro.addActionListener(this);
	    
	}
	

	
	public void actionPerformed(ActionEvent e) 
	  {
		if (e.getSource() == salva)
		{  	String nn = this.nomedel.getText();
		
			if(nn.length() < 1) 
			{	JOptionPane.showMessageDialog(this.FrameB, "ERROR : il nome della squadra non puo essere nullo.","Error", JOptionPane.ERROR_MESSAGE);
				return  ; 
			}
			
			if (NuovoCampionato.getCh().deleteTeam(nn))
			{	JOptionPane.showMessageDialog(null,"Eliminazione effettuata"); }
			else 
			{ 	JOptionPane.showMessageDialog(this.FrameB,"ERROR : squadra non presente","Error",JOptionPane.ERROR_MESSAGE); }
			
		}
		
		if (e.getSource() == indietro)
		{ 
			this.FrameB.goTo(TestMain.Panel, this);
		}
		
		}

}
