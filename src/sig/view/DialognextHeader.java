
package sig.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author ahmed
 */
public class DialognextHeader extends JDialog {
    private JTextField customernamebar;
    private JTextField DateofInvoiceBar;
    private JLabel CustomerNameLabel;
    private JLabel DateOfUnvoiceLabel;
    private JButton okBtn;
    private JButton cancelBtn;

    public DialognextHeader(MainFrame frame) {
       
        DateOfUnvoiceLabel = new JLabel("Invoice Date:");
        DateofInvoiceBar = new JTextField(20);
        CustomerNameLabel = new JLabel("Customer Name:");
        customernamebar = new JTextField(20);
        cancelBtn = new JButton("Cancel");
        okBtn = new JButton("OK");
        okBtn.setActionCommand("newInvoiceOK");
        cancelBtn.setActionCommand("newInvoiceCancel");
        
        okBtn.addActionListener(frame.getActionListener());
        cancelBtn.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(3, 2));
        
        add(DateOfUnvoiceLabel);
        add(DateofInvoiceBar);
        add(CustomerNameLabel);
        add(customernamebar);
        add(okBtn);
        add(cancelBtn);
        
        pack();
        
    }

    public JTextField getCustomernamebar() {
        return customernamebar;
    }

    public JTextField getDateofInvoiceBar() {
        return DateofInvoiceBar;
    }
    
}
