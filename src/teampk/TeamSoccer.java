package teampk;

import javax.swing.ImageIcon;

import teampk.Team;

public class TeamSoccer extends Team {

    private static final long serialVersionUID = 1L;
    
    public TeamSoccer ()
    { super (); }
    
    public TeamSoccer (String nome, String citta, ImageIcon im)
    { super (nome,citta,im); }
    
    /** Nel calcio la vittoria vale tre punti in classifica, la sconfitta nessuno
     * e il pareggio un punto.
     */
    public void calcPoints ()
    { this.punti_sq = ( ( this.win * 3 ) + (this.draw * 1)) ; }

}