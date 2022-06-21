
package sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ahmed
 */
public class LinesOfInvoiceTableModel extends AbstractTableModel {

    private ArrayList<LinesOfInvoice> LinesDetailArray;
    private String[] ColumnsMain = {"Item Name", "Unit Price", "Count", "Line Total"};

    public LinesOfInvoiceTableModel(ArrayList<LinesOfInvoice> LinesMainArrayy) {
        this.LinesDetailArray = LinesMainArrayy;
    }

    @Override
    public int getRowCount() {
        return LinesDetailArray == null ? 0 : LinesDetailArray.size();
    }

    @Override
    public int getColumnCount() {
        return ColumnsMain.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (LinesDetailArray == null) {
            return "";
        } else {
            LinesOfInvoice line = LinesDetailArray.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return line.getItem();
                case 1:
                    return line.getPrice();
                case 2:
                    return line.getCount();
                case 3:
                    return line.getSingleLineSum();
                default:
                    return "";
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return ColumnsMain[column];
    }

}
