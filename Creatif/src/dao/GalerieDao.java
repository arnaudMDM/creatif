/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import modele.Galerie;
import util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class GalerieDao {
        public void create(Galerie galerie){
                JpaUtil.getEntityManager().persist(galerie);
            }
    }
