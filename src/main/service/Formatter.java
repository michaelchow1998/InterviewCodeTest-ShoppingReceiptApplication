package service;

import dto.Receipt;
import model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Formatter {

    public static void printReceipt(Receipt receipt){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s %-15s %-15s%n", "Item", "Price", "Qty"));
        sb.append(System.lineSeparator());
        //append products
        ArrayList<Product> products = receipt.getProductReceipts();
        for (Product product:
                products) {
            sb.append(String.format("%-15s $%-15.2f %-15s%n", product.getProductName(), product.getPrice(), product.getQuantity().toString()));
        }
        //append total
        sb.append(String.format("%-30s %-15s%n", "subtotal", formatDecimal(receipt.getSubtotal())));
        sb.append(String.format("%-30s %-15s%n", "tax", formatDecimal(receipt.getTax())));
        sb.append(String.format("%-30s %-15s%n", "total", formatDecimal(receipt.getTotal())));

        System.out.println(sb);
    }

    public static String formatDecimal(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(value);
    }
}
