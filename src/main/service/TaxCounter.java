package service;


import common.Category;
import config.Config;
import model.Product;


public class TaxCounter {
    public static double getSubTotal(Product product){
        return product.getPrice() * product.getQuantity();
    }
    public static double countTax(Config config, Product product){
        double productTotalPriceBeforeTax = product.getPrice() * product.getQuantity();
        double tax;

        Category productCategory = product.getCategory();
        boolean isExempt = config.getExemptList().contains(productCategory);

        if(isExempt){
            tax = roundup(productTotalPriceBeforeTax * config.getExemptSalesTaxRate());
        }else{
            tax = roundup(productTotalPriceBeforeTax * config.getSalesTaxRate());
        }

        return tax;
    }

    public static Double roundup(Double value){
        return Math.ceil(value * 20) / 20.0;
    }

}
