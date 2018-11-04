package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static ConnectionFactory instance = null;
	
	public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }

        return instance;
    }
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/world_cup","eduardo","wasd1");
		}catch(Exception e) {
			System.out.println("DEU RUIM");
			throw new RuntimeException(e);
		}
	}
	
	

}
