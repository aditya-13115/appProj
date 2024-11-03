package expense_income_tracker;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExpenseIncomeTableModel extends AbstractTableModel {
    private final List<ExpenseIncomeEntry> entries; // This is your list of entries
    private final String[] columnNames = {"Date", "Description", "Amount", "Type"};

    public ExpenseIncomeTableModel() {
        entries = new ArrayList<>();
    }

    public void addEntry(ExpenseIncomeEntry entry) {
        entries.add(entry);
        fireTableRowsInserted(entries.size() - 1, entries.size() - 1);
    }

    public void editEntry(int rowIndex, ExpenseIncomeEntry updatedEntry) {
        entries.set(rowIndex, updatedEntry);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public void removeEntry(int rowIndex) {
        entries.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void sortByColumn(int columnIndex) {
        Collections.sort(entries, new Comparator<ExpenseIncomeEntry>() {
            @Override
            public int compare(ExpenseIncomeEntry e1, ExpenseIncomeEntry e2) {
                Object value1 = getColumnValue(e1, columnIndex);
                Object value2 = getColumnValue(e2, columnIndex);

                if (value1 instanceof Comparable && value2 instanceof Comparable) {
                    return ((Comparable<Object>) value1).compareTo(value2);
                }
                return 0; // or throw an exception based on your use case
            }
        });
        fireTableDataChanged();
    }

    // New method to retrieve the entire entry
    public ExpenseIncomeEntry getEntryAt(int rowIndex) {
        return entries.get(rowIndex); // Return the entire entry
    }

    private Object getColumnValue(ExpenseIncomeEntry entry, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return entry.getDate();
            case 1:
                return entry.getDescription();
            case 2:
                return entry.getAmount();
            case 3:
                return entry.getType();
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() { 
        return entries.size(); 
    }

    @Override
    public int getColumnCount() { 
        return columnNames.length; 
    }

    @Override
    public String getColumnName(int column) { 
        return columnNames[column]; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ExpenseIncomeEntry entry = entries.get(rowIndex);
        return getColumnValue(entry, columnIndex);
    }
}
