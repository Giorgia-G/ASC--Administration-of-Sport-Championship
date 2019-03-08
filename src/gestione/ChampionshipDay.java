package gestione;

import java.io.Serializable;
import java.util.Vector;

/** ChampionshipDay rappresenta una giornata di campionato.
 *  Una giornata di campionato consiste in n/2 partite dove n è il numero
 *  (pari) di squadre del campionato. 
 *  
 *  Ogni giornata di campionato è composta da :
 *  {@link matchday} vettore che contiene le singole partite di tipo {@link Match}
 *  della singola giornata.
 *  {@link numDay} numero intero che rappresenta il numero della giornata di campionato.
 *
 */
public class ChampionshipDay  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** Vector contenente le varie partite della giornata */
	private Vector<Match> matchDay ;
	
	/** Costruttore */
	public ChampionshipDay ()
	{   
		matchDay = new Vector <Match>() ;
		
			
	}
	
	/** Ritorna tutte le partite della giornata */
	public Vector<Match> getMatchDay ()
	{ 
		return this.matchDay ;
	}
	
	
	
	/** Metodo per aggiungere una partita alla giornata, viene aggiunta per ultima */
	public void addMatch(Match m) 
	{
			this.matchDay.add(m) ;
			
	}
	
	
		

	
	

}

