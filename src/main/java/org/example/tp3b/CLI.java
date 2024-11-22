package org.example.tp3b;

import java.util.Date;
import java.util.Scanner;

public class CLI {
    public static <string> void main(String[] args) throws ProduitNonTrouveException {
        Scanner scanner = new Scanner(System.in);
        Depot produitRepo = new Depot(); // Supposons que tu aies un repo produit

        while (true) {
            System.out.println("1. Afficher les produits");
            System.out.println("2. Ajouter un produit");
            System.out.println("3. Supprimer un produit");
            System.out.println("4. Mettre à jour un produit");
            System.out.println("5. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choix) {
                case 1:
                    produitRepo.getDepot_produit();
                    break;
                case 2:
                    System.out.println("Nom du produit : ");
                    String nom = scanner.nextLine();
                    System.out.println("Prix du produit : ");
                    double prix = scanner.nextDouble();
                    scanner.nextLine(); // Consomme la nouvelle ligne
                    System.out.println("Date d'expiration (yyyy-mm-dd) : ");
                    String dateExp = scanner.nextLine();
                    produitRepo.ajouter_produit(new Produit(nom, prix, dateExp));
                    break;
                case 3:
                    System.out.println("ID du produit à supprimer : ");
                    int id = scanner.nextInt();
                    produitRepo.supprimer_produit(id);
                    break;
                case 4:
                    // Similaire pour mettre à jour un produit
                    break;
                case 5:
                    System.out.println("Fermeture du programme.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }
}
