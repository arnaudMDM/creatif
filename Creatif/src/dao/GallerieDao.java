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
    }
