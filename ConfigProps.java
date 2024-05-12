import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProps {
    public static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("database.properties")) {
            props.load(fis);
        }
        return props;
    }
}
