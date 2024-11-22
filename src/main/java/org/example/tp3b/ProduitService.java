package org.example.tp3b;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class ProduitService {

    private MongoCollection<Document> produitCollection;

    public ProduitService(MongoDatabase database) {
        this.produitCollection = database.getCollection("produit");
    }

    public ProduitService(){}

    public void createProduit(Produit produit) {
        Document doc = new Document("nom_produit", produit.getNom_produit())
                .append("prix_produit", produit.getPrix_produit())
                .append("date_expiration", produit.getDate_experiration());
        produitCollection.insertOne(doc);
    }

    public Produit getProduitById(String id) {
        Document doc = produitCollection.find(eq("id", id)).first();
        if (doc != null) {
            return new Produit(
                    doc.getString("nom_produit"),
                    doc.getInteger("prix_produit"),
                    doc.getString("date_expiration")
            );
        }
        return null; // ou lancer une exception si l'utilisateur n'existe pas
    }

    // Update: Mettre à jour un utilisateur
    public void updateProduit(String id, Produit produit) {
        Document updatedDoc = new Document("nom_produit", produit.getNom_produit())
                .append("prix_produit", produit.getPrix_produit())
                .append("date_expiration", produit.getDate_experiration());
        produitCollection.updateOne(eq("id", id), new Document("$set", updatedDoc));
    }

    // Delete: Supprimer un utilisateur
    public void deleteProduit(String id) {
        produitCollection.deleteOne(eq("id", id));
    }

}
