import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;

    // Constructor que establece la conexión
    public DatabaseConnection() {
        try {
            String url = "db.url";
            String user = "db.user";
            String password = "db.password";
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con éxito a la base de datos MySQL");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos MySQL");
            e.printStackTrace();
        }
    }

    // Método para obtener la conexión
    public Connection getConnection() {
        return this.connection;
    }

    // Método para cerrar la conexión
    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión");
                e.printStackTrace();
            }
        }
    }
}



