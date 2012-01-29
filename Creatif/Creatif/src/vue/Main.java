/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import modele.Client;
import service.Service;

/**
 *
 * @author Administrateur
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static Service service = new Service();

    public static void main(String[] args) {
        //Creation d'un client
        Client client = new Client("20 avenue albert einstein", "69100", "Villeurbanne", 0612121212);
        service.creerClient(client);
    }

}
