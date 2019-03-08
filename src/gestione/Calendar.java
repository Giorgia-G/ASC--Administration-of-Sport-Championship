package gestione;

import teampk.* ;

import java.io.Serializable ;
import java.util.Vector ;

/** Classe che rappresenta il calendario delle partite.
 *  Il calendario è costituito da : 
 *  @sport sport del campionato a cui appartiene il calendario
 *  @teams squadre che appartengono al campionato, ogni squadra è un oggetto di tipo 
 *  {@link Team} .
 *  @days  ovvero le singole giornate di campionato, ogni giornata di campionato
 *  è di tipo {@link ChampionshipDay} .
 * 
 *
 */

public class Calendar implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	/** Vettore che contiene tutte le giornate di campionato.
	 * Ogni giornata di campionato è un oggetto di tipo @ChampionshipDay . */
	private Vector<ChampionshipDay> days ;
	
	/** Costruttore a cui passo tutti i parametri.
	 * @param d Vector delle giornate di cui il campionato è composto.
	 */
	public Calendar (Vector<ChampionshipDay> d)
	{ 
		this. days = d ;
	}
	
	/** Ritorna il vettore di giornate del campionato */
	public Vector<ChampionshipDay> getDays ()
	{
		return this.days ;
	}

}
