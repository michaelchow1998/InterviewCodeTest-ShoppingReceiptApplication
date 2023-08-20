package config;

import common.Category;

import java.util.ArrayList;
import java.util.List;

public class CAConfig implements Config{

    private static final double CA_SALES_TAX_RATE = 0.0975;
    private static final double EXEMPT_SALES_TAX_RATE = 0;
    private static final ArrayList<Category> EXEMPT_LIST = new ArrayList<>(
            List.of(Category.FOOD));

    @Override
    public double getSalesTaxRate() {
        return CA_SALES_TAX_RATE;
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
