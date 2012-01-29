/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import modele.Oeuvre;
import util.JpaUtil;
import modele.Comparaison;

/**
 *
 * @author Administrateur
 */
public class OeuvreDao {
    public void create(Oeuvre oeuvre)
    {
        JpaUtil.getEntityManager().persist(oeuvre);
    }

    public void update (Oeuvre oeuvre)
    {
        JpaUtil.getEntityManager().merge(oeuvre);
    }

    public List<Oeuvre> findOeuvreByPrix( float prix, Comparaison comparaison)
    {
        Query query = null;

        switch (comparaison)
        {
            case SUP:
              query  = JpaUtil.getEntityManager().createQuery("select e from Oeuvre e WHERE e.prix > :prix");
              break;
            case INF:
              query = JpaUtil.getEntityManager().createQuery("select e from Oeuvre e WHERE e.prix < :prix");
              break;
            case EGAL:
                query = JpaUtil.getEntityManager().createQuery("select e from Oeuvre e WHERE e.prix = :prix");
              break;
        }
              query.setParameter("prix", prix);
        return query.getResultList();
    }
    
        public List<Oeuvre> findOeuvreByPrixAndByDate( float prix, Comparaison comparaison, Date dateDeb,Date dateFin)
    {
        Query query = null;
        String dateRequest = "(e1.oeuvreId NOT IN (Select e2.oeuvreId from Gallerie g1, IN (g1.listeOeuvres) e2) "
                    + "OR e1.oeuvreId IN (select e3.oeuvreId from Oeuvre e3 , "
                    + "IN (e3.listeGalleries) g2 where g2.dateDebut > :dateFin OR g2.dateFin < :dateDeb))";

        switch (comparaison)
        {
            case SUP:
              query  = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 WHERE (e1.prix > :prix)" + " AND " + dateRequest);
              break;
            case INF:
              query = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 WHERE e1.prix < :prix" + " AND " + dateRequest);
              break;
            case EGAL:
                query = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 WHERE e1.prix = :prix" + " AND " + dateRequest);
              break;
        }
              query.setParameter("prix", prix);
              query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
              query.setParameter("dateFin", dateFin, TemporalType.DATE);
        return query.getResultList();
    }

    public List<Oeuvre> findOeuvreByPrixAndArtiste( String unArtiste, float prix, Comparaison comparaison)
    {
        Query query = null;

        switch (comparaison)
        {
            case SUP:
              query  = JpaUtil.getEntityManager().createQuery("select e from Oeuvre e "
                      + "WHERE e.prix > :prix AND e.artiste.nomArtiste = :artiste");
              break;
            case INF:
              query = JpaUtil.getEntityManager().createQuery("select e from Oeuvre e "
                      + "WHERE e.prix < :prix AND e.artiste.nomArtiste = :artiste");
              break;
            case EGAL:
                query = JpaUtil.getEntityManager().createQuery("select e from Oeuvre e "
                        + "WHERE e.prix = :prix AND e.artiste.nomArtiste = :artiste");
              break;
        }
              query.setParameter("prix", prix);
              query.setParameter("artiste", unArtiste);
        return query.getResultList();
    }
    
        public List<Oeuvre> findOeuvreByPrixAndArtisteAndByDate( String unArtiste, float prix, Comparaison comparaison, Date dateDeb,Date dateFin)
    {
        Query query = null;
        String dateRequest = "(e1.oeuvreId NOT IN (Select e2.oeuvreId from Gallerie g1, IN (g1.listeOeuvres) e2) "
            + "OR e1.oeuvreId IN (select e3.oeuvreId from Oeuvre e3 , "
            + "IN (e3.listeGalleries) g2 where g2.dateDebut > :dateFin OR g2.dateFin < :dateDeb))";

        switch (comparaison)
        {
            case SUP:
              query  = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 "
                      + "WHERE (e1.prix > :prix AND e1.artiste.nomArtiste = :artiste)" + " AND " + dateRequest);
              break;
            case INF:
              query = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 "
                      + "WHERE (e1.prix < :prix AND e1.artiste.nomArtiste = :artiste)" + " AND " + dateRequest);
              break;
            case EGAL:
                query = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 "
                        + "WHERE (e1.prix = :prix AND e1.artiste.nomArtiste = :artiste)" + " AND " + dateRequest);
              break;
        }
              query.setParameter("prix", prix);
              query.setParameter("artiste", unArtiste);
              query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
              query.setParameter("dateFin", dateFin, TemporalType.DATE);
        return query.getResultList();
    }
    
    
    public List<Oeuvre> findNomOeuvreAndByDate( String nomOeuvre, Date dateDeb,Date dateFin)
    {
        Query query = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 "
                + "WHERE (UPPER(e1.titre) LIKE :nomOeuvre) AND (e1.oeuvreId NOT IN (Select e2.oeuvreId from Gallerie g1, IN (g1.listeOeuvres) e2) "
                    + "OR e1.oeuvreId IN (select e3.oeuvreId from Oeuvre e3 , "
                    + "IN (e3.listeGalleries) g2 where g2.dateDebut > :dateFin OR g2.dateFin < :dateDeb))");
        query.setParameter("nomOeuvre", '%'+ nomOeuvre.toUpperCase()+'%');
        query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
        query.setParameter("dateFin", dateFin, TemporalType.DATE);
        return query.getResultList();
    }
    
        public List<Oeuvre> findNomOeuvre( String nomOeuvre)
    {
        Query query = JpaUtil.getEntityManager().createQuery("select e from Oeuvre e WHERE UPPER(e.titre) LIKE :nomOeuvre");
        query.setParameter("nomOeuvre", '%'+ nomOeuvre.toUpperCase()+'%');
        return query.getResultList();
    }

}
