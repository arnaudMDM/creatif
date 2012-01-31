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
    
        public List<Oeuvre> findOeuvreByPrixDate( float prix, Comparaison comparaison, Date dateDeb,Date dateFin)
    {
        Query query = null;
        String dateRequest = "(e1.oeuvreId NOT IN (Select e2.oeuvreId from Gallerie g1, IN (g1.listeOeuvres) e2) "
                    + "OR e1.oeuvreId IN (select e3.oeuvreId from Oeuvre e3 , "
                    + "IN (e3.listeGalleries) g2 where g2.dateDebut > :dateFin OR g2.dateFin < :dateDeb))";
        String order = "order by e1.nom";

        switch (comparaison)
        {
            case SUP:
              query  = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 WHERE (e1.prix > :prix)" + " AND " + dateRequest + order);
              break;
            case INF:
              query = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 WHERE e1.prix < :prix" + " AND " + dateRequest + order);
              break;
            case EGAL:
                query = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 WHERE e1.prix = :prix" + " AND " + dateRequest + order);
              break;
        }
              query.setParameter("prix", prix);
              query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
              query.setParameter("dateFin", dateFin, TemporalType.DATE);
        return query.getResultList();
    }

//    public List<Oeuvre> findOeuvreByPrixArtiste( String unArtiste, float prix, Comparaison comparaison)
//    {
//        Query query = null;
//
//        switch (comparaison)
//        {
//            case SUP:
//              query  = JpaUtil.getEntityManager().createQuery("select e from Oeuvre e "
//                      + "WHERE e.prix > :prix AND e.artiste.nom = :artiste");
//              break;
//            case INF:
//              query = JpaUtil.getEntityManager().createQuery("select e from Oeuvre e "
//                      + "WHERE e.prix < :prix AND e.artiste.nom = :artiste");
//              break;
//            case EGAL:
//                query = JpaUtil.getEntityManager().createQuery("select e from Oeuvre e "
//                        + "WHERE e.prix = :prix AND e.artiste.nom = :artiste");
//              break;
//        }
//              query.setParameter("prix", prix);
//              query.setParameter("artiste", unArtiste);
//        return query.getResultList();
//    }
    
        public List<Oeuvre> findOeuvreByPrixArtisteDate( int unIdArtiste, float prix, Comparaison comparaison, Date dateDeb,Date dateFin)
    {
        Query query = null;
        String dateRequest = "(e1.oeuvreId NOT IN (Select e2.oeuvreId from Gallerie g1, IN (g1.listeOeuvres) e2) "
            + "OR e1.oeuvreId IN (select e3.oeuvreId from Oeuvre e3 , "
            + "IN (e3.listeGalleries) g2 where g2.dateDebut > :dateFin OR g2.dateFin < :dateDeb))";
        String order = "order by e1.nom";

        switch (comparaison)
        {
            case SUP:
              query  = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 "
                      + "WHERE (e1.prix > :prix AND e1.artiste.idArtiste = :unIdArtiste)" + " AND " + dateRequest + order);
              break;
            case INF:
              query = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 "
                      + "WHERE (e1.prix < :prix AND e1.artiste.idArtiste = :unIdArtiste)" + " AND " + dateRequest + order);
              break;
            case EGAL:
                query = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 "
                        + "WHERE (e1.prix = :prix AND e1.artiste.idArtiste = :unIdArtiste)" + " AND " + dateRequest + order);
              break;
        }
              query.setParameter("prix", prix);
              query.setParameter("unIdArtiste", unIdArtiste);
              query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
              query.setParameter("dateFin", dateFin, TemporalType.DATE);
        return query.getResultList();
    }
    
    
    public List<Oeuvre> findOeuvreByNomDate( String nomOeuvre, Date dateDeb,Date dateFin)
    {
        String order = "order by e1.nom";
        Query query = JpaUtil.getEntityManager().createQuery("select e1 from Oeuvre e1 "
                + "WHERE (UPPER(e1.nom) LIKE :nomOeuvre) AND (e1.oeuvreId NOT IN (Select e2.oeuvreId from Gallerie g1, IN (g1.listeOeuvres) e2) "
                    + "OR e1.oeuvreId IN (select e3.oeuvreId from Oeuvre e3 , "
                    + "IN (e3.listeGalleries) g2 where g2.dateDebut > :dateFin OR g2.dateFin < :dateDeb))" + order);
        query.setParameter("nomOeuvre", '%'+ nomOeuvre.toUpperCase()+'%');
        query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
        query.setParameter("dateFin", dateFin, TemporalType.DATE);
        return query.getResultList();
    }
    
        public List<Oeuvre> findOeuvreByNom( String nomOeuvre)
    {
        Query query = JpaUtil.getEntityManager().createQuery("select e from Oeuvre e WHERE UPPER(e.nom) LIKE :nomOeuvre");
        query.setParameter("nomOeuvre", '%'+ nomOeuvre.toUpperCase()+'%');
        return query.getResultList();
    }
        
        public Oeuvre findOeuvreById( int unIdOeuvre)
    {
        Query query = JpaUtil.getEntityManager().createQuery("select e from Oeuvre e WHERE e.oeuvreId = :unIdOeuvre");
        query.setParameter("unIdOeuvre", unIdOeuvre);
        return (Oeuvre) query.getSingleResult();
    }
        
        public List<Oeuvre> findOeuvreByDate(Date dateDeb,Date dateFin)
    {
        String order = "order by e1.nom";
        //recherche des oeuvres dont les galleries associées ne correspondent
        //pas à cette fourchette de date et recherche des oeuvres qui ne sont associées à aucune gallerie
        Query query = JpaUtil.getEntityManager().createQuery
                ("select e1 from Oeuvre e1 WHERE e1.oeuvreId "
                + "NOT IN (Select e2.oeuvreId from Gallerie g1, IN (g1.listeOeuvres) e2) "
                + "OR e1.oeuvreId IN (select e3.oeuvreId from Oeuvre e3 , "
                + "IN (e3.listeGalleries) g2 where g2.dateDebut > :dateFin OR g2.dateFin < :dateDeb)" + order);
        query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
        query.setParameter("dateFin", dateFin, TemporalType.DATE);
        return query.getResultList();
    }
        
        public List<Oeuvre> findAllOeuvre()
        {
            Query query = JpaUtil.getEntityManager().createQuery("SELECT e FROM Oeuvre e ORDER BY e.nom");
            return query.getResultList();
        }

}
