import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        // Carga las propiedades desde el archivo
        Properties props = loadProperties("database.properties");

        // Obtiene las propiedades necesarias
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        String driver = props.getProperty("db.driver");

        // Usa estas propiedades para conectar a la base de datos
        System.out.println("URL: " + url);
        System.out.println("User: " + user);
        System.out.println("Password: " + password);
        System.out.println("Driver: " + driver);
    }

    // Método para cargar las propiedades desde el archivo
    private static Properties loadProperties(String filename) {
        Properties props = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new IOException("No se pudo encontrar el archivo: " + filename);
            }
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            // Maneja el error adecuadamente, por ejemplo, lanzando una excepción o tomando otra acción.
        }
        return props;
    }
}
