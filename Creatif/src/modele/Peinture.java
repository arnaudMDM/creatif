/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.util.List;
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

    public Peinture( String titre, String carac, float prix, String dimension) {
        super( titre, carac, prix);
        this.dimension = dimension;
    }
}
