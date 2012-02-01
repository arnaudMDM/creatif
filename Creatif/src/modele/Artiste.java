/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Administrateur
 */

@Entity
public class Artiste implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int idArtiste;

    public int getIdArtiste() {
        return idArtiste;
    }


    public String getBiographie() {
        return biographie;
    }

    public String getNom() {
        return nom;
    }
    
    protected String prenom;
    protected String nom;


    protected String biographie;

    public Artiste() {
    }

    public Artiste(String prenom, String nom, String biographie) {
        this.prenom = prenom;
        this.nom = nom;
        this.biographie = biographie;
    }

    public String getPrenom() {
        return prenom;
    }





}
