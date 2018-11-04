package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Adm;
import model.Historico;


public class AdmDAO extends DAO<Adm>{
	private static final String CREATE_QUERY = "INSERT INTO adm"+ "(login, senha)"+ "VALUES (?,md5(?));";
	
	private static final String READ_QUERY= "SELECT * "+ "FROM adm "+ "WHERE login = ?;";

	private static final String UPDATE_QUERY= "UPDATE adm "+ "SET login = ?, senha = ? "+ "WHERE login = ?;";

	private static final String DELETE_QUERY= "DELETE FROM adm "+ "WHERE login = ?;";

//	private static final String UPDATE_WITH_PASSWORD_QUERY= "UPDATE j2ee.usuario "+ "SET login = ?, nome = ?, nascimento = ?, senha = md5(?) "+ "WHERE id = ?;";

	private static final String ALL_QUERY= "SELECT login, senha "+ "FROM adm;";

	private static final String AUTHENTICATE_QUERY= "SELECT login, senha "+ "FROM adm "+ "WHERE login = ? AND senha = md5(?);";

	public AdmDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(Adm adm) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);) {
		    statement.setString(1, adm.getLogin());
		    statement.setString(2, adm.getSenha());
		   
		
		    try (ResultSet result = statement.executeQuery();) {
		//        if (result.next()) {
		//            usuario.setId(result.getInt("id"));
		//        }
		    }
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    if (ex.getMessage().contains("uq_usuario_login")) {
		        throw new SQLException("Erro ao inserir adm: nome já existente.");
		    } else if (ex.getMessage().contains("not-null")) {
		        throw new SQLException("Erro ao inserir usuário: pelo menos um campo está em branco.");
		    } else {
		        throw new SQLException("Erro ao inserir usuário.");
		    }
		}
	}
	
	@Override
	public Adm read(String id) throws SQLException {
		Adm adm = new Adm();
		
		try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
		    statement.setString(1, id);
		    try (ResultSet result = statement.executeQuery()) {
		        if (result.next()) {
	//	        	usuario.setId(id);
		        	adm.setLogin(result.getString("login"));
		        	adm.setSenha(result.getString("senha"));
		            
		        } else {
		            throw new SQLException("Erro ao visualizar: adm não encontrado.");
		        }
		    }
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    if (ex.getMessage().equals("Erro ao visualizar: adm não encontrado.")) {
		        throw ex;
		    } else {
		        throw new SQLException("Erro ao visualizar adm.");
		    }
		}
		
		return adm;
	}
	
	@Override
	public void update(Adm adm) throws SQLException {
		String query = UPDATE_QUERY;
		 
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
		    statement.setString(1, adm.getLogin());
		    statement.setString(2, adm.getSenha());
		    statement.setString(3, adm.getLogin());
		    
		    if (statement.executeUpdate() < 1) {
		        throw new SQLException("Erro ao editar: seleção não encontrada.");
		    }
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    if (ex.getMessage().equals("Erro ao editar: seleção não encontrada.")) {
		        throw ex;
		    } else if (ex.getMessage().contains("uq_usuario_login")) {
		        throw new SQLException("Erro ao editar seleção: seleção já existente.");
		    } else if (ex.getMessage().contains("not-null")) {
		        throw new SQLException("Erro ao editar seleção: pelo menos um campo está em branco.");
		    } else {
		        throw new SQLException("Erro ao editar seleção.");
		    }
		}
	}
	
	@Override
	public void delete(String nome) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
		    statement.setString(1, nome);
		
		    if (statement.executeUpdate() < 1) {
		        throw new SQLException("Erro ao excluir: adm não encontrado.");
		    }
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    if (ex.getMessage().equals("Erro ao excluir: usuário não encontrado.")) {
		        throw ex;
		    } else {
		        throw new SQLException("Erro ao excluir usuário.");
		    }
		}
	}
	
	@Override
	public List<Adm> all() throws SQLException {
		List<Adm> admList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
		        ResultSet result = statement.executeQuery()) {
		    while (result.next()) {
		        Adm adm = new Adm();
		        adm.setLogin(result.getString("login"));
		        adm.setSenha(result.getString("senha"));
		
		        admList.add(adm);
		    }
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    throw new SQLException("Erro ao listar selecoes.");
		}
		
		return admList;
	}
	
	public void authenticate(Adm adm) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(AUTHENTICATE_QUERY)) {
            statement.setString(1, adm.getLogin());
            statement.setString(2, adm.getSenha());

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    adm.setLogin(result.getString("login"));
                    adm.setSenha(result.getString("senha"));
                } else {
                    throw new SecurityException("Login ou senha incorretos.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao autenticar adm.");
        }
    }

	@Override
	public List<Historico> aprov() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Historico> mediaCartoes() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Historico> porcPart() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Historico> titulos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}

