package gui;

import javax.swing.JPanel;

/** Classe astratta da cui erediteranno tutti i panel.
 *  Al costruttore si passano i parametri :
 *  @m ovvero il MainFrame che contiene tutti i panel in successione, unico MainFrame 
 *  del programma.
 *  @prev ovvero il panel precedente all'attuale, serve per implementare il metodo
 *  @goTo per "sfogliare" i vari panel.
 */
 
    public abstract class MainPanel extends JPanel {
    	
	private static final long serialVersionUID = 1L;
	
	/** FrameB è il "frame base" su cui "scorrerrano" i vari panel durante l'esecuzione. 
	 */
	protected MainFrame FrameB ;
	
	/** PrevPanel è il pannello precedente a quello aperto, serve 
	 * in modo da implementare in modo semplice il tasto "Indietro" della 
	 * finestra.
	 */
	protected JPanel PrevPanel ;
	
	public MainPanel(MainFrame m, JPanel prev) 
	{
		this.FrameB = m ;
		this.PrevPanel = prev ;

	}
	
	/** Funzione per, dato un pannello, ritornare il pannello precedente.
	 * Utile seriamente ?  */
	public JPanel getPrev ()
	{
		return this.PrevPanel;
	}
		
}

