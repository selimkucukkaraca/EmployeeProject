package service;

import dao.DatabaseConnection;

public class AdminService {

    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public boolean login(String username, String password) {
        return databaseConnection.login(username, password);
    }
}
