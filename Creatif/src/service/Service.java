/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.*;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityTransaction;
import modele.*;
import util.JpaUtil;


/**
 *
 * @author Administrateur
 */
public class Service {

    protected ClientDao clientDao;
    protected GallerieDao gallerieDao;
    protected OeuvreDao oeuvreDao;
    protected ArtisteDao artisteDao;
    protected PeintureDao peintureDao;
    protected SculptureDao sculptureDao;
    protected int validite;

    public Service()
    {
         clientDao = new ClientDao();
         gallerieDao = new GallerieDao();
         oeuvreDao = new OeuvreDao();
         artisteDao = new ArtisteDao();
         peintureDao = new PeintureDao();
         sculptureDao = new SculptureDao();

    }
     public int creerClient(Client client){
         validite = 0;
            JpaUtil.openEntityManager();
            EntityTransaction tx = null;
            try {
                tx = JpaUtil.getEntityManagerTransaction();

                tx.begin();
                clientDao.create(client);
                tx.commit();
            }
            catch(Exception e){
                e.printStackTrace();
               System.out.println("Erreur detectée lors de la transaction");
               validite = 1;
            }
            JpaUtil.closeEntityManager();
            return validite;
     }


     public int creerGallerie(Gallerie gallerie, Client client, List<Oeuvre> lOeuvre){         
         
         if(lOeuvre.size() <= 10)
         {    
            validite = 0;
            JpaUtil.openEntityManager();
            EntityTransaction tx = null;
            client.ajouterGallerie(gallerie);
            gallerie.setClient(client);
            gallerie.ajouterOeuvre(lOeuvre);
            gallerie.CalculerPrixTotal();
            for (Oeuvre oeuvre : lOeuvre)
            {
                oeuvre.ajouterGallerie(gallerie);
            }
            try {
                tx = JpaUtil.getEntityManagerTransaction();

                tx.begin();
                gallerieDao.create(gallerie);
                for (Oeuvre oeuvre : lOeuvre)
                {
                    //on met à jour la base de données pour que le lien entre les oeuvres et les galleries se fassent
                    oeuvreDao.update(oeuvre);
                }
                clientDao.update(client);
                
                tx.commit();
            }
            catch(Exception e){
                e.printStackTrace();
               System.out.println("Erreur detectée lors de la transaction");
               validite = 1;
            }
            JpaUtil.closeEntityManager();
         }
         else 
         {
             //Si il y a trop d'oeuvre dans le catalogue on renvoie la taille du catalogue
             validite = lOeuvre.size();
         }
         return validite;
     }

     public int creerOeuvre(Oeuvre oeuvre, Artiste artiste)
     {
         validite = 0;
         JpaUtil.openEntityManager();
         EntityTransaction tx = null;
         oeuvre.setArtiste(artiste);
//         artiste.ajouterOeuvre(oeuvre);
         try{
             tx = JpaUtil.getEntityManagerTransaction();

             tx.begin();

             oeuvreDao.create(oeuvre);
             //on met jour la base de données pour que le lien entre Artiste et oeuvre se fasse
             artisteDao.update(artiste);
             tx.commit();
         }
         catch(Exception e){
             e.printStackTrace();
             System.out.println("Erreur detecte lors de la transaction");
             validite = 1;
         }
         JpaUtil.closeEntityManager();
         return validite;
     }

     public int creerArtiste(Artiste artiste)
     {
         validite = 0;
         JpaUtil.openEntityManager();
         EntityTransaction tx = null;
         try{
             tx = JpaUtil.getEntityManagerTransaction();

             tx.begin();
             artisteDao.create(artiste);
             tx.commit();
         }
         catch(Exception e){
             e.printStackTrace();
             System.out.println("Erreur detecte lors de la transaction");
             validite = 1;
         }
         JpaUtil.closeEntityManager();
         return validite;
     }

     public List<Oeuvre> rechercherOeuvreParDate(Date dateDeb, Date dateFin)
     {
         List<Oeuvre> listeOeuvres;
                  JpaUtil.openEntityManager();
                  listeOeuvres = oeuvreDao.findOeuvreByDate(dateDeb,dateFin);
                  JpaUtil.closeEntityManager();
                  return listeOeuvres;
     }

     public List<Oeuvre> rechercherOeuvreParNom(String nomOeuvre)
     {
         List<Oeuvre> listeOeuvres;
                  JpaUtil.openEntityManager();
                  listeOeuvres = oeuvreDao.findOeuvreByNom(nomOeuvre);
                  System.out.println(listeOeuvres.size());
                  JpaUtil.closeEntityManager();
                  return listeOeuvres;
     }
     
     public Oeuvre rechercherOeuvreParId(int idOeuvre)
     {
                  Oeuvre oeuvre;
                  JpaUtil.openEntityManager();
                  oeuvre = oeuvreDao.findOeuvreById(idOeuvre);
                  JpaUtil.closeEntityManager();
                  return oeuvre;
     }
     
     public List<Oeuvre> rechercherOeuvreParNomDate(String nomOeuvre,Date dateDeb, Date dateFin)
     {
         List<Oeuvre> listeOeuvres;
                  JpaUtil.openEntityManager();
                  listeOeuvres = oeuvreDao.findOeuvreByNomDate(nomOeuvre, dateDeb, dateFin);
                  JpaUtil.closeEntityManager();
                  return listeOeuvres;
     }

     
          public List<Oeuvre> rechercherOeuvreParPrixArtisteDate(int idArtiste, 
                  float prix, Comparaison comparaison, Date dateDeb, Date dateFin)
     {
         List<Oeuvre> listeOeuvres;
                  JpaUtil.openEntityManager();
                  //si artiste vaut "*", cela signifie que l'on ne veut pas faire de requete au niveau de l'artiste
                  if (idArtiste == -1)
                  {
                        listeOeuvres = oeuvreDao.findOeuvreByPrixDate(prix, comparaison, dateDeb, dateFin);
                  }
                  else
                  {
                        listeOeuvres = oeuvreDao.findOeuvreByPrixArtisteDate(idArtiste, prix, comparaison, dateDeb,dateFin);
                  }
                  JpaUtil.closeEntityManager();
                  return listeOeuvres;
     }
     
     //renvoie un client si le code est correct. Le code du client correspond à 
     //son Id généré automatiquement. On ne peut donc pas avoir deux clients comme réponse de la requete
     public Client connexionClient(int code)
     {
         Client client;
         JpaUtil.openEntityManager();
         client = clientDao.findClientById(code);
         JpaUtil.closeEntityManager();
         return client;
     }
     
     public List<Artiste> rechercherTousArtistes()
     {
         List<Artiste> listeArtistes;
         JpaUtil.openEntityManager();
         listeArtistes = artisteDao.findAllArtiste();
         JpaUtil.closeEntityManager();
         return listeArtistes;
     }
     
     public List<Oeuvre> rechercherToutesOeuvres()
     {
         List<Oeuvre> listeOeuvres;
         JpaUtil.openEntityManager();
         listeOeuvres = oeuvreDao.findAllOeuvre();
         JpaUtil.closeEntityManager();
         return listeOeuvres;
     }

     public List<Peinture> rechercherToutesPeintures()
     {
         List<Peinture> listePeintures;
         JpaUtil.openEntityManager();
         listePeintures = peintureDao.findAllPeinture();
         JpaUtil.closeEntityManager();
         return listePeintures;
     }
     
     public List<Sculpture> rechercherToutesSculptures()
     {
         List<Sculpture> listeSculpture;
         JpaUtil.openEntityManager();
         listeSculpture = sculptureDao.findAllSculpture();
         JpaUtil.closeEntityManager();
         return listeSculpture;
     }
}
