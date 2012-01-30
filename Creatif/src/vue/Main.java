/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import creatif.Saisie;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modele.*;
import service.Service;

/**
 *
 * @author Administrateur
 */

public class Main {

    /**
     * @param args the command line arguments
     */
//    public static List<Client> listeClientsRef;;
//    public static List<Oeuvre> listeOeuvresRef;
//    public static List<Gallerie> listeGalleriesRef;
//    public static List<Artiste> listeArtistesRef;
    
    public static Service service = new Service();
    
    public static void Initialisation()
    {
//        listeClientsRef = new ArrayList<Client>();
//        listeOeuvresRef = new ArrayList<Oeuvre>();
//        listeGalleriesRef = new ArrayList<Gallerie>();
//        listeArtistesRef = new ArrayList<Artiste>();
        
        Client client = new Client("20 avenue albert einstein", "69100", "Villeurbanne", 0612121212, "Jean", "Dupont");
//        listeClientsRef.add(client);
        service.creerClient(client);

        Date dateDeb = new Date(112,5,12);
        Date dateFin = new Date(112,5,28);
        int prixTotal = 2000;
        Gallerie gallerie = new Gallerie(dateDeb,dateFin,prixTotal);
//       listeGalleriesRef.add(gallerie);

        Artiste artiste = new Artiste("heu", "test ","C'est");
//        listeArtistesRef.add(artiste);
        service.creerArtiste(artiste);

        Peinture oeuvre = new Peinture("Monochrome test","pigment et résine synthétique sur toile",124,"50x150");
//       listeOeuvresRef.add(oeuvre);
        Peinture oeuvre2 = new Peinture("Monochrome jaune","pigment et résine synthétique sur toile",154,"50x150");
//        listeOeuvresRef.add(oeuvre2);
        Sculpture oeuvre3 = new Sculpture("Monochrome marron","pigment et résine synthétique sur toile",154,"50x150");  
//        listeOeuvresRef.add(oeuvre3);
        service.creerOeuvre(oeuvre, artiste);
        service.creerOeuvre(oeuvre2, artiste);
        service.creerOeuvre(oeuvre3, artiste);
        List <Oeuvre> lOeuvre = new ArrayList<Oeuvre> ();
        lOeuvre.add(oeuvre);
        lOeuvre.add(oeuvre2);
        lOeuvre.add(oeuvre3);
        

        service.creerGallerie(gallerie, client, lOeuvre);
        
    }
    
    public static void AfficherCatalogue(List<Oeuvre> catalogue)
    {
        int i = 0;
        for(Oeuvre o : catalogue)
        {
            System.out.println(o.getTitre() 
                    + '\t' + o.getArtiste().getNomArtiste() +'\t' + Float.toString(o.getPrix()) + " €" + " (" + i + ")");
            i++;
        }
    }

    
    public static List<Date> SaisieDate()
    {         
        List<Date> listeDates = new ArrayList<Date>();
        boolean dateCorrect = false;
        while(!dateCorrect)
        {           
            List<Oeuvre> catalogue;
            Date dateDebut = null;
            Date dateFin = null;
            String reponse;
            String str[];
            int jour;
            int mois;
            int année;
            while(!dateCorrect)
            {
                try { 
                    reponse = Saisie.lireChaine("veuiller choisir la date de début "
                            + "de la location (ex : 1 1 2012 correspond au 1 janvier 2012 ) : ");
                    str = reponse.split(" ");
                    jour = Integer.parseInt(str[0]);
                    mois = Integer.parseInt(str[1])-1;
                    année = Integer.parseInt(str[2])-1900;
                    if (jour < 32 && jour > 0 && mois >= 0 && mois < 12)
                    {
                        dateCorrect = true;
                        dateDebut = new Date(année,mois,jour);
                        listeDates.add(dateDebut);
                    }
                    else 
                    {
                        System.out.println("Date incorrecte");               
                    }
                }
                catch (Exception e) {
                System.out.println("Date incorrecte");
                dateCorrect = false;
                }
            }

            dateCorrect = false;

            while(!dateCorrect)
            {
                try{
                    reponse = Saisie.lireChaine("veuiller choisir la date de fin de "
                            + "la location (ex : 1 1 2012 correspond au 1 janvier 2012 ) : ");
                    str = reponse.split(" ");
                    jour = Integer.parseInt(str[0]);
                    mois = Integer.parseInt(str[1])-1;
                    année = Integer.parseInt(str[2]) - 1900;
                    if (jour < 32 && jour > 0 && mois >= 0 && mois < 12)
                    {
                        dateFin = new Date(année,mois,jour);
                        if (dateDebut.before(dateFin))
                        {
                        listeDates.add(dateFin);
                        dateCorrect = true;
                        System.out.println("Date bien saisie");  
                        }
                        else
                        {
                            System.out.println("Date de Fin inférieur à la date de début");     
                        }
                    }
                    else 
                    {
                        System.out.println("Date incorrecte");               
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Date incorrecte"); 
                    dateCorrect = false;
                }
            }
        }
        return listeDates;
    }
    
    public static List<Oeuvre> RechercheOeuvreParNom(Date dateDeb, Date dateFin)
    {
        List<Oeuvre> catalogue = null;
        while(catalogue == null || catalogue.size() == 0)
        {
            String nom = Saisie.lireChaine("Indiquer le nom de l'oeuvre : ");
            catalogue = service.rechercherOeuvreParArtisteEtDate(nom, dateDeb, dateFin);
            AfficherCatalogue(catalogue);
            if(catalogue.size() == 0)
            {
                System.out.println("Aucune oeuvre trouvée");
            }
        }
        return catalogue;
    }
            
    public static List<Oeuvre> RechercheOeuvreParArtistePrix(Date dateDeb, Date dateFin)
    {
        List<Oeuvre> catalogue = null;
        boolean valide = false;
        String reponse = null;
        while(catalogue == null || catalogue.size() == 0)
        {
            while (!valide) 
            {
                reponse = Saisie.lireChaine("Voulez vous rechercher par prix O/N ? ");
                if (reponse.equals("O")  || reponse.equals("N") ) {
                    valide = true;
                }
            }
            String comparateur = "SUP";
            float prix = 0;

            if (reponse.equals("O") ) {
                valide = false;
                while (!valide) {
                    comparateur = Saisie.lireChaine("Veuillez saisir la comparaison "
                            + "du prix (SUP: supérieur, EGAL: egal, INF:inférieur) :");
                    if (comparateur.equals("SUP") || comparateur.equals("INF") || comparateur.equals("EGAL")) {
                        valide = true;
                    }
                    else
                    {
                        System.out.println("Comparaison incorrect");
                    }
                }
                prix = Float.parseFloat(Saisie.lireChaine("Veuillez saisir le prix :"));
            }


            String artiste = Saisie.lireChaine("Veuillez saisir le nom de l'Artiste ('*' pour tous les artistes) :");

            Comparaison comp = Comparaison.valueOf(comparateur);
            catalogue = service.rechercherOeuvreParPrixEtNomEtDate(artiste, prix, comp, dateDeb, dateFin);

            AfficherCatalogue(catalogue);
            if(catalogue.size() == 0)
            {
                System.out.println("Aucune oeuvre trouvée");
                valide = false;

            }
        }
        return catalogue;
    }
    
    public static void ValiderGallerie (List<Oeuvre> catalogue, Client unClient, Date dateDeb, Date dateFin) {
        int prixTotal = 2000;
        Gallerie uneGallerie = new Gallerie(dateDeb,dateFin,prixTotal);
        service.creerGallerie(uneGallerie, unClient, catalogue);
        System.out.println("Gallerie personnelle");
        for(Oeuvre o : catalogue)
        {
            System.out.println(o.getArtiste().getNomArtiste() +'\t' + o.getTitre() 
                    + '\t' + Float.toString(o.getPrix()) + " €");
        }
    }
    
    public static Client Connexion() {
        Client c = null;
        String reponse;

        while(c == null)
        {
            reponse = Saisie.lireChaine("Entrez votre code client : ");
            try
            {
            c = service.connexionClient(Integer.parseInt(reponse));
            System.out.println("Connexion réussie");
            
//            for (Client cli: listeClientsRef) {
//                if (cli.getClientId() == c.getClientId())
//                {
//                    System.out.println ("_________Le code client existe, le test est valide");
//                    break;
//                }
//            }
            
            }
            catch(Exception e)
            {
                System.out.println("Code incorrect");
            }
        }
        return c;
    }
    
    public static void TestArtistes()
    {
        List<Artiste> listeArtistes;
        listeArtistes = service.RechercherTousLesArtistes();
        for(Artiste a : listeArtistes)
        {
            System.out.println(a.getNomArtiste());
        }
    }
    
    public static List<Oeuvre> AffichageOeuvreDispo(Date dateDeb, Date dateFin)
    {
        List<Oeuvre> catalogue = service.rechercherOeuvrePardate(dateDeb, dateFin);
        AfficherCatalogue(catalogue);
        return catalogue;
    }
    
    public static Oeuvre SaisieOeuvre(List<Oeuvre> catalogue)
    {
        boolean valide = false;
        Oeuvre oeuvre = null;
        while(!valide)
        {
            String choix = Saisie.lireChaine("\nVeuillez choisir un numéro : ");
            int index = Integer.parseInt(choix);
            if( index >= 0 && index < catalogue.size())
            {
                valide = true;
                oeuvre = catalogue.get(index);
            }
            else
            {
            System.out.println("Numéro incorrect");
            }
        }
        return oeuvre;
    }
    
    //renvoie vrai si oeuvre n'est pas présent dans catalogue
    public static boolean ComparerOeuvreListeOeuvres(Oeuvre oeuvre, List<Oeuvre> catalogue)
    {
        if(catalogue.size() != 0)
        {
            for(Oeuvre o : catalogue)
            {
                if(o.getOeuvreId() == oeuvre.getOeuvreId())
                {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("------Initialisation de la Base de données ------");
        Initialisation();
        
        List<Oeuvre> catalogue = null;
        Oeuvre oeuvre = null;
        List<Oeuvre> listeOeuvres = new ArrayList<Oeuvre>();
        
        System.out.println("------Démo de connexion ------");
        Client client = Connexion();
        
        
        System.out.println("\n------Démo de création de gallerie ------");
        List<Date> listeDates;
        Date dateDeb = null;
        Date dateFin = null;
        while (catalogue == null || catalogue.isEmpty())
        {
            System.out.println("------Saisie de date de la location ------");
            listeDates = SaisieDate();
            dateDeb = listeDates.get(0);
            dateFin = listeDates.get(1);

            System.out.println("------Affichage des oeuvres disponibles ------");
            catalogue = AffichageOeuvreDispo(dateDeb, dateFin);
            if(catalogue.isEmpty())
            {
                System.out.println("Aucune oeuvre disponible pour ces dates");
            }
        }
        
        System.out.println("------Ajout d'une oeuvre à la gallerie ------");
        oeuvre = SaisieOeuvre(catalogue);
        if(ComparerOeuvreListeOeuvres(oeuvre, listeOeuvres))
        {
            listeOeuvres.add(oeuvre);
            System.out.println("Oeuvre ajoutée");
        }
        else
        {
            System.out.println("Oeuvre déjà ajoutée");
        }
        
        
        System.out.println("------Recherche d'une oeuvre par nom ------");
        catalogue = RechercheOeuvreParNom(dateDeb, dateFin);
        System.out.println("------Ajout d'une oeuvre à la gallerie ------");
        oeuvre = SaisieOeuvre(catalogue);
        if(ComparerOeuvreListeOeuvres(oeuvre, listeOeuvres))
        {
            listeOeuvres.add(oeuvre);
            System.out.println("Oeuvre ajoutée");
        }
        else
        {
            System.out.println("Oeuvre déjà ajoutée");
        }
        
        System.out.println("------Recherche d'une oeuvre par Artiste et par Prix ------");
        catalogue = RechercheOeuvreParArtistePrix(dateDeb, dateFin);
        System.out.println("------Ajout d'une oeuvre à la gallerie ------");
        oeuvre = SaisieOeuvre(catalogue);
        if(ComparerOeuvreListeOeuvres(oeuvre, listeOeuvres))
        {
            listeOeuvres.add(oeuvre);
            System.out.println("Oeuvre ajoutée");
        }
        else
        {
            System.out.println("Oeuvre déjà ajoutée");
        }
        
        System.out.println("------Validation de la gallerie ------");
        ValiderGallerie(listeOeuvres, client, dateDeb, dateFin);
        
        //System.out.println("------Test Recherche par Nom de l'oeuvre ------");
        //TestRechercheParNom();
        
//        System.out.println("------Test Recherche par Artiste et Prix ------");
//        TestRechercheParArtisteEtPrix();
//        
//        System.out.println("------Test de tous les artistes ------");
//        //TestArtistes();      
//        
        
//        System.out.println("------Test de validation de la gallerie ------");
        
    }
    
    public void Old () {
          //Creation d'un client
     /*  Client client = new Client("20 avenue albert einstein", "69100", "Villeurbanne", 0612121212);
        service.creerClient(client);

        Date dateDeb = new Date(112,5,12);
        Date dateFin = new Date(112,5,28);
        Date dateLivraison = new Date(112, 5, 10);
        int prixTotal = 2000;
        Gallerie gallerie = new Gallerie(dateDeb,dateFin,prixTotal,dateLivraison);



        

        Artiste artiste = new Artiste("test ","C'est");
        service.creerArtiste(artiste);

        Peinture oeuvre = new Peinture("Monochrome test","pigment et résine synthétique sur toile",124,"50x150");
        Peinture oeuvre2 = new Peinture("Monochrome jaune","pigment et résine synthétique sur toile",154,"50x150");
        Peinture oeuvre3 = new Peinture("Monochrome marron","pigment et résine synthétique sur toile",154,"50x150");        
        service.creerOeuvre(oeuvre, artiste);
        service.creerOeuvre(oeuvre2, artiste);
        service.creerOeuvre(oeuvre3, artiste);
        List <Oeuvre> lOeuvre = new ArrayList<Oeuvre> ();
        lOeuvre.add(oeuvre);
        lOeuvre.add(oeuvre2);
        lOeuvre.add(oeuvre3);

        service.creerGallerie(gallerie, client, lOeuvre);

        //List<Oeuvre> listeOeuvre2;
        //listeOeuvre2 = service.rechercherOeuvrePardate(new Date(112,4,12), new Date(112,6,28));
        //listeOeuvre2 = service.rechercherOeuvreParNom("Monochrome jau");
        //listeOeuvre2 = service.rechercherOeuvreParPrixEtNom("test", 154, Comparaison.INF);
       /* for(Oeuvre o : listeOeuvre2)
        {
            System.out.println(o.getOeuvreId());
        }*/
        
        
//        Client c = null;
//        String reponse;
//        List<Oeuvre> catalogue = null;
//        while(c == null)
//        {
//            reponse = Saisie.lireChaine("Entrez votre code client : ");
//            try
//            {
//            c = service.connexionClient(Integer.parseInt(reponse));
//            }
//            catch(Exception e)
//            {
//                System.out.println("Code incorrect");                                
//            }
//        }
//        boolean quitter = false;
//        while (!quitter)
//        {
//            reponse = Saisie.lireChaine("\n---MENU---\n"
//                    + "Quitter (0)\n"
//                    + "Creer Gallerie (1)\n"
//                    + "Affichage catalogue (2)\n\n"
//                    + "veuillez choisir un numéro : ");
//            switch (Integer.parseInt(reponse))
//            {
//                case 0 : quitter = true;
//                break;
//                
//                case 1: 
//                {
//                    Gallerie gallerie = new Gallerie();
//                    List<Oeuvre> oeuvre = new ArrayList<Oeuvre>();
//
//                    reponse = Saisie.lireChaine("\n---CREER GALLERIE---\n"
//                        + "Retour à l'accueil (0)\n"    
//                        + "Rechercher par nom d'une oeuvre (1)\n"
//                        + "Rechercher par Artiste et Par Prix (2)\n\n"
//                        + "veuillez choisir un numéro : ");  
//                    switch (Integer.parseInt(reponse))
//                    {
//                        case 0: 
//                            break;
//                        case 1:
//                        {
//                            reponse = Saisie.lireChaine("Indiquer le nom de l'oeuvre : ");
//                            catalogue = service.rechercherOeuvreParNom(reponse);
//                            System.out.println(catalogue.size());
//                            int i = 1;
//                            System.out.println("Revenir à l'accueil (0)");
//                            for(Oeuvre o : catalogue)
//                            {
//                                System.out.println(o.getArtiste().getNomArtiste() +'\t' + o.getTitre() + Float.toString(o.getPrix())+"\t("+i+")");
//                                i++;
//                            }
//                            reponse = Saisie.lireChaine("\nVeuillez choisir un numéro : ");
//                            int index = Integer.parseInt(reponse);
//                            if( index > 0 && index < i)
//                            {
//                                oeuvre.add(catalogue.get(index -1));
//                            }
//                            break;
//                        }
//                        case 2:
//                        {
//                            
//                            break;
//                        }
//                    }
//                    break;                    
//                }
//                
//                case 2:
//                    break;
//                
//                default:
//                    break;
//
//            }
//        }
    }

}
