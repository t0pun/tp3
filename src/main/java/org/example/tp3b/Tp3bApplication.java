package org.example.tp3b;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spoon.reflect.declaration.CtClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
public class Tp3bApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(Tp3bApplication.class, args);

        // Récupérer la base de données depuis la classe MongoDBConnection
        MongoDatabase database = MongoDBConnection.getDatabase();

        // Récupérer la collection pour effectuer des opérations
        MongoCollection<Document> collection = database.getCollection("depots");
        MongoCollection<Document> collectionProduit = database.getCollection("produit");


        // Exemple d'insertion de produit
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateExpiration = formatter.parse("12-03-2012");


        Produit p = new Produit("pigeon", 12.,"2000-03-12");
        Produit p2 = new Produit("cochon", (double) 22.,"2001-03-12");
        System.out.println(p.getPrix_produit());

        p.setPrix_produit(10);
        System.out.println(p.getPrix_produit());
        System.out.println(p.toString());




        /*
        Document document1 = new Document ("nom",p.getNom_produit())
                .append("prix",p.getPrix_produit())
                .append("date_expiration",p.getDate_experiration());
        Document document2 = new Document ("nom",p2.getNom_produit())
                .append("prix",p2.getPrix_produit())
                .append("date_expiration",p2.getDate_experiration());
        collection.insertOne(document2);
        collectionProduit.insertOne(document1);
        //collectionProduit.insertOne(document2);

         */

        System.out.println("Insertion réussie !");


    }
}
