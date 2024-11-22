package org.example.tp3b;
import org.slf4j.Logger;
public class Produit {
    private static int cId;

    private int id;

    private String nom_produit;

    private double prix_produit;

    private String date_experiration;

    public Produit(String nom_produit, double prix_produit, String date_experiration) {
        this.id = ++cId;
        this.nom_produit = nom_produit;
        this.prix_produit = prix_produit;
        this.date_experiration = date_experiration;
    }

    public int getId() {
        logger.info("Méthode getId exécutée.");;
        return id;
    }

    public String getNom_produit() {
        logger.info("Méthode getNom_produit exécutée.");;
        return nom_produit;
    }

    public double getPrix_produit() {
        logger.info("Méthode getPrix_produit exécutée.");;
        return prix_produit;
    }

    public String getDate_experiration() {
        logger.info("Méthode getDate_experiration exécutée.");;
        return date_experiration;
    }

    public void setPrix_produit(double prix) {
        logger.info("Méthode setPrix_produit exécutée.");;
        this.prix_produit = prix;
    }

    public void setDate_experiration(String date) {
        logger.info("Méthode setDate_experiration exécutée.");;
        this.date_experiration = date;
    }

    @Override
    public String toString() {
        logger.info("Méthode toString exécutée.");;
        return ((((((((("Produit{" + "l'id est ") + id) + " nom_produit='") + nom_produit) + '\'') + ", prix_produit=") + prix_produit) + ", date_experiration=") + date_experiration) + '}';
    }

    private static final Logger logger = LoggerFactory.getLogger(Produit.class);
}