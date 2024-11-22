package org.example.tp3b;
import org.slf4j.Logger;
public class UserProfile {
    private final String userId;

    private int readOperations;

    private int writeOperations;

    private int searchOperations;

    public UserProfile(String userId) {
        this.userId = userId;
    }

    public void incrementReadOperations() {
        logger.info("Méthode incrementReadOperations exécutée.");;
        readOperations++;
    }

    public void incrementWriteOperations() {
        logger.info("Méthode incrementWriteOperations exécutée.");;
        writeOperations++;
    }

    public void incrementSearchOperations() {
        logger.info("Méthode incrementSearchOperations exécutée.");;
        searchOperations++;
    }

    // Getters pour la sérialisation JSON
    public String getUserId() {
        logger.info("Méthode getUserId exécutée.");;
        return userId;
    }

    public int getReadOperations() {
        logger.info("Méthode getReadOperations exécutée.");;
        return readOperations;
    }

    public int getWriteOperations() {
        logger.info("Méthode getWriteOperations exécutée.");;
        return writeOperations;
    }

    public int getSearchOperations() {
        logger.info("Méthode getSearchOperations exécutée.");;
        return searchOperations;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserProfile.class);
}