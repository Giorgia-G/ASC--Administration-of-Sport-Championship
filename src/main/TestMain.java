package main;
import java.io.IOException;

import gui.MainFrame;
import gui.PageHome;
public class TestMain {
	
	/**Panel è il Panel della Home Page dell'applicazione e fr è il frame su cui scorrono
	 * i vari panel durante l'uso dell'applicazione, sono dichiarati public e static così da 
	 * risultare accessibili in tutto il programma da ogni classe e essere unici.
	 */
	public static PageHome Panel ;
	public static MainFrame fr ;
	
	public static void main(String[] args) throws IOException 
	{
		fr = new MainFrame() ;
		Panel = new PageHome(fr) ;
		fr.add(Panel) ;
		fr.goTo(Panel, Panel);
		
			
	}

}
