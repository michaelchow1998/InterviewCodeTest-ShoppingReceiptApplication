package config;

import common.Category;

import java.util.ArrayList;

public interface Config {

    double getSalesTaxRate();

    double getExemptSalesTaxRate();

    ArrayList<Category> getExemptList();
}
