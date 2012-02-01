/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import javax.persistence.Entity;

/**
 *
 * @author Administrateur
 */

@Entity
public class Peinture extends Oeuvre {

    protected String dimension;

    public Peinture() {
    }

    public String getDimension() {
        return dimension;
    }

    public Peinture( String titre, String carac, float prix, String dimension) {
        super( titre, carac, prix);
        this.dimension = dimension;
    }
}
