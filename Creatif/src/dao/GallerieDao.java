/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import modele.Gallerie;
import modele.Oeuvre;
import util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class GallerieDao {
        public void create(Gallerie gallerie){
                JpaUtil.getEntityManager().persist(gallerie);
            }

        public List<Oeuvre> findOeuvreByDate(Date dateDeb,Date dateFin)
        {
            //recherche des oeuvres dont les galleries associées ne correspondent
            //pas à cette fourchette de date et recherche des oeuvres qui ne sont associées à aucune gallerie
            Query query = JpaUtil.getEntityManager().createQuery
                    ("select e1 from Oeuvre e1 WHERE e1.oeuvreId "
                    + "NOT IN (Select e2.oeuvreId from Gallerie g1, IN (g1.listeOeuvres) e2) "
                    + "OR e1.oeuvreId IN (select e3.oeuvreId from Oeuvre e3 , "
                    + "IN (e3.listeGalleries) g2 where g2.dateDebut > :dateFin OR g2.dateFin < :dateDeb) ");
            query.setParameter("dateDeb", dateDeb, TemporalType.DATE);
            query.setParameter("dateFin", dateFin, TemporalType.DATE);
            return query.getResultList();
        }
    }
