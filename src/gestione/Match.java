package gestione;

import java.io.Serializable;

import teampk.Team;

public class Match implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Attributi
	
	/** Squadra che gioca in casa */
	private Team hostTeam;
	/** Squadra che gioca in trasferta */
	private Team guestTeam;
	/** Punti realizzati dalla squadra ospitante*/
	private int phost;
	/** Punti realizzati dalla squadra ospitata */
	private int pguest;
	
	//Metodi
	
	/** Costruttore di una partita non ancora iniziata*/
	public Match (Team h, Team g)
	{ 
		this.hostTeam = h ;
		this.guestTeam = g ;
		this.phost = 0 ;
		this.pguest = 0 ;
		
	}
	
	/** Costruttore di una partita finita (quindi con punteggio già noto) */
	public Match (Team h, Team g, int ph, int pg)
	{
		this.hostTeam = h ;
		this.guestTeam = g ;
		this.phost = ph ;
		this.pguest = pg ;
	}
	
	public Match getMatch ()
	{
		return this ;
	}
	
	/** Funzione che permette di modificare il punteggio della squadra di casa */
	public void setHPoint (int newh)
	{ 
		this.phost = newh ;
	}
		
	/** Funzione che permette di modificare il punteggio della squadra in trasferta */
	public void setGPoint (int newg)
	{ 
		this.pguest = newg ;
	}
	
	/** Funzione che ritorna i punti fatti dalla squadra di casa nel match */
	public int getPMathHost ()
	{
		return this.phost ;
	}
	
	/** Funzione che ritorna i punti fatti dalla squadra ospite nel match */
	public int getPMatchGuest ()
	{
		return this.pguest ;
	}
	
	/** Metodo che permette di accedere all'oggetto hostTeam esternamente per
	 *  modificarne il risultato. */	 
	public Team getHost() 
	{
		return this.hostTeam ;
	}
	
	/** Metodo che permette di accedere all'oggetto guestTeam esternamente per
	 *  modificarne il risultato. */	 
	public Team getGuest() 
	{
		return this.guestTeam ;
	}
	
	/** Metodo che resetta il punteggio del match ( ovvero pone i punti di 
	 * entrambe le squadre a 0 */
	public void resetMatch ()
	{ 
		this.phost = 0;
		this.pguest = 0;
	
	}
	
	

	
	
	
	
	

}

