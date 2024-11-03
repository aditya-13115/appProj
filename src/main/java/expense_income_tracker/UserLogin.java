package expense_income_tracker;

import javax.swing.*;
import java.awt.*;

public class UserLogin extends JFrame {
    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;
    private JTextField signupUsernameField;
    private JPasswordField signupPasswordField;

    public UserLogin() {
        setTitle("User Login and Signup");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(new Color(240, 240, 240));

        JTabbedPane tabbedPane = new JTabbedPane();

        // Create a UI theme
        Color backgroundColor = new Color(230, 230, 250);
        Color buttonColor = new Color(100, 149, 237);
        Color labelColor = new Color(0, 0, 128);
        Font font = new Font("Arial", Font.BOLD, 14);

        // Login Panel
        JPanel loginPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        loginPanel.setBackground(backgroundColor);
        loginUsernameField = new JTextField(15);
        loginPasswordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(loginUsernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(loginPasswordField);
        loginPanel.add(new JLabel(""));
        loginPanel.add(loginButton);

        // Set styles for login panel components
        for (Component comp : loginPanel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setForeground(labelColor);
                ((JLabel) comp).setFont(font);
            }
        }
        loginButton.setBackground(buttonColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(font);
        loginButton.addActionListener(e -> login());

        // Signup Panel
        JPanel signupPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        signupPanel.setBackground(backgroundColor);
        signupUsernameField = new JTextField(15);
        signupPasswordField = new JPasswordField(15);
        JButton signupButton = new JButton("Signup");

        signupPanel.add(new JLabel("Username:"));
        signupPanel.add(signupUsernameField);
        signupPanel.add(new JLabel("Password:"));
        signupPanel.add(signupPasswordField);
        signupPanel.add(new JLabel(""));
        signupPanel.add(signupButton);

        // Set styles for signup panel components
        for (Component comp : signupPanel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setForeground(labelColor);
                ((JLabel) comp).setFont(font);
            }
        }
        signupButton.setBackground(buttonColor);
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(font);
        signupButton.addActionListener(e -> signup());

        // Add panels to tabbed pane
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Signup", signupPanel);

        add(tabbedPane);
        setVisible(true);
    }

    private void login() {
        String username = loginUsernameField.getText();
        String password = new String(loginPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Perform login validation (e.g., with a database)
        if (username.equals("testuser") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Login successful");
            new ExpensesIncomesTracker(username); // Pass username to the main application
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void signup() {
        String username = signupUsernameField.getText();
        String password = new String(signupPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Here you would typically check if the username already exists in a database

        // Perform signup action (e.g., save to database)
        JOptionPane.showMessageDialog(this, "Signup successful");
        new ExpensesIncomesTracker(username); // Pass username to the main application
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserLogin::new);
    }
}
