package teampk;

import javax.swing.ImageIcon;

import teampk.Team;

public class TeamRugby extends Team {

    private static final long serialVersionUID = 1L;
    
    public TeamRugby ()
    { super (); }
    
    public TeamRugby (String nome, String citta, ImageIcon im)
    { super (nome,citta,im); }
    
    /** Nel rugby la vittoria vale quattro punti in classifica, la sconfitta nessuno
     * e il pareggio due punti.
     */
    public void calcPoints ()
    { this.punti_sq = ( ( this.win * 4 ) + (this.draw * 2)) ; }

}
