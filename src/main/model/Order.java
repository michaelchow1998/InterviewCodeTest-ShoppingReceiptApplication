package model;

import common.Location;

import java.util.ArrayList;

public class Order {

    private Location location;
    private ArrayList<Product> purchasedProducts;


    public Order(Location location) {
        this.location = location;
        this.purchasedProducts = new ArrayList<Product>();
    }

    public Location getLocation() {
        return location;
    }

    public void addPurchasedProducts(Product product) {
        purchasedProducts.add(product);
    }

    public ArrayList<Product> getPurchasedProducts() {
        return purchasedProducts;
    }
}
