package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static Properties props;

    private PropertiesLoader() {
    }

    public static Properties get() {
        return props == null ? readProperties() : props;
    }

    private static Properties readProperties() {
        props = new Properties();
        try {
            props.load(PropertiesLoader.class.getClassLoader().getResourceAsStream("Configurations.properties"));
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return props;
    }

}
