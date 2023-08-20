package model;

import common.Category;
import config.CategoriesMappingConfig;

public class Product {
    private final String productName;
    private final Category category;
    private final Integer quantity;
    private final Double price;



    public Product(String productName, Integer quantity, Double price) {
        this.productName = productName;
        this.category = initCategory(productName);
        this.quantity = quantity;
        this.price = price;
    }

    private Category initCategory(String productName){
        CategoriesMappingConfig categoriesMapper = new CategoriesMappingConfig();
        return categoriesMapper.mapCategory(productName);
    }


    public String getProductName() {
        return productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

}
