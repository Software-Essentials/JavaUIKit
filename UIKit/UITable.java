package UIKit;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UITable {

    @SuppressWarnings("serial")
    class UITableRepresentable extends JTable {
    
        private UITable parent;

        public UITableRepresentable(DefaultTableModel model, UITable parent) {
            super(model);
            this.parent = parent;
        }

        public boolean isCellEditable(int row, int column) {
            return parent.isCellEditable(row, column);
        }

        public Class<?> getColumnClass(int column) {
            return parent.getColumnClass(column);
        }

    }

    private UITableRepresentable component;
    
    public UITable(DefaultTableModel model) {
        component = new UITableRepresentable(model, this);
    }

    public boolean isCellEditable(int row, int column) {
        return component.isCellEditable(row, column);
    }

    public Class<?> getColumnClass(int column) {
        return component.getColumnClass(column);
    }

    public void setFillsViewportHeight(boolean fillsViewportHeight) {
        component.setFillsViewportHeight(fillsViewportHeight);
    }

    public int getRowCount() {
        return component.getRowCount();
    }

    public Object getValueAt(int row, int column) {
        return component.getValueAt(row, column);
    }

    // MARK: - Accessibility methods
  
    public JTable accessibilityComponent() {
      return component;
    }
    
}