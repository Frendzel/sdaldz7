package pl.sda;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.apache.log4j.Logger.getLogger;

public class PropertiesLoader {

    private static final String CONNECTION_PROPERTIES = "connection.properties";

    Logger logger = getLogger(PropertiesLoader.class);

    Properties properties = new Properties();

    public PropertiesLoader() {
        try {
            this.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() throws IOException {
        InputStream stream = getClass().getClassLoader().
                getResourceAsStream(CONNECTION_PROPERTIES);
        properties.load(stream);
        logger.info("Wczytalem Propertasy");
    }

    public String getUser() {
        return properties.getProperty("user");
    }

    public String getDb() {
        return properties.getProperty("db");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getAddress() {
        return properties.getProperty("address");
    }
}
