/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

/**
 *
 * @author Administrateur
 */
public class Oeuvre {

        protected int oeuvreId;
        protected String titre;
        protected String carac;
        protected float prix;

    public Oeuvre(int oeuvreId, String titre, String carac, float prix) {
        this.oeuvreId = oeuvreId;
        this.titre = titre;
        this.carac = carac;
        this.prix = prix;
    }

}
