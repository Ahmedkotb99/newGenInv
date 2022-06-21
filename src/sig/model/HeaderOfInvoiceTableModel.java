
package sig.model;

import sig.view.MainFrame;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ahmed
 */
public class HeaderOfInvoiceTableModel extends AbstractTableModel {

    private ArrayList<HeaderOfInvoice> invoicesArray;
    private String[] columns = {"Invoice Num", "Invoice Date", "Customer Name", "Invoice Total"};
    
    public HeaderOfInvoiceTableModel(ArrayList<HeaderOfInvoice> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }

    @Override
    public int getRowCount() {
        return invoicesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HeaderOfInvoice inv = invoicesArray.get(rowIndex);
        switch (columnIndex) {
            case 0: return inv.getNumberr();
            case 1: return MainFrame.dateFormat.format(inv.getDateOfInvoice());
            case 2: return inv.getCusName();
            case 3: return inv.getInvoiceSum();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
