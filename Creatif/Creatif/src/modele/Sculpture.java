/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.util.List;

/**
 *
 * @author Administrateur
 */
public class Sculpture extends Oeuvre{

    protected List<Float> dimension;

    public Sculpture(int oeuvreId, String titre, String carac, float prix, List<Float> dimension) {
        super(oeuvreId, titre, carac, prix);
        this.dimension = dimension;
    }



}
