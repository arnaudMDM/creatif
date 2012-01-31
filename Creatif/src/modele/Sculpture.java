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
public class Sculpture extends Oeuvre{

    public String getDimension() {
        return dimension;
    }

    protected String dimension;

    public Sculpture() {
    }

    public Sculpture(String titre, String carac, float prix, String dimension) {
        super( titre, carac, prix);
        this.dimension = dimension;
    }



}
