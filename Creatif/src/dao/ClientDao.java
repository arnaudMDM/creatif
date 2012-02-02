/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import javax.persistence.Query;
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
        public void update(Client client)
        {
                JpaUtil.getEntityManager().merge(client);
        }
        
        public Client findClientById(int code)
        {
            Query query = JpaUtil.getEntityManager().createQuery("Select c from Client c where c.clientId = :code");
            query.setParameter("code", code);
            return (Client) query.getSingleResult();
        }
    }



