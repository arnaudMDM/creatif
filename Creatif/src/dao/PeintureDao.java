/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import modele.Peinture;
import util.JpaUtil;

/**
 *
 * @author arnaud
 */
public class PeintureDao {
    public List<Peinture> findAllPeinture()
    {    
        Query query = JpaUtil.getEntityManager().createQuery("select p from Peinture p order by p.nomOeuvre");
        return query.getResultList();
    }
}
