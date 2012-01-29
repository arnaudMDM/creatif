/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import modele.Client;
import util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class ClientDao {
    public void create(Client client){
            JpaUtil.getEntityManager().persist(client);
        }
}
