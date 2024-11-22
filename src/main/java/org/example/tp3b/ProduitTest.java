package org.example.tp3b;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class ProduitTest {

    private static final Logger logger = LoggerFactory.getLogger(ProduitTest.class);
    private static final List<Produit> produits = new ArrayList<>();
    private static final Random random = new Random();
    private static final Map<String, UserProfile> userProfiles = new HashMap<>();

    private static final MongoDatabase database = MongoDBConnection.getDatabase(); // Utilise la variable déjà initialisée
    private static final MongoCollection<Document> produitsCollection = database.getCollection("produit");

    public static void main(String[] args) {

        long count = produitsCollection.countDocuments();
        System.out.println(count);

        for (int i = 0; i < 10; i++) {
            performRandomOperation("User1");
        }

        // Enregistrer les profils des utilisateurs en format JSON
        saveUserProfilesToJSON();

    }

    private static void performRandomOperation(String userId) {
        int operation = random.nextInt(3); // 3 types d'opérations différentes
        UserProfile profile = userProfiles.computeIfAbsent(userId, k -> new UserProfile(userId));

        switch (operation) {
            case 0:
                readProducts();
                profile.incrementReadOperations();
                break;
            case 1:
                updateRandomProductPrice();
                profile.incrementWriteOperations();
                break;
            case 2:
                searchMostExpensiveProduct();
                profile.incrementSearchOperations();
                break;
            default:
                logger.warn("Opération non reconnue !");
        }
    }

    private static void readProducts() {
        logger.info("Lecture des produits depuis la base de données :");

        // Parcours des produits dans la collection "produits" de la base de données
        for (Document doc : produitsCollection.find()) {
            String nomProduit = doc.getString("nom");
            double prixProduit = doc.getDouble("prix");
            String dateExpiration = doc.getString("date_expiration");
            logger.info("Produit : " + nomProduit + " - Prix : " + prixProduit + " - Expiration : " + dateExpiration);
        }
    }



    private static void updateRandomProductPrice() {
        List<String> nomsProduits = new ArrayList<>();
        produitsCollection.find().forEach(document -> {
            String nom = document.getString("nom");
            if (nom != null) {
                nomsProduits.add(nom);
            }
        });

        // Vérifie s'il y a des produits disponibles
        if (nomsProduits.isEmpty()) {
            logger.warn("Aucun produit trouvé dans la collection pour la mise à jour.");
            return;
        }
        Random random = new Random();
        String nomProduit = nomsProduits.get(random.nextInt(nomsProduits.size()));

        double newPrice = Math.random();


        produitsCollection.updateOne(
                Filters.eq("nom", nomProduit), // Filtre pour trouver le produit par son nom
                Updates.set("prix", newPrice)  // Mise à jour du prix
        );


        logger.info("Mise à jour du prix du produit " + nomProduit + " - Nouveau prix : " + newPrice);
    }

    private static void searchMostExpensiveProduct() {
        // Utilisation de l'agrégation pour trier les produits par prix décroissant
        AggregateIterable<Document> result = produitsCollection.aggregate(Arrays.asList(
                new Document("$sort", new Document("prix_produit", -1)), // Trier par prix décroissant
                new Document("$limit", 1) // Limiter à un seul produit (le plus cher)
        ));

        Document maxProduit = result.first();

        if (maxProduit != null) {
            String nomProduit = maxProduit.getString("nom");
            double prixProduit = maxProduit.getDouble("prix");
            String dateExpiration = maxProduit.getString("date_expiration");
            logger.info("Produit le plus cher : " + nomProduit + " - Prix : " + prixProduit + " - Expiration : " + dateExpiration);
        } else {
            logger.warn("Aucun produit trouvé.");
        }
    }

    private static void saveUserProfilesToJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("user_profiles.json"), userProfiles);
            logger.info("Profils des utilisateurs enregistrés avec succès dans user_profiles.json");
        } catch (IOException e) {
            logger.error("Erreur lors de l'enregistrement des profils des utilisateurs en JSON", e);
        }
    }
}
