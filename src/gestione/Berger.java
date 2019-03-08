package gestione;


import java.util.Vector;

import gui.NuovoCampionato;
import teampk.* ;

/** L'algoritmo di Berger serve per generare calendari di partite con scontri casuali.
 *  Bisogna considerare un numero pari di squadre. Le giornate di ogni girone (andata
 *  e ritorno) sono date dal numero di squadre -1 .
 *  L'algoritmo opera in questo modo : la prima squadra rimane fissa, le altre shiftano.
 *  Il girone di ritorno è uguale a quello di andata ma si inverte il campo di gioco.
 */

public class Berger {
				
		/** Lista delle squadre che giocano in casa */
		private Team[] Casa ;
		
		/** Lista delle squadre che giocano in trasferta */
		private Team[] Trasferta ;
			
		/** Giornata "di appoggio" che verra' aggiunta al vettore in MyChamp */
		private ChampionshipDay Giornata_temp ;
		
		/** Partita " di appoggio" che verrà aggiunta al vettore in ChampionshipDay. */
		private Match Partita_temp ;
		
		/** Numero di squadre del campionato (il numero è pari) */
		private int nsquadre ;
		
		/** Numero di giornate del campionato */
		private int ngiornate ;	
		
		public Berger() {
			
			if(NuovoCampionato.getCh().getDays().size() !=0) {
				return ;
			}
					
			this.nsquadre 	= NuovoCampionato.getCh().getTeams().size() ;
			this.ngiornate 	= 2*(NuovoCampionato.getCh().getTeams().size()-1) ;
					
			this.Casa 				= new Team[this.nsquadre/2] ;
			this.Trasferta 			= new Team[this.nsquadre/2] ;
			
			for(int i = 0 ; i < this.nsquadre /2 ; i++) {
				
				this.Casa[i] 		= NuovoCampionato.getCh().getTeams().elementAt(i) ;
				this.Trasferta[i] 	= NuovoCampionato.getCh().getTeams().elementAt(this.nsquadre-1-i) ;
			}
			
			for(int i = 0 ; i < this.ngiornate ; i++) {
				
				//Si crea una nuova giornata di campionato
				this.Giornata_temp = new ChampionshipDay() ;
				
				//Alternanza delle partite in casa e in trasferta
				if(i %2 == 0) {
					for(int j = 0 ; j < this.nsquadre/2; j++) {
						
						//Creazione di una partita
						this.Partita_temp = new Match(this.Trasferta[j], this.Casa[j]) ;
						
						//Inserisco la partita all'interno della giornata 
						this.Giornata_temp.addMatch(this.Partita_temp);
					}
				}
				if(i % 2 != 0) {
					for(int j = 0 ; j < this.nsquadre / 2 ; j++) {
						
						//Creo una partita
						this.Partita_temp = new Match(this.Casa[j], this.Trasferta[j]) ;
						
						//Inserisco la partita all'interno della giornata
						this.Giornata_temp.addMatch(this.Partita_temp);
					}
				}
				
				Team pivot  	= this.Casa[0] ;
				Team riporto 	= this.Trasferta[this.Trasferta.length-1] ;
				
				this.Trasferta 		= this.shiftRight(this.Trasferta, this.Casa[1]) ;
				this.Casa 			= this.shiftLeft(this.Casa, riporto) ;
				
				this.Casa[0] 		= pivot ;
				
				NuovoCampionato.getCh().addChampDay(this.Giornata_temp);
			}
		}
			
		/**
		 * Metodo per effettuare lo shift a sinistra delle squadre in casa ed inserire in ultima
		 * posizione la squadra uscente dallo shift a destra delle squadre in trasferta.
		 * @param data
		 * @param add
		 * @return
		 */
		private Team[] shiftLeft(Team[] data, Team add) {
			 Team[] temp = new Team[data.length];
			 
			 for (int i = 0; i < data.length-1; i++)
				 temp[i] = data[i + 1];
			 
			 temp[data.length - 1] = add;
			 return temp;
		}
			
		
		/**
		 * Metodo per effettuare lo shift a destra delle squadre in trasferta ed inserire
		 * in prima posizione la squadra uscente dallo shift a sinistra delle squadre in casa.
		 * @param data
		 * @param add
		 * @return
		 */
		private Team[] shiftRight(Team[] data, Team add) {
			
			Team[] temp = new Team[data.length];
			
			for (int i = 1; i < data.length; i++)
				temp[i] = data[i - 1];
			
			temp[0] = add;
			return temp;
		}

	}


