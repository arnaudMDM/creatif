/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

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
    private List<Galerie> listeGaleries = new ArrayList<Galerie>();

    protected String adresse;

    public int getClientId() {
        return clientId;
    }
    
    protected String prenom;
    protected String nom;
    protected String cp;
    protected String ville;
    protected String telephone;

    public Client() {
    }

    public Client(String adresse, String cp, String ville, String telephone, String prenom, String nom) {
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.telephone = telephone;
        this.prenom = prenom;
        this.nom = nom;
    }

    public List<Galerie> getListeGaleries() {
        return listeGaleries;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCp() {
        return cp;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getVille() {
        return ville;
    }

    public void ajouterGalerie(Galerie galerie)
    {
        listeGaleries.add(galerie);
    }



}
