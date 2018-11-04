package dao;

import model.Historico;
import model.Selecao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelecaoDAO extends DAO<Selecao>{
	
	private static final String CREATE_QUERY = "INSERT INTO selecao"+ "(nome, bandeira)"+ "VALUES (?,?);";

	private static final String READ_QUERY= "SELECT * "+ "FROM selecao "+ "WHERE nome = ?;";

	private static final String UPDATE_QUERY= "UPDATE selecao "+ "SET nome = ?, bandeira = ? "+ "WHERE nome = ?;";

	private static final String DELETE_QUERY= "DELETE FROM selecao "+ "WHERE nome = ?;";

//	private static final String UPDATE_WITH_PASSWORD_QUERY= "UPDATE j2ee.usuario "+ "SET login = ?, nome = ?, nascimento = ?, senha = md5(?) "+ "WHERE id = ?;";

	private static final String ALL_QUERY= "SELECT nome, bandeira "+ "FROM selecao;";

//	private static final String AUTHENTICATE_QUERY= "SELECT id, nome, nascimento, avatar "+ "FROM j2ee.usuario "+ "WHERE login = ? AND senha = md5(?);";

	public SelecaoDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(Selecao selecao) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);) {
		    statement.setString(1, selecao.getNome());
		    statement.setString(2, selecao.getBandeira());
		   
		
		    try (ResultSet result = statement.executeQuery();) {
		//        if (result.next()) {
		//            usuario.setId(result.getInt("id"));
		//        }
		    }
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    if (ex.getMessage().contains("uq_usuario_login")) {
		        throw new SQLException("Erro ao inserir selecao: nome já existente.");
		    } else if (ex.getMessage().contains("not-null")) {
		        throw new SQLException("Erro ao inserir usuário: pelo menos um campo está em branco.");
		    } else {
		        throw new SQLException("Erro ao inserir usuário.");
		    }
		}
	}
	
	@Override
	public Selecao read(String id) throws SQLException {
		Selecao selecao = new Selecao();
		
		try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
		    statement.setString(1, id);
		    try (ResultSet result = statement.executeQuery()) {
		        if (result.next()) {
	//	        	usuario.setId(id);
		        	selecao.setNome(result.getString("nome"));
		        	selecao.setBandeira(result.getString("bandeira"));
		            
		        } else {
		            throw new SQLException("Erro ao visualizar: seleção não encontrada.");
		        }
		    }
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    if (ex.getMessage().equals("Erro ao visualizar: seleção não encontrada.")) {
		        throw ex;
		    } else {
		        throw new SQLException("Erro ao visualizar seleção.");
		    }
		}
		
		return selecao;
	}
	
	@Override
	public void update(Selecao selecao) throws SQLException {
		String query = UPDATE_QUERY;
		 
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
		    statement.setString(1, selecao.getNome());
		    statement.setString(2, selecao.getBandeira());
		    statement.setString(3, selecao.getNome());
		    
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
		        throw new SQLException("Erro ao excluir: usuário não encontrado.");
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
	public List<Selecao> all() throws SQLException {
		List<Selecao> selecaoList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
		        ResultSet result = statement.executeQuery()) {
		    while (result.next()) {
		        Selecao selecao = new Selecao();
		        selecao.setNome(result.getString("nome"));
		        selecao.setBandeira(result.getString("bandeira"));
		
		        selecaoList.add(selecao);
		    }
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    throw new SQLException("Erro ao listar selecoes.");
		}
		
		return selecaoList;
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
	
//	public void authenticate(Usuario usuario) throws SQLException {
//	try (PreparedStatement statement = connection.prepareStatement(AUTHENTICATE_QUERY)) {
//	    statement.setString(1, usuario.getLogin());
//	    statement.setString(2, usuario.getSenha());
//	
//	    try (ResultSet result = statement.executeQuery()) {
//	        if (result.next()) {
//	            usuario.setId(result.getInt("id"));
//	            usuario.setNome(result.getString("nome"));
//	            usuario.setNascimento(result.getDate("nascimento"));
//	            usuario.setAvatar(result.getString("avatar"));
//	        } else {
//	            throw new SecurityException("Login ou senha incorretos.");
//	        }
//	    }
//	} catch (SQLException ex) {
//	    System.err.println(ex.getMessage());
//	
//	    throw new SQLException("Erro ao autenticar usuário.");
//	}
//	}
}
