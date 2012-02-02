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
public class Galerie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int galerieId;

    @ManyToOne
    private Client client;

    @ManyToMany(mappedBy = "listeGaleries")
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

    public Galerie() {
    }

    public Galerie( Date dateDebut, Date dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        prixTotal = 0;
    }

    public int getGalerieId() {
        return galerieId;
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
