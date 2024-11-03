package expense_income_tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton registerButton;

    public UserLogin() {
        setTitle("User Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginButton.addActionListener(this::loginUser);
        registerButton.addActionListener(this::registerUser);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    private void loginUser(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (validateLogin(username, password)) {
            new ExpensesIncomesTracker(username); // Open main tracker window
            dispose(); // Close login window
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password); // Consider hashing passwords in production!

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if a matching user is found
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error during login: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // No user found
    }

    private void registerUser(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty", "Registration Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
        } else {
            if (registerNewUser(username, password)) {
                JOptionPane.showMessageDialog(this, "User registered successfully! You can now log in.", "Registration Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isUsernameTaken(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if the username is already taken
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error during username check: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // No user found
    }

    private boolean registerNewUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password); // Consider hashing passwords in production!
            statement.executeUpdate();
            return true; // Registration successful
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error during registration: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Registration failed
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserLogin::new);
    }
}
