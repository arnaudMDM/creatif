/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.ClientDao;
import javax.persistence.EntityTransaction;
import modele.Client;
import util.JpaUtil;


/**
 *
 * @author Administrateur
 */
public class Service {

    protected ClientDao clientDao;

 public void creerClient(Client client){
     clientDao = new ClientDao();
        JpaUtil.openEntityManager();
        EntityTransaction tx = null;
        try {
            tx = JpaUtil.getEntityManagerTransaction();

            tx.begin();
            clientDao.create(client);
            tx.commit();
        }
        catch(Exception e){
           System.out.println("Erreur detectée lors de la transaction");
        }
        JpaUtil.closeEntityManager();
    }
}
