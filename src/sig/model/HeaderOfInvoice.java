
package sig.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HeaderOfInvoice {
    private Date DateOfInvoice;
    private String CusName;
    private int Numberr;
    private ArrayList<LinesOfInvoice> lines;
    private DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

    public HeaderOfInvoice() {
    }

    public HeaderOfInvoice(int Numb, String customer, Date InvoiceDate) {
        this.Numberr = Numb;
        this.CusName = customer;
        this.DateOfInvoice = InvoiceDate;
    }

    public Date getDateOfInvoice() {
        return DateOfInvoice;
    }

    public void setDateOfInvoice(Date DateOfInvoice) {
        this.DateOfInvoice = DateOfInvoice;
    }

    public int getNumberr() {
        return Numberr;
    }

    public void setNumberr(int Numberr) {
        this.Numberr = Numberr;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String CusName) {
        this.CusName = CusName;
    }

    public ArrayList<LinesOfInvoice> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<LinesOfInvoice> lines) {
        this.lines = lines;
    }
    
    public double getInvoiceSum() {
        double total = 0.0;
        
        for (int i = 0; i < getLines().size(); i++) {
            total += getLines().get(i).getSingleLineSum();
        }
        
        return total;
    }

    @Override
    public String toString() {
        return Numberr + "," + dateformat.format(DateOfInvoice) + "," + CusName;
    }
    
}
