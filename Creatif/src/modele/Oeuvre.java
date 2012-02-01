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
@Inheritance (strategy = InheritanceType.JOINED)
public class Oeuvre implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        protected int oeuvreId;

        @ManyToMany 
        private List<Gallerie> listeGalleries = new ArrayList<Gallerie>();

        @ManyToOne
        private Artiste artiste;

        protected String nom;
        protected String carac;
        protected float prix;

    public Oeuvre() {
    }

    public Oeuvre( String nom, String carac, float prix) {
        this.nom = nom;
        this.carac = carac;
        this.prix = prix;
    }

    public void ajouterGallerie(Gallerie gallerie)
    {
        listeGalleries.add(gallerie);
    }

    public void setArtiste(Artiste artiste)
    {
        this.artiste = artiste;
    }

    public int getOeuvreId()
    {
        return oeuvreId;
    }
    
    public List<Gallerie> getListeGalleries()
    {
        return listeGalleries;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public String getCarac() {
        return carac;
    }

    public float getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

}
