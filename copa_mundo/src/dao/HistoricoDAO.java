package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Historico;

public class HistoricoDAO extends DAO<Historico>{
	
	private static final String CREATE_QUERY = "INSERT INTO historico "+ "(nome_selecao, titulos, participacoes, total_cartoes, cartoes_amarelos, cartoes_vermelhos, partidas, pontuacao, vitorias, derrotas, empates)"
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	
	private static final String READ_QUERY= "SELECT * "+ "FROM historico "+ "WHERE nome_selecao = ?;";
	
	private static final String UPDATE_QUERY= "UPDATE historico "+ "SET titulos = ?, participacoes = ?, total_cartoes = ?, cartoes_amarelos = ?, cartoes_vermelhos = ?, partidas = ?, pontuacao = ?, vitorias = ?, derrotas = ?, empates = ?"+ "WHERE nome_selecao = ?;";
	
	private static final String ALL_QUERY= "SELECT * "+ "FROM historico;";
	
	private static final String DELETE_QUERY= "DELETE FROM historico "+ "WHERE nome_selecao = ?;";
	
	private static final String APROVEITAMENTO = "SELECT h.nome_selecao, h.partidas, h.pontuacao, ROUND((CAST (h.pontuacao / (CAST ( 3 * h.partidas AS DECIMAL)) AS DECIMAL )* 100 ),2) AS aproveitamento FROM historico h;";
	
	private static final String MED_CARTOES = "SELECT h.nome_selecao, h.partidas, h.total_cartoes, (CAST (h.total_cartoes / h.partidas AS DOUBLE PRECISION))AS media_cartoes FROM historico h;";
	
	private static final String PORC_PART = "SELECT h.nome_selecao, h.participacoes, ROUND(CAST ( (CAST (h.participacoes / 21 AS DECIMAL)) * 100 AS DECIMAL),2) AS porc_part FROM historico h;";
	
	
	public HistoricoDAO(Connection connection) {
		super(connection);
	}
	
	public void create(Historico historico) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);) {
		    
			statement.setString(1, historico.getNome_selecao());
		    statement.setInt(2, historico.getTitulos());
		    statement.setInt(3, historico.getParticipacoes());
		    statement.setInt(4, historico.getTotal_cartoes());
		    statement.setInt(5, historico.getCartoes_amarelos());
		    statement.setInt(6, historico.getCartoes_vermelhos());
		    statement.setInt(7, historico.getPartidas());
		    statement.setInt(8, historico.getPontuacao());
		    statement.setInt(9, historico.getVitorias());
		    statement.setInt(10, historico.getDerrotas());
		    statement.setInt(11, historico.getEmpates());
		   
		
		    try (ResultSet result = statement.executeQuery();) {
		//        if (result.next()) {
		//            usuario.setId(result.getInt("id"));
		//        }
		    }
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    if (ex.getMessage().contains("uq_usuario_login")) {
		        throw new SQLException("Erro ao inserir historico: historico já existente.");
		    } else if (ex.getMessage().contains("not-null")) {
		        throw new SQLException("Erro ao inserir usuário: pelo menos um campo está em branco.");
		    } else {
		        throw new SQLException("Erro ao inserir usuário.");
		    }
		}
	}
	
	public Historico read(String nome_selecao) throws SQLException {
		Historico historico = new Historico();
		
		try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
		    statement.setString(1, nome_selecao);
		    try (ResultSet result = statement.executeQuery()) {
		        if (result.next()) {
		        	historico.setNome_selecao(result.getString("nome_selecao"));
		        	historico.setTitulos(result.getInt("titulos"));
		        	historico.setParticipacoes(result.getInt("participacoes"));
		        	historico.setTotal_cartoes(result.getInt("total_cartoes"));
		        	historico.setCartoes_amarelos(result.getInt("cartoes_amarelos"));
		        	historico.setCartoes_vermelhos(result.getInt("cartoes_vermelhos"));
		        	historico.setPartidas(result.getInt("partidas"));
		        	historico.setPontuacao(result.getInt("pontuacao"));
		        	historico.setVitorias(result.getInt("vitorias"));
		        	historico.setDerrotas(result.getInt("derrotas"));
		        	historico.setEmpates(result.getInt("empates"));
		            
		        } else {
		            throw new SQLException("Erro ao visualizar: historico não encontrado.");
		        }
		    }
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    if (ex.getMessage().equals("Erro ao visualizar: historico não encontrado.")) {
		        throw ex;
		    } else {
		        throw new SQLException("Erro ao visualizar historico.");
		    }
		}
		
		return historico;
	}
	
	public void update(Historico historico) throws SQLException {
		String query = UPDATE_QUERY;
		 
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			
			statement.setInt(1, historico.getTitulos());
			statement.setInt(2, historico.getParticipacoes());
			statement.setInt(3, historico.getTotal_cartoes());
		    statement.setInt(4, historico.getCartoes_amarelos());
		    statement.setInt(5, historico.getCartoes_vermelhos());
		    statement.setInt(6, historico.getPartidas());
		    statement.setInt(7, historico.getPontuacao());
		    statement.setInt(8, historico.getVitorias());
		    statement.setInt(9, historico.getDerrotas());
		    statement.setInt(10, historico.getEmpates());
		    statement.setString(11, historico.getNome_selecao());
		    
		
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
		        throw new SQLException("Erro ao excluir: histórico não encontrado.");
		    }
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    if (ex.getMessage().equals("Erro ao excluir: histórico não encontrado.")) {
		        throw ex;
		    } else {
		        throw new SQLException("Erro ao excluir histórico.");
		    }
		}
	}
	
	@Override
	public List<Historico> all() throws SQLException {
		List<Historico> historicoList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
		        ResultSet result = statement.executeQuery()) {
		    while (result.next()) {
		        Historico historico = new Historico();
		        historico.setNome_selecao(result.getString("nome_selecao"));
		        historico.setTitulos(result.getInt("titulos"));
		        historico.setParticipacoes(result.getInt("participacoes"));
		        historico.setTotal_cartoes(result.getInt("total_cartoes"));
		        historico.setCartoes_amarelos(result.getInt("cartoes_amarelos"));
		        historico.setCartoes_vermelhos(result.getInt("cartoes_vermelhos"));
		        historico.setPartidas(result.getInt("partidas"));
		        historico.setPontuacao(result.getInt("pontuacao"));
		        historico.setVitorias(result.getInt("vitorias"));
		        historico.setDerrotas(result.getInt("derrotas"));
		        historico.setEmpates(result.getInt("empates"));
		        
		
		        historicoList.add(historico);
		    }
		    return historicoList;
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    throw new SQLException("Erro ao listar históricos.");
		}
		
	}
	
	public List<Historico> aprov() throws SQLException {
		List<Historico> historicoList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(APROVEITAMENTO);
		        ResultSet result = statement.executeQuery()) {
		    while (result.next()) {
		        Historico historico = new Historico();
		        historico.setNome_selecao(result.getString("nome_selecao"));
		        historico.setPartidas(result.getInt("partidas"));
		        historico.setPontuacao(result.getInt("pontuacao"));
		        historico.setAproveitamento(result.getDouble("aproveitamento"));
		        
		        historicoList.add(historico);
		    }
		    return historicoList;
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    throw new SQLException("Erro ao listar históricos.");
		}
		
	}

	@Override
	public List<Historico> mediaCartoes() throws SQLException {
		List<Historico> historicoList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(MED_CARTOES);
		        ResultSet result = statement.executeQuery()) {
		    while (result.next()) {
		        Historico historico = new Historico();
		        historico.setNome_selecao(result.getString("nome_selecao"));
		        historico.setPartidas(result.getInt("partidas"));
		        historico.setTotal_cartoes(result.getInt("total_cartoes"));
		        historico.setMedia_cartoes(result.getDouble("media_cartoes"));
		        
		        historicoList.add(historico);
		    }
		    return historicoList;
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    throw new SQLException("Erro ao listar históricos.");
		}
		
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
