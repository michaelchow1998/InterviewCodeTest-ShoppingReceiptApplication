package config;

import common.Category;

import java.util.ArrayList;
import java.util.Arrays;

public class NYConfig implements Config{

    private static final double NY_SALES_TAX_RATE = 0.08875;
    private static final double EXEMPT_SALES_TAX_RATE = 0;
    private static final ArrayList<Category> EXEMPT_LIST = new ArrayList<>(
            Arrays.asList(Category.FOOD, Category.CLOTHING));

    @Override
    public double getSalesTaxRate() {
        return NY_SALES_TAX_RATE;
    }

    @Override
    public double getExemptSalesTaxRate() {
        return EXEMPT_SALES_TAX_RATE;
    }

    @Override
    public ArrayList<Category> getExemptList() {
        return EXEMPT_LIST;
    }
}
