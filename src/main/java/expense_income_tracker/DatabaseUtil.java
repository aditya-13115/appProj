package expense_income_tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/app_proj";
    private static final String USER = "root"; // Your database username
    private static final String PASSWORD = "aditya"; // Your database password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
