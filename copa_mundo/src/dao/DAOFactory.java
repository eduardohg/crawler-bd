package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import jdbc.ConnectionFactory;

public class DAOFactory implements AutoCloseable{
	
	private Connection connection = null;

    public DAOFactory() throws ClassNotFoundException, IOException, SQLException {
        connection = ConnectionFactory.getInstance().getConnection();
    }
	
	public SelecaoDAO getSelecaoDAO() {
		return new SelecaoDAO(connection);
	}
	
	public HistoricoDAO getHistoricoDAO() {
		return new HistoricoDAO(connection);
	}
	
	public RelatorioDAO getRelatorioDAO() {
		return new RelatorioDAO(connection);
	}
	
	public AdmDAO getAdmDAO() {
		return new AdmDAO(connection);
	}
	
	public void closeConnection() throws SQLException {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao fechar conexão ao banco de dados.");
        }
    }

	@Override
	public void close() throws SQLException {
		closeConnection();
	}

}
