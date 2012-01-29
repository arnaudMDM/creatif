/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.util.Date;

/**
 *
 * @author Administrateur
 */
public class Gallerie {

    protected int gallerieId;
    protected Date dateDebut;
    protected Date dateFin;
    protected int prixTotal;
    protected Date dateLivraison;

    public Gallerie(int gallerieId, Date dateDebut, Date dateFin, int prixTotal, Date dateLivraison) {
        this.gallerieId = gallerieId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
        this.dateLivraison = dateLivraison;
    }

}
