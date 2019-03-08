
package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;



/** Frame su cui si interscambieranno i vari panel durante l'esecuzione del programma. 
 *  In questo modo si evita di creare molti frame. 
 *  I panel si succedono sullo stesso frame e permettono
 *  anche di risalire più facilmente al panel precedente.
 *  @author Gibellini Giorgia
 */
public class MainFrame extends JFrame
{ 	//Controllare la serlialversion UID
	private static final long serialVersionUID = 1L;
	

	public MainFrame() 
	{
	this.setTitle("ASC - Administration of Sport Championship ");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setBounds(200,100,790,590);
	this.setVisible(true);
		
	/*NOT WORKING */
	/** Cambia l'icona in alto a sinistra della finestra con quella del path 
    ImageIcon imgasc = new ImageIcon("/home/bea/Scrivania/img_prj/ASC.jpg");
    this.setIconImage(imgasc.getImage());
     */
    }
    
	/** Metodo che permette, passando il panel futuro e il panel attuale di risalire a quel
	 *  e panel specifico. 
	 *  Può essere usata sia per implementare un pulsante "Home" che per implementare
	 *  un pulsante "Indietro".
	 * 
	 * @param home
	 * @param now
	 */
	public void goTo(JPanel next, JPanel now) 
	{	
	this.remove(now);
	this.add(next) ;
	this.invalidate();
	this.validate();
	this.repaint();	
    }
	

	}
