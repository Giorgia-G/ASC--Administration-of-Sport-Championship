package thread;

import teampk.* ;
import gestione.Championship;
import gui.MainFrame;

/**
 * Classe che implementa l'uso del thread per il caricamento del Calendario da File.
 * 
 * @author Giorgia Gibellini
 * @version 1.0
 */
public class CaricaCalendario extends Thread{
	
	private Championship NuovoCampionato ;
	
	public CaricaCalendario(Championship Camp) 
	{
		this.NuovoCampionato = Camp ;
		
	}
	
	
	/**
	 * Funzione che descrive il corpo principale del thread.
	 */
	public void run() {
		
		gui.CaricaCalendario.caricamento(1);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if(this.NuovoCampionato.getSportCh() == 0)
			System.out.println(" Il calendario in caricamento è di basket ");
		gui.CaricaCalendario.caricamento(2);
		try {
			gui.CaricaCalendario.caricamento(5);
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if(this.NuovoCampionato.getSportCh() == 1)
			System.out.println(" Il calendario in caricamento è di calcio ");
		gui.CaricaCalendario.caricamento(7);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		if(this.NuovoCampionato.getSportCh() == 2)
			System.out.println(" Il calendario in caricamento è di rugby ");
		try {
			gui.CaricaCalendario.caricamento(25);
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		gui.CaricaCalendario.caricamento(50);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		gui.CaricaCalendario.caricamento(100);
		
		//this.NuovoCampionato.LoadCal(this.Calendario.getGiornate(), this.Calendario.getSquadre());
		//this.GGP = new GestioneGiornate(this.Frame, this.CCP, this.Campionato) ;
		//this.Frame.changePanel(this.CCP, this.GGP);
		return ;
		
	}

}