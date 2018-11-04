package jdbc;
import java.sql.Connection;
import java.sql.SQLException;

public class Teste {
	
	public static void main(String[] args) throws SQLException {
		
			Connection con = ConnectionFactory.getInstance().getConnection();
			System.out.println("CONECTA");
			System.out.println("Conexão: "+con);
		
	}
}
