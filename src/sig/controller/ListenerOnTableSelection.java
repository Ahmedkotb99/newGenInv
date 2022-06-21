
package sig.controller;

import sig.model.HeaderOfInvoice;
import sig.model.LinesOfInvoice;
import sig.model.LinesOfInvoiceTableModel;
import sig.view.MainFrame;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ahmed
 */
public class ListenerOnTableSelection implements ListSelectionListener {

    private MainFrame frame;

    public ListenerOnTableSelection(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int SelectedInvoice = frame.getInvoiceTableMain().getSelectedRow();
        System.out.println("Invoice selected: " + SelectedInvoice);
        if (SelectedInvoice != -1) {
            HeaderOfInvoice SelectedInvoiceSecon = frame.getInvoicesArray().get(SelectedInvoice);
            ArrayList<LinesOfInvoice> lines = SelectedInvoiceSecon.getLines();
            LinesOfInvoiceTableModel lineTableModel = new LinesOfInvoiceTableModel(lines);
            frame.setLinesArray(lines);
            frame.getInvoicedetailLabel().setModel(lineTableModel);
            frame.getCutomerNameLabel().setText(SelectedInvoiceSecon.getCusName());
            frame.getInvoiceNumLabel().setText("" + SelectedInvoiceSecon.getNumberr());
            frame.getInvoiceTotalLabel().setText("" + SelectedInvoiceSecon.getInvoiceSum());
            frame.getInvoiceDateLable().setText(MainFrame.dateFormat.format(SelectedInvoiceSecon.getDateOfInvoice()));
        }
    }

}
