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
public class Artiste implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int idArtiste;

    public String getNomArtiste() {
        return nomArtiste;
    }

    protected String nomArtiste;

    @OneToMany(mappedBy = "artiste")
    protected List<Oeuvre> listeOeuvres = new ArrayList<Oeuvre>();

    protected String biographie;

    public Artiste() {
    }

    public Artiste(String nomArtiste, String biographie) {
        this.nomArtiste = nomArtiste;
        this.biographie = biographie;
    }

    public void ajouterOeuvre(Oeuvre oeuvre)
    {
        listeOeuvres.add(oeuvre);
    }




}
