package factory;

import config.CAConfig;
import config.Config;
import config.NYConfig;

public class ConfigFactory {
    public static Config createConfig(String type) {
        if (type.equalsIgnoreCase("CA")) {
            return new CAConfig();
        } else if (type.equalsIgnoreCase("NY")) {
            return new NYConfig();
        } else {
            throw new IllegalArgumentException("Invalid config type: " + type);
        }
    }

}
