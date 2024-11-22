package org.example.tp3b;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

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
        return id;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public double getPrix_produit() {
        return prix_produit;
    }

    public String getDate_experiration() {
        return date_experiration;
    }


    public void setPrix_produit(double prix) {
        this.prix_produit = prix;
    }

public void setDate_experiration(String date) {
        this.date_experiration = date;
    }


    @Override
    public String toString() {
        return "Produit{" +"l'id est "+ id +  " nom_produit='"+nom_produit+'\''+", prix_produit="+prix_produit+", date_experiration="+date_experiration+'}';
    }
}
