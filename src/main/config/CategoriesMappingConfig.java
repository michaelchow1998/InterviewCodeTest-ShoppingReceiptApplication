package config;

import common.Category;

import java.util.HashMap;
import java.util.Map;

public class CategoriesMappingConfig {
    private static CategoriesMappingConfig instance;
    private Map<String, Category> categoryMap;

    public CategoriesMappingConfig() {
        this.categoryMap = new HashMap<>();
        initMapping();
    }

    public static CategoriesMappingConfig getInstance() {
        if(instance == null) {
            synchronized (CategoriesMappingConfig.class) {
                if (instance == null) {
                    instance = new CategoriesMappingConfig();
                }
            }
        }
        return instance;
    }

    private void initMapping() {
        categoryMap.put("shirt", Category.CLOTHING);
        categoryMap.put("dress", Category.CLOTHING);
        categoryMap.put("shoes", Category.CLOTHING);
        categoryMap.put("hat", Category.CLOTHING);
        categoryMap.put("apple", Category.FOOD);
        categoryMap.put("chicken", Category.FOOD);
        categoryMap.put("milk", Category.FOOD);
        categoryMap.put("beef", Category.FOOD);
        categoryMap.put("rice", Category.FOOD);
        categoryMap.put("potato chips", Category.FOOD);
    }

    public Category mapCategory(String category) {
        return categoryMap.getOrDefault(category.toLowerCase(), Category.OTHERS);
    }
}
