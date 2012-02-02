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
    
    public static Service service = new Service();
    
    public static void Initialisation()
    {
        
        Client client1 = new Client("20 avenue albert einstein", "69100", "Villeurbanne", "0612121212", "Jean", "Dupont");
        service.creerClient(client1);
        Client client2 = new Client("228 cours emile zola", "69100", "Villeurbanne", "0648121212", "Fred", "Dussant");
        service.creerClient(client2);
        
        Date dateDeb1 = new Date(112,5,12);
        Date dateFin1 = new Date(112,5,28);
        Galerie galerie1 = new Galerie(dateDeb1,dateFin1);
        
        Date dateDeb2 = new Date(112,4,5);
        Date dateFin2 = new Date(112,4,29);       
        Galerie galerie2 = new Galerie(dateDeb2, dateFin2);

        Artiste artiste1 = new Artiste("Yves", "Kein","Peintre contemporain");
        service.creerArtiste(artiste1);
        
        Artiste artiste2 = new Artiste("Jean", "Renoir", "Peinre impressioniste");
        service.creerArtiste(artiste2);

        Peinture oeuvre = new Peinture("Monochrome test","pigment et résine synthétique sur toile",124,"50x150");
        Peinture oeuvre2 = new Peinture("Monochrome jaune","pigment et résine synthétique sur toile",154,"50x150");
        Sculpture oeuvre3 = new Sculpture("Monochrome marron","pigment et résine synthétique sur toile",154,"50x150");
        Peinture oeuvre4 = new Peinture("Bal du moulin de la Galette", "huile sur toile",10000 , "1,31x1,75");
        service.creerOeuvre(oeuvre, artiste1);
        service.creerOeuvre(oeuvre2, artiste1);
        service.creerOeuvre(oeuvre3, artiste1);
        service.creerOeuvre(oeuvre4, artiste2);
        
        List <Oeuvre> lOeuvre1 = new ArrayList<Oeuvre> ();
        lOeuvre1.add(oeuvre);
        lOeuvre1.add(oeuvre2);
        lOeuvre1.add(oeuvre3);
        
        service.creerGalerie(galerie1, client1, lOeuvre1);
        
        
        List <Oeuvre> lOeuvre2 = new ArrayList<Oeuvre> ();
        lOeuvre2.add(oeuvre2);
        
        service.creerGalerie(galerie2, client2, lOeuvre2);
        
    }
    
    public static void AfficherListeOeuvres(List<Oeuvre> listeOeuvres)
    {
        int i = 0;
        System.out.println("\nListe d'Oeuvres");
        for(Oeuvre o : listeOeuvres)
        {
            System.out.println(o.getNom() 
                    + '\t' + o.getArtiste().getNom() +'\t' + Float.toString(o.getPrix()) + " €" + " (" + i + ")");
            i++;
        }
    }
    
    public static void AfficherCatalogue()
    {
        List<Peinture> listePeintures;
        listePeintures = service.rechercherToutesPeintures();
        System.out.println("------Peinture------");
        for (Peinture p : listePeintures)
        {
            System.out.println(p.getNom() + "\t" + p.getArtiste().getNom() 
                    + "\t" + p.getCarac() + "\t" + p.getDimension() + "\t" + p.getPrix() + " €");
        }
        List<Sculpture> listeSculptures;
        listeSculptures = service.rechercherToutesSculptures();
        System.out.println("------Sculpture------");
        for (Sculpture s : listeSculptures)
        {
            System.out.println(s.getNom() + "\t" + s.getArtiste().getNom() 
            + "\t" + s.getCarac() + "\t" + s.getDimension() + "\t" + s.getPrix() + " €");
        }
    }

    
    public static List<Date> SaisieDate()
    {         
        List<Date> listeDates = new ArrayList<Date>();
        boolean dateCorrect = false;
        while(!dateCorrect)
        {           
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
        List<Oeuvre> listeOeuvres = null;
        while(listeOeuvres == null || listeOeuvres.size() == 0)
        {
            String nom = Saisie.lireChaine("Indiquer le nom de l'oeuvre : ");
            listeOeuvres = service.rechercherOeuvreParNomDate(nom, dateDeb, dateFin);
            AfficherListeOeuvres(listeOeuvres);
            if(listeOeuvres.size() == 0)
            {
                System.out.println("Aucune oeuvre trouvée");
            }
        }
        return listeOeuvres;
    }
            
    public static List<Oeuvre> RechercheOeuvreParArtistePrix(Date dateDeb, Date dateFin)
    {
        List<Oeuvre> listeOeuvres = null;
        boolean valide = false;
        String reponse = null;
        while(listeOeuvres == null || listeOeuvres.size() == 0)
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

            List<Artiste> listeArtistes = AfficherTousArtistes();
            int idArtiste = SaisieArtiste(listeArtistes);

            Comparaison comp = Comparaison.valueOf(comparateur);
            listeOeuvres = service.rechercherOeuvreParPrixArtisteDate(idArtiste, prix, comp, dateDeb, dateFin);

            AfficherListeOeuvres(listeOeuvres);
            if(listeOeuvres.size() == 0)
            {
                System.out.println("Aucune oeuvre trouvée");
                valide = false;

            }
        }
        return listeOeuvres;
    }
    
    public static List<Artiste> AfficherTousArtistes()
    {
        List<Artiste> listeArtistes;
        listeArtistes = service.rechercherTousArtistes();
        System.out.println("\nListe d'Artistes");
        System.out.println("Tous (0)");
        int i = 1;
        for (Artiste a : listeArtistes)
        {
            System.out.println(a.getPrenom() + " " + a.getNom() + " (" + i + ")");
            i++;
        }
        return listeArtistes;
    }
    
    public static int SaisieArtiste(List<Artiste> listeArtistes)
    {
        boolean valide = false;
        int idArtiste = -1;
        while(!valide)
        {
            String choix = Saisie.lireChaine("\nVeuillez choisir un numéro : ");
            int index = Integer.parseInt(choix);
            if( index >= 0 && index < listeArtistes.size() + 1)
            {
                valide = true;
                if(index != 0)
                {
                    idArtiste = listeArtistes.get(index - 1).getIdArtiste();                
                }
            }
            else
            {
            System.out.println("Numéro incorrect");
            }
        }
        return idArtiste;
    }
    
    public static void ValiderGalerie (List<Oeuvre> listeOeuvres, Client unClient, Date dateDeb, Date dateFin) {
        Galerie uneGalerie = new Galerie(dateDeb,dateFin);
        service.creerGalerie(uneGalerie, unClient, listeOeuvres);
        System.out.println("Galerie personnelle");
        for(Oeuvre o : listeOeuvres)
        {
            System.out.println(o.getNom() 
                    + '\t' + o.getArtiste().getNom() +'\t' + Float.toString(o.getPrix()) + " €");
        }
        System.out.println("");
        System.out.println("Prix Total : " + uneGalerie.getPrixTotal() + " €");
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
    
    public static List<Oeuvre> AffichageOeuvreDispo(Date dateDeb, Date dateFin)
    {
        List<Oeuvre> listeOeuvres = service.rechercherOeuvreParDate(dateDeb, dateFin);
        AfficherListeOeuvres(listeOeuvres);
        return listeOeuvres;
    }
    
    public static Oeuvre SaisieOeuvre(List<Oeuvre> listeOeuvres)
    {
        boolean valide = false;
        Oeuvre oeuvre = null;
        while(!valide)
        {
            String choix = Saisie.lireChaine("\nVeuillez choisir un numéro : ");
            int index = Integer.parseInt(choix);
            if( index >= 0 && index < listeOeuvres.size())
            {
                valide = true;
                oeuvre = listeOeuvres.get(index);
            }
            else
            {
            System.out.println("Numéro incorrect");
            }
        }
        return oeuvre;
    }
    
    //renvoie vrai si oeuvre n'est pas présent dans listeOeuvres
    public static boolean ComparerOeuvreListeOeuvres(Oeuvre oeuvre, List<Oeuvre> listeOeuvres)
    {
        if(listeOeuvres.size() != 0)
        {
            for(Oeuvre o : listeOeuvres)
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
    
    public static void CreationOeuvre()
    {
       boolean valide = false;
       Oeuvre oeuvre = null;
       String nom = Saisie.lireChaine("indiquer le nom de l'oeuvre : "); 
       
       List<Artiste> listeArtistes = service.rechercherTousArtistes();
       System.out.println("\nListe d'Artistes");
       int i = 0;
       for (Artiste a : listeArtistes)
        {
            System.out.println(a.getPrenom() + " " + a.getNom() + " (" + i + ")");
            i++;
        }
       
       Artiste artiste = null;
        while(!valide)
        {
            String choix = Saisie.lireChaine("\nVeuillez choisir un artiste : ");
            int index = Integer.parseInt(choix);
            if( index >= 0 && index < listeArtistes.size())
            {
                valide = true;
                    artiste = listeArtistes.get(index);                
            }
            else
            {
            System.out.println("Numéro incorrect");
            }
        }
       valide = false; 
       
       
       String carac = Saisie.lireChaine("indiquer la caractéristique de l'oeuvre : "); 
       String dimension = Saisie.lireChaine("indiquer la dimension de l'oeuvre : "); 
       float prix = Integer.parseInt(Saisie.lireChaine("indiquer le prix de l'oeuvre : ")); 
       while(!valide)
       {
            String choix = Saisie.lireChaine("Voulez-vous créer une peinture ou sculpture(P/S) : "); 
            if(!choix.equals("P") && !choix.equals("S"))
            {
                System.out.println("Choix incorrect");
            }
            else if(choix.equals("P"))
            {
                oeuvre = new Peinture(nom, carac, prix, dimension);
                valide = true;
            }
            else
            {
                valide =true;
                oeuvre = new Sculpture(nom, carac, prix, dimension); 
            }
       }
       service.creerOeuvre(oeuvre, artiste);   
       System.out.println("Oeuvre crée");
    }
    
    public static void AffichagePlanningOeuvre(Oeuvre oeuvre)
    {
        int moisDeb;
        int anneeDeb;
        int moisFin;
        int anneeFin;
        String dateDeb;
        String dateFin;
        System.out.println("Plannig");
        System.out.println("Oeuvre : " + oeuvre.getNom() + ", ID : " + oeuvre.getOeuvreId());
        for(Galerie g : oeuvre.getListeGaleries())
        {
            moisDeb = g.getDateDebut().getMonth()+ 1;
            anneeDeb =g.getDateDebut().getYear() + 1900;
            dateDeb = g.getDateDebut().getDate() + "/" + moisDeb + "/" + anneeDeb;
            moisFin = g.getDateFin().getMonth()+ 1;
            anneeFin =g.getDateFin().getYear() + 1900;
            dateFin = g.getDateFin().getDate() + "/" + moisFin + "/" + anneeFin;
            System.out.println(g.getGalerieId() + "\t" + dateDeb + "\t" 
                    + dateFin + "\t" + g.getClient().getNom() + "\t" + g.getClient().getPrenom());
        }
    }
    
    public static void AffichagePlanning()
    {
        int idOeuvre = Integer.parseInt(Saisie.lireChaine("Indiquer l'ID de l'oeuvre : "));
        Oeuvre oeuvre = service.rechercherOeuvreParId(idOeuvre);
        AffichagePlanningOeuvre(oeuvre);
        
        System.out.println("");
        String nom = Saisie.lireChaine("Indiquer le nom de l'oeuvre : ");
        List<Oeuvre> listeOeuvres = service.rechercherOeuvreParNom(nom);
        AfficherListeOeuvres(listeOeuvres);
        oeuvre = SaisieOeuvre(listeOeuvres);
        AffichagePlanningOeuvre(oeuvre);
    }

    public static void main(String[] args) {
        System.out.println("------Initialisation de la Base de données ------");
        Initialisation();
        
        List<Oeuvre> catalogue = null;
        Oeuvre oeuvre = null;
        List<Oeuvre> listeOeuvres = new ArrayList<Oeuvre>();
        
        System.out.println("------Démo de connexion ------");
        Client client = Connexion();
        
        
        System.out.println("\n------Démo de création de galerie ------");
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
        
        System.out.println("------Ajout d'une oeuvre à la galerie ------");
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
        System.out.println("------Ajout d'une oeuvre à la galerie ------");
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
        System.out.println("------Ajout d'une oeuvre à la galerie ------");
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
        
        System.out.println("------Validation de la galerie ------");
        ValiderGalerie(listeOeuvres, client, dateDeb, dateFin);
        
        System.out.println("\n");
        System.out.println("------Démo de l'affichage du catalogue ------");
        AfficherCatalogue();
        
        System.out.println("\n");
        System.out.println("------Démo de la création d'une oeuvre ------");
        CreationOeuvre();
        
        System.out.println("\n");
        System.out.println("------Démo de l'affichage du planning d'une oeuvre ------");
        AffichagePlanning();
 

    }

}
