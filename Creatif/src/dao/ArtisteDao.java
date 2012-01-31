/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import javax.persistence.Query;
import modele.Artiste;
import util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class ArtisteDao {

    public void create(Artiste artiste)
    {
        JpaUtil.getEntityManager().persist(artiste);
    }

    public void update(Artiste artiste)
    {
        JpaUtil.getEntityManager().merge(artiste);
    }
    
    public List<Artiste> findAllArtiste()
    {
        Query query = JpaUtil.getEntityManager().createQuery("select a from Artiste a order by a.nom");
        return query.getResultList();
    }

}
