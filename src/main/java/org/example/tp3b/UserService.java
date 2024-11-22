package org.example.tp3b;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class UserService {
    private MongoCollection<Document> userCollection;

    public UserService(MongoDatabase database) {
        this.userCollection = database.getCollection("user");
    }

    public void createUser(User user) {
        Document doc = new Document("id", user.getID())
                .append("name", user.getName())
                .append("age", user.getAge())
                .append("email", user.getEmail())
                .append("password", user.getPassword());
        userCollection.insertOne(doc);
    }

    public User getUserById(String id) {
        Document doc = userCollection.find(eq("id", id)).first();
        if (doc != null) {
            return new User(
                    doc.getInteger("id"),
                    doc.getString("name"),
                    doc.getInteger("age"),
                    doc.getString("email"),
                    doc.getString("password")
            );
        }
        return null; // ou lancer une exception si l'utilisateur n'existe pas
    }

    // Update: Mettre à jour un utilisateur
    public void updateUser(String id, User user) {
        Document updatedDoc = new Document("id", user.getID())
                .append("name", user.getName())
                .append("age", user.getAge())
                .append("email", user.getEmail())
                .append("password", user.getPassword()); // Pense à hacher le mot de passe
        userCollection.updateOne(eq("id", id), new Document("$set", updatedDoc));
    }

    // Delete: Supprimer un utilisateur
    public void deleteUser(String id) {
        userCollection.deleteOne(eq("id", id));
    }

}
