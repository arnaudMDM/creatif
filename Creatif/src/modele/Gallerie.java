/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


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

    public Client getClient() {
        return client;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public int getPrixTotal() {
        return prixTotal;
    }

    public Gallerie() {
    }

    public Gallerie( Date dateDebut, Date dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        prixTotal = 0;
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
    
    public void CalculerPrixTotal()
    {
        for(Oeuvre o : listeOeuvres)
        {
            prixTotal += o.getPrix();
        }
    }
}
