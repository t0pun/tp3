package org.example.tp3b;

public class UserProfile {

    private final String userId;
    private int readOperations;
    private int writeOperations;
    private int searchOperations;

    public UserProfile(String userId) {
        this.userId = userId;
    }

    public void incrementReadOperations() {
        readOperations++;
    }

    public void incrementWriteOperations() {
        writeOperations++;
    }

    public void incrementSearchOperations() {
        searchOperations++;
    }

    // Getters pour la sérialisation JSON
    public String getUserId() {
        return userId;
    }

    public int getReadOperations() {
        return readOperations;
    }

    public int getWriteOperations() {
        return writeOperations;
    }

    public int getSearchOperations() {
        return searchOperations;
    }
}