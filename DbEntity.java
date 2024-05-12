import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbEntity {

    public static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        }
        return props;
    }

    public static void retrieveData() {
        try {
            // Cargar las propiedades desde el archivo
            Properties props = loadProperties();

            // Obtener las propiedades necesarias
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            String driver = props.getProperty("db.driver");

            // Cargar el controlador JDBC
            Class.forName(driver);

            // Establecer la conexión
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                System.out.println("Conexión establecida con éxito a la base de datos MySQL");

                // Ejemplo: Consulta simple
                String query = "SELECT * FROM usuarios";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        int userId = resultSet.getInt("id");
                        String username = resultSet.getString("username");
                        System.out.println("ID: " + userId + ", Usuario: " + username);
                    }
                }

                // Cerrar la conexión
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos MySQL");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error al cargar el controlador JDBC o al cargar las propiedades");
            e.printStackTrace();
        }
    }
}

