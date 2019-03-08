package teampk;

//Guarda la verisone del seriliazziatore

import java.io.Serializable;
import javax.swing.ImageIcon ;
import gui.NuovoCampionato;

/**
* Classe che rappresenta una squadra. Più squadre compongono un {@link Championship}.
*/
public class Team implements Serializable {
	private static final long serialVersionUID = 803102302935792025L;
	
	//Attributi
	
	/** Nome e città  di provenienza della squadra */
	private String nome_sq, citta_sq ;
	
	private ImageIcon logo_sq ;
	
	/** punti_ sq : intero che rappresenta i punti della squadra nella classifica
	 *  win : intero che rappresenta il numero di vittorie della squadra
	 *  lose : intero che rappresenta il numero di sconfitte della squadra 
	 *  draw : intero che rappresenta il numero di pareggi della squadra
	 *  sport : intero che rappresenta lo sport della squadra (0=basket, 1=calcio, 2=rugby)
	 *  cod : intero che rappresenta il codice della squadra*/
	protected int  punti_sq, win, lose, draw, sport, cod;
	
	//Metodi
	
	/** Costruttore vuoto, inizializza le stringhe con uno spazio, i punti della squadra a 0 e il logo della squadra 
	 *  a quello di default. */
	public Team () 
	{ 
		this.nome_sq = " " ;
		this.citta_sq = " " ;
		this.logo_sq = new ImageIcon ("Img\\logoUnk.png") ;
		this.punti_sq = 0 ;
		this.win = 0 ;
		this.lose = 0 ;
		this.draw = 0 ;
		this.cod = 1 ;
	}
	
	/** Costruttore a cui passo i dati della squadra inseriti dall'utente */
	public Team (String nome, String citta, ImageIcon im) 
	{ 
		this.nome_sq = nome ;
		this.citta_sq = citta ;
		this.logo_sq = im ;
		this.punti_sq = 0 ;
		this.win = 0 ;
		this.lose = 0 ;
		this.draw = 0 ;
		this.cod = 1 ;
	}
	
	/** Aggiunge una vittoria alla squadra   */
	public void addWin ()
	{ this.win++; }
	
	/** Aggiunge un pareggio alla variabile intera draw che ne rappresenta il numero */
	public void addDraw ()
	{ this.draw++ ; }
	
	/** Aggiunge una sconfitta alla variabile intera lose che ne rappresenta il numero */
	public void addLose ()
	{ this.lose++ ; }
	
	/** Calcola i punti in classifica della squadra in base a : sport, numero di vittorie,
	 * numero di sconfitte e numero di pareggi. Il variare dei punti in classifica a seconda
	 * dello sport viene rappresentato tramite l'uso dell'ereditarietà e del polimorfismo.
	 */
	public void calcPoints ()
	{ this.punti_sq = this.win *2 ; }
	
	/** Azzera le vittorie della squadra   */
	public void resetWin ()
	{ this.win = 0; }
	
	/** Azzera i pareggi di una squadra */
	public void resetDraw ()
	{ this.draw = 0 ; }
	
	/** Azzera le partite perse di una squadra */
	public void resetLose ()
	{ this.lose  = 0 ; }
	
	public void resetPoints ()
	{ this.punti_sq = 0 ; }
	
	/** Ritorna il nome della squadra */
	public String getNameT ()
	{ return this.nome_sq ; }
	
	/** Ritorna il nome della città a cui appartiene la squadra */
	public String getCity ()
	{ return this.citta_sq ; }
	
	/** Ritorna il logo della squadra */
	public ImageIcon getImg ()
	{ return this.logo_sq ; }
	
	/** Ritorna il codice della squadra */
	public int getCod ()
	{ return this.cod ; }
	
	/** Ritorna il numero di vittorie della squadra */
	public int getWin ()
	{ return this.win ; }
	
	/** Ritorna il numero di pareggi della squadra */
	public int getDraw ()
	{ return this.draw ; }
	
	/** Ritorna il numero di sconfitte della squadra */
	public int getLose ()
	{ return this.lose ; }
	
	/** Ritorna il numero di punti in classifica della squadra */
	public int getPoints ()
	{ return this.punti_sq ; }
	
	/** Ritorna lo sport della squadra */
	public int getSport ()
	{ return this.sport ; }
	
	/** Cambia  il nome della squadra*/
	public void setName (String name)
	{ nome_sq = name ; }
	
	/** Cambia il luogo della squadra */
	public void setCitta (String citta)
	{ this.citta_sq = citta ; }
	
	/** Data un'immagine la pone come logo della squadra */
	public void setImg (ImageIcon im)
	{ this.logo_sq = im ; }
	
	/** Modifica il nome di una squadra dal campionato.
     *  @param n1 nome della squadra da modificare
     *  @param n2 nuovo nome della squadra  */
    public void modN (String n1, String n2) 
    { //Trovo l'indice della squadra da modificare
      int i = NuovoCampionato.getCh().findObj(n1) ;
      //modifico la squadra
      NuovoCampionato.getCh().getTeams().get(i).setName(n2);
    }
	
    /** Modifica il nome della città di una squadra dal campionato.
     *  @param n1 nome della squadra da modificare
     *  @param n2 nuovo nome della città della squadra  */
    public void modC (String n1, String n2) 
    { //Trovo l'indice della squadra da modificare
      int i = NuovoCampionato.getCh().findObj(n1) ;
      //modifico la città della squadra
      NuovoCampionato.getCh().getTeams().get(i).setCitta(n2);
    }
	
    /** Modifica il logo di una squadra dal campionato.
     *  @param n1 nome della squadra da modificare
     *  @param path path del nuovo logo della squadra  */
    public void modL (String n1, String path) 
    { 	//Trovo l'indice della squadra da modificare
    	int i = NuovoCampionato.getCh().findObj(n1) ;
        //Creo l'ImageIcon dal path per darla come parametro alla funzione setImg
        ImageIcon temp = new ImageIcon (path) ;
        NuovoCampionato.getCh().getTeams().get(i).setImg(temp);
    }
    
    
	
	 
	
	
	

 
	
	
}


