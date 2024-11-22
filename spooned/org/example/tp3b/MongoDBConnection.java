package org.example.tp3b;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
public class MongoDBConnection {
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        logger.info("Méthode getDatabase exécutée.");;
        if (database == null) {
            MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.3.2");
            database = mongoClient.getDatabase("depot");
        }
        return database;
    }

    private static final Logger logger = LoggerFactory.getLogger(MongoDBConnection.class);
}