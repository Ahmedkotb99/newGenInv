
package sig.model;

public class LinesOfInvoice {
    private String item;
    private double price;
    private int count;
    private HeaderOfInvoice header;

    public LinesOfInvoice() {
    }

    public LinesOfInvoice(String item, double price, int count, HeaderOfInvoice header) {
        this.item = item;
        this.price = price;
        this.count = count;
        this.header = header;
    }

    public HeaderOfInvoice getHeader() {
        return header;
    }

    public void setHeader(HeaderOfInvoice header) {
        this.header = header;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public double getSingleLineSum() {
        return price * count;
    }

    @Override
    public String toString() {
        return header.getNumberr() + "," + item + "," + price + "," + count;
    }

    
    
}
