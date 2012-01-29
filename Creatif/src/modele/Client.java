/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Administrateur
 */
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int clientId;

    @OneToMany(mappedBy = "client")
    private List<Gallerie> listeGalleries = new ArrayList<Gallerie>();

    protected String adresse;

    public int getClientId() {
        return clientId;
    }
    protected String cp;
    protected String ville;
    protected int telephone;

    public Client() {
    }

    public Client(String adresse, String cp, String ville, int telephone) {
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.telephone = telephone;
    }

    public void ajouterGallerie(Gallerie gallerie)
    {
        listeGalleries.add(gallerie);
    }



}
