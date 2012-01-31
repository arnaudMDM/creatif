/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import modele.Sculpture;
import util.JpaUtil;

/**
 *
 * @author arnaud
 */
public class SculptureDao {
        public List<Sculpture> findAllSculpture()
    {    
        Query query = JpaUtil.getEntityManager().createQuery("select s from Sculpture s order by s.nom");
        return query.getResultList();
    }
}
