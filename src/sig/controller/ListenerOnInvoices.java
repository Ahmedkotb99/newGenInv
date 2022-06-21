
package sig.controller;

import sig.model.HeaderOfInvoice;
import sig.model.HeaderOfInvoiceTableModel;
import sig.model.LinesOfInvoice;
import sig.model.LinesOfInvoiceTableModel;
import sig.view.MainFrame;
import sig.view.DialognextHeader;
import sig.view.DialogNextLine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class ListenerOnInvoices implements ActionListener {
    private DialognextHeader HeaderSecDialog;
    private DialogNextLine lineDialog;
     private MainFrame frame;

    public ListenerOnInvoices(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            
            case "Delete Invoice":
                deleteInvoice();
                
                break;
            case "Load Files":
                loadFiles();
                break;

            case "New Invoice":
                creatInvoice();
                break;

            
            case "newLineCancel":
                newLineDialogCancel();
                break;
            case "Save Files":
                saveFile();
                break;
            

            case "newInvoiceOK":
                OkSecDialog();
                break;
                case "New Line":
                createNewLine();
                break;
                
            case "Delete Line":
                deleteLine();
                break;

            case "newInvoiceCancel":
                CancelSecDialog();
                break;

            case "newLineOK":
                newLineDialogOK();
                break;
                
        }
    }

    

    private void creatInvoice() {
        HeaderSecDialog = new DialognextHeader(frame);
        HeaderSecDialog.setVisible(true);
    }

    private void deleteInvoice() {
        int selectedInvoiceIndex = frame.getInvoiceTableMain().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
            frame.getInvoicesArray().remove(selectedInvoiceIndex);
            frame.getHeaderTableModel().fireTableDataChanged();

            frame.getInvoicedetailLabel().setModel(new LinesOfInvoiceTableModel(null));
            frame.setLinesArray(null);
            frame.getCutomerNameLabel().setText("");
            frame.getInvoiceNumLabel().setText("");
            frame.getInvoiceTotalLabel().setText("");
            frame.getInvoiceDateLable().setText("");
        }
    }

    private void createNewLine() {
        lineDialog = new DialogNextLine(frame);
        lineDialog.setVisible(true);
    }

    private void deleteLine() {
        int selectedLineIndex = frame.getInvoicedetailLabel().getSelectedRow();
        int selectedInvoiceIndex = frame.getInvoiceTableMain().getSelectedRow();
        if (selectedLineIndex != -1) {
            frame.getLinesArray().remove(selectedLineIndex);
            LinesOfInvoiceTableModel lineTableModel = (LinesOfInvoiceTableModel) frame.getInvoicedetailLabel().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getInvoiceTotalLabel().setText("" + frame.getInvoicesArray().get(selectedInvoiceIndex).getInvoiceSum());
            frame.getHeaderTableModel().fireTableDataChanged();
            frame.getInvoiceTableMain().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
        }
    }

    private void saveFile() {
        ArrayList<HeaderOfInvoice> invoicesArray = frame.getInvoicesArray();
        JFileChooser filechooser = new JFileChooser();
        try {
            int result = filechooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = filechooser.getSelectedFile();
                FileWriter filewrter = new FileWriter(headerFile);
                String headers = "";
                String lines = "";
                for (HeaderOfInvoice invoice : invoicesArray) {
                    headers += invoice.toString();
                    headers += "\n";
                    for (LinesOfInvoice line : invoice.getLines()) {
                        lines += line.toString();
                        lines += "\n";
                    }
                }
                //  w e l c o m e
                //  0 1 2 3 4 5 6
                //  1 2 3 4 5 6 7
                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                result = filechooser.showSaveDialog(frame);
                File lineFile = filechooser.getSelectedFile();
                FileWriter lfw = new FileWriter(lineFile);
                filewrter.write(headers);
                lfw.write(lines);
                filewrter.close();
                lfw.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void CancelSecDialog() {
        HeaderSecDialog.setVisible(false);
        HeaderSecDialog.dispose();
        HeaderSecDialog = null;
    }

    private void OkSecDialog() {
        HeaderSecDialog.setVisible(false);

        String custName = HeaderSecDialog.getCustomernamebar().getText();
        String str = HeaderSecDialog.getDateofInvoiceBar().getText();
        Date d = new Date();
        try {
            d = MainFrame.dateFormat.parse(str);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot parse date, resetting to today.", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }

        int invNum = 0;
        for (HeaderOfInvoice inv : frame.getInvoicesArray()) {
            if (inv.getNumberr() > invNum) {
                invNum = inv.getNumberr();
            }
        }
        invNum++;
        HeaderOfInvoice newInv = new HeaderOfInvoice(invNum, custName, d);
        frame.getInvoicesArray().add(newInv);
        frame.getHeaderTableModel().fireTableDataChanged();
        HeaderSecDialog.dispose();
        HeaderSecDialog = null;
    }

    private void newLineDialogCancel() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }

    private void newLineDialogOK() {
        lineDialog.setVisible(false);

        String name = lineDialog.getNameofItemBar().getText();
        String str1 = lineDialog.getCountOfItemBar().getText();
        String str2 = lineDialog.getPriceOfItemBar().getText();
        int count = 1;
        double price = 1;
        try {
            count = Integer.parseInt(str1);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }

        try {
            price = Double.parseDouble(str2);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot convert price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvHeader = frame.getInvoiceTableMain().getSelectedRow();
        if (selectedInvHeader != -1) {
            HeaderOfInvoice invHeader = frame.getInvoicesArray().get(selectedInvHeader);
            LinesOfInvoice line = new LinesOfInvoice(name, price, count, invHeader);
            //invHeader.getLines().add(line);
            frame.getLinesArray().add(line);
            LinesOfInvoiceTableModel lineTableModel = (LinesOfInvoiceTableModel) frame.getInvoicedetailLabel().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getHeaderTableModel().fireTableDataChanged();
        }
        frame.getInvoiceTableMain().setRowSelectionInterval(selectedInvHeader, selectedInvHeader);
        lineDialog.dispose();
        lineDialog = null;
    }
    private void loadFiles() {
        JFileChooser fileChooser = new JFileChooser();
        try {
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fileChooser.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                ArrayList<HeaderOfInvoice> invoiceHeaders = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] arr = headerLine.split(",");
                    String str1 = arr[0];
                    String str2 = arr[1];
                    String str3 = arr[2];
                    int code = Integer.parseInt(str1);
                    Date invoiceDate = MainFrame.dateFormat.parse(str2);
                    HeaderOfInvoice header = new HeaderOfInvoice(code, str3, invoiceDate);
                    invoiceHeaders.add(header);
                }
                frame.setInvoicesArray(invoiceHeaders);

                result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fileChooser.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    ArrayList<LinesOfInvoice> invoiceLines = new ArrayList<>();
                    for (String lineLine : lineLines) {
                        String[] arr = lineLine.split(",");
                        String str1 = arr[0];    // invoice num (int)
                        String str2 = arr[1];    // item name   (String)
                        String str3 = arr[2];    // price       (double)
                        String str4 = arr[3];    // count       (int)
                        int invCode = Integer.parseInt(str1);
                        double price = Double.parseDouble(str3);
                        int count = Integer.parseInt(str4);
                        HeaderOfInvoice inv = frame.getInvObject(invCode);
                        LinesOfInvoice line = new LinesOfInvoice(str2, price, count, inv);
                        inv.getLines().add(line);
                    }
                }
                HeaderOfInvoiceTableModel headerTableModel = new HeaderOfInvoiceTableModel(invoiceHeaders);
                frame.setHeaderTableModel(headerTableModel);
                frame.getInvoiceTableMain().setModel(headerTableModel);
                System.out.println("files read");
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
