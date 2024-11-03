package expense_income_tracker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ExpensesIncomesTracker extends JFrame {
    private final ExpenseIncomeTableModel tableModel;
    private final JTable table;
    private final JTextField dateField;
    private final JTextField descriptionField;
    private final JTextField amountField;
    private final JComboBox<String> typeCombobox;
    private final JButton addButton;
    private final JButton editButton;
    private final JButton removeButton;
    private final JLabel balanceLabel;
    private final JLabel welcomeLabel; // New label for welcome message
    private double balance;

    // Constructor modified to accept username
    public ExpensesIncomesTracker(String username) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        tableModel = new ExpenseIncomeTableModel();
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(60, 130, 200));
        table.getTableHeader().setForeground(Color.WHITE);

        dateField = new JTextField(10);
        descriptionField = new JTextField(20);
        amountField = new JTextField(10);
        typeCombobox = new JComboBox<>(new String[]{"Expense", "Income"});
        
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        removeButton = new JButton("Remove");

        balanceLabel = new JLabel("Balance: Rs " + balance);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        balanceLabel.setForeground(new Color(0, 128, 0));
        
        // Create welcome label
        welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(new Color(34, 139, 34)); // Green color

        addButton.setBackground(new Color(34, 139, 34));
        addButton.setForeground(Color.WHITE);
        editButton.setBackground(new Color(70, 130, 180));
        editButton.setForeground(Color.WHITE);
        removeButton.setBackground(new Color(220, 20, 60));
        removeButton.setForeground(Color.WHITE);

        addButton.addActionListener(e -> addEntry());
        editButton.addActionListener(e -> editEntry());
        removeButton.addActionListener(e -> removeEntry());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 4, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("Date (YYYY-MM-DD)"));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Description"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Amount"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Type"));
        inputPanel.add(typeCombobox);
        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(removeButton);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(245, 245, 245));
        bottomPanel.add(balanceLabel);
        
        // Add welcome label to the bottom panel
        bottomPanel.add(welcomeLabel);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setTitle("Budget Tracker");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addEntry() {
        // Your existing implementation for adding an entry
    }

    private void editEntry() {
        // Your existing implementation for editing an entry
    }

    private void removeEntry() {
        // Your existing implementation for removing an entry
    }
}
