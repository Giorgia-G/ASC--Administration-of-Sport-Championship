package teampk;

import javax.swing.ImageIcon;

import teampk.Team;

public class TeamBasket extends Team {

    private static final long serialVersionUID = 1L;
    
    public TeamBasket ()
    { super (); }
    
    public TeamBasket(String nome, String citta,ImageIcon im)
    { super (nome,citta,im); }
    
    /** Nel basket la vittoria vale due punti in classifica, la sconfitta nessuno
     * e il pareggio non è previsto.
     */
    public void calcPoints ()
    { this.punti_sq = this.win *2 ; }

}
