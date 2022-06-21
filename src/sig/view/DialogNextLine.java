/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class DialogNextLine extends JDialog {

    private final JTextField NameofItemBar;
    private final JTextField CountOfItemBar;
    private JTextField PriceOfItemBar;
    private JLabel NameOfItemLabel;
    private JLabel CountOfItemLabel;
    private JLabel PriceOfItemLabel;
    private JButton okBtn;
    private JButton cancelBtn;

    public DialogNextLine(MainFrame frame) {

        PriceOfItemBar = new JTextField(20);
        PriceOfItemLabel = new JLabel("Item Price");
        CountOfItemBar = new JTextField(20);
        CountOfItemLabel = new JLabel("Item Count");
        NameofItemBar = new JTextField(20);
        NameOfItemLabel = new JLabel("Item Name");
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        setLayout(new GridLayout(4, 2));
        okBtn.setActionCommand("newLineOK");
        cancelBtn.setActionCommand("newLineCancel");
        okBtn.addActionListener(frame.getActionListener());
        cancelBtn.addActionListener(frame.getActionListener());

        add(NameOfItemLabel);
        add(NameofItemBar);
        add(CountOfItemLabel);
        add(CountOfItemBar);
        add(PriceOfItemLabel);
        add(PriceOfItemBar);
        add(okBtn);
        add(cancelBtn);

        pack();
    }

    public JTextField getNameofItemBar() {
        return NameofItemBar;
    }

    public JTextField getCountOfItemBar() {
        return CountOfItemBar;
    }

    public JTextField getPriceOfItemBar() {
        return PriceOfItemBar;
    }
}
