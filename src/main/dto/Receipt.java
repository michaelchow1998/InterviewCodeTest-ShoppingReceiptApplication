package dto;

import model.Product;

import java.util.ArrayList;

public class Receipt {

    private double subtotal;

    private double tax;


    private ArrayList<Product> productReceipts;

    public Receipt() {
        this.productReceipts = new ArrayList<Product>();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void addSubtotal(double newTotal) {
        this.subtotal = this.subtotal + newTotal;
    }

    public double getTax() {
        return tax;
    }

    public void addTax(double newTax) {
        this.tax = this.tax + newTax;
    }

    public double getTotal() {
        return subtotal + tax;
    }

    public ArrayList<Product> getProductReceipts() {
        return productReceipts;
    }
    public void addPurchasedProducts(Product product) {
        productReceipts.add(product);
    }


}
