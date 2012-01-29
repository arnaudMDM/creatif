/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;


/**
 *
 * @author Administrateur
 */

@Entity
public class Gallerie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int gallerieId;

    @ManyToOne
    private Client client;

    @ManyToMany(mappedBy = "listeGalleries")
    private List<Oeuvre> listeOeuvres = new ArrayList<Oeuvre>();
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date dateDebut;
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date dateFin;
    protected int prixTotal;
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date dateLivraison;

    public Gallerie() {
    }

    public Gallerie( Date dateDebut, Date dateFin, int prixTotal, Date dateLivraison) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
        this.dateLivraison = dateLivraison;
    }

    public int getGallerieId() {
        return gallerieId;
    }

    public void ajouterOeuvre(Oeuvre oeuvre)
    {
        listeOeuvres.add(oeuvre);
    }

    public void ajouterOeuvre (List <Oeuvre> lOeuvre)
    {
        listeOeuvres.addAll(lOeuvre);
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

}
