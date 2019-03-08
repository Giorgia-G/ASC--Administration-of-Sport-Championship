package gestione;

import java.io.Serializable;
import java.util.Vector;

import teampk.Team;
import gui.NuovoCampionato; 


/**
 * {@link Championship} può essere composto da fino a 30 squadre di classe {@link Team}.
 * @author Giorgia Gibellini
 * @version 1.0
 */
public class Championship implements Serializable {
    private static final long serialVersionUID = -5230409083492858228L;
    
 // Attributi
    
    /** Numero massimo di squadre che il campionato potrÃ  contenere */
    public static final int MAX_TEAM = 30; 
    /** Sport specifico del campionato tra : basket(0), calcio(1), rugby(2) */
    private int sport;
    /** Vettore che rappresenta l'insieme delle squdre che compongono il campionato */
    private Vector<Team> teams; 
    /** Vettore di oggetti ChampionshipDay (giornate di campionato) che costituisce tutto il campionato */
    private Vector<ChampionshipDay> allDays ;
    /** Nome del campionato : fip under 18 maschile, csi femminile, ... */
    String nameChamp ;
    
 // Metodi 
    
    /** Costruttore */
    public Championship() 
    {
    	sport = 0;
        teams = new Vector <Team>(); 
        allDays = new Vector <ChampionshipDay>();
        nameChamp = "Unknown" ;
    }
  
    
    /** Costruttore con parametri  */
    public Championship(String name, int s) 
    {
    	sport = s;
        teams = new Vector <Team>(); 
        allDays = new Vector <ChampionshipDay>();
        nameChamp = name ;
    }
    
    /** Permette di accedere all'oggetto di tipo campionato esternamente */
    public Championship getChampionship()
    { 
    	return this ;
    }
    
    /** Metodo che, dato in ingresso il nome della squadra da cercare,
     * ne restituisce la posizione nel vettore contenente le squadre.
     * @param name nome della squadra da trovare
     * @return idx indice della squadra nel vettore
     */
    public int findObj (String name)
	{ 
		int max = this.teams.size();
		for (int idx = 0; idx<max; idx++)
		{	
			if (this.teams.get(idx).getNameT().equals(name) )
				return idx ;
		} 
		System.out.println("findObj failed");
		return -1 ;
	}
    
    /** Permette di restituire il nome del campionato */
    public String getNameCh()
    { 
    	return this.nameChamp;
    	
    }
    
    /** Permette di restituire lo sport del campionato. Per il basket 0 , per il calcio 1 
     * e per il rugby 2 .*/
    public int getSportCh()
    { 
    	return this.sport;
    	
    }
    
    /** Permette di restituire il vettore delle giornate di campionato. */
    public Vector<ChampionshipDay> getDays()
    { 
    	return this.allDays;
    	
    }
    
    /** Permette di restituire il vettore delle squadre del campionato. */
    public Vector<Team> getTeams()
    { 
    	return this.teams;
    	
    }
    
    /** Funzione per modificare nome e sport del campionato */
    public void modChampName(String nome)
    { 
      nameChamp = nome ;
    }
    
    public void modChampSport(int s)
    { 
      sport = s ;
    }
 
    
    /** Aggiunge una nuova squadra al campionato 
     * @param new_team la squadra da aggiungere */
    public boolean addTeam(Team new_team) 
    {   if (this.teams.size()<30)
    	{ this.teams.add(new_team);
    	  return true ;
    	}
    	else 
    		return false ;
    }
    
    /** Elimina la squadra dal campionato.
     *  @param n nome della squadra da eliminare */
    public boolean deleteTeam (String n) 
    { 	//Trovo l'indice della squadra da eliminare
    	int i = NuovoCampionato.getCh().findObj(n) ;
    	
    	if (i == -1)
    		return false ;
    	else
    	{	Team temp = new Team () ;
    		temp = NuovoCampionato.getCh().getTeams().get(i);
   
    		NuovoCampionato.getCh().getTeams().remove(temp);
    		return true ;
    	}
    }
    
    
    /** Metodo che preso come parametro
     *  @param day ovvero la giornata di campionato da aggiungere
     *  la inserisce nel vettore delle giornate di campionato.
     */
    public void addChampDay (ChampionshipDay day)
    {
    	this.allDays.add(day);
    	return ;
    }
    
    /** Metodo che preso in ingresso l'oggetto cal di tipo @Calendar ne trasferisce il contenuto dentro agli 
     *  appositi campi di @Championship.
     * 
     * @param cal, calendario delle partite caricato da file
     */
    public void addCal (Calendar cal)
    {
    	for (int i=0 ; i<cal.getDays().size(); i++)
    	{
    		NuovoCampionato.getCh().addChampDay(cal.getDays().get(i)) ;
    	}
    }
    
    /** Metodo che, dati come parametri i nomi delle squadre host e guest e i relativi punteggi, 
	 *  setta il risultato della partita come richiesto.
	 * 
	 * @param h, nome della squadra di casa
	 * @param g, nome della squadra ospite
	 * @param ph, punti realizzati dalla squadra di casa
	 * @param pg, punti realizzati dalla squadra ospite
	 */
	public boolean setXMatch (String h,String g, int ph, int pg)
	{
		int n_day = NuovoCampionato.getCh().getDays().size() ;
		
		for(int l = 0 ; l < n_day ; l++) 
		{	int n_match = NuovoCampionato.getCh().getDays().get(l).getMatchDay().size() ;
			for (int j=0; j<n_match; j=j+1)
			{	String n_h = NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getHost().getNameT();
				String n_g = NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).getGuest().getNameT();
			
				if (h.equals(n_h) && g.equals(n_g))
				{ 
					NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).setHPoint(ph);
					NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).setGPoint(pg);
					
				}
		
			}
		}
		
		return true ;
	}
    
    
	/** Funzione che modifica i risultati di tutti i match del calendario attuale resettandoli.
	 *  Ovvero punteggio host = 0
	 *  	   punteggio guest = 0
	 */
	public boolean resetMatches ()
	{	int n_day = NuovoCampionato.getCh().getDays().size() ;
	
		for(int l = 0 ; l < n_day ; l++) 
		{	int n_match = NuovoCampionato.getCh().getDays().get(l).getMatchDay().size() ;
			for (int j=0; j<n_match; j=j+1)
			{	
				NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).setHPoint(0);
				NuovoCampionato.getCh().getDays().get(l).getMatchDay().get(j).setGPoint(0);
					
			}
		
		}
		return true ;
	} 

    
    



}
