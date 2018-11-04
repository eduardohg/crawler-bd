package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Historico;

public class RelatorioDAO extends DAO<Historico>{
	
	private static final String APROVEITAMENTO = "SELECT h.nome_selecao, h.partidas, h.pontuacao, ROUND((CAST (h.pontuacao / (CAST ( 3 * h.partidas AS DECIMAL)) AS DECIMAL )* 100 ),2) AS aproveitamento FROM historico h where h.partidas > 0;";
	
	private static final String MED_CARTOES = "SELECT h.nome_selecao, h.partidas, h.total_cartoes, ROUND((CAST (CAST (h.total_cartoes AS DECIMAL) / CAST (h.partidas AS DECIMAL) AS DECIMAL)),2)AS media_cartoes FROM historico h where h.partidas > 0";
	
	private static final String PORC_PART = "SELECT h.nome_selecao, h.participacoes, ROUND(CAST ( (CAST (CAST (h.participacoes AS DECIMAL) / 21 AS DECIMAL)) * 100 AS DECIMAL),2) AS porc_part FROM historico h;";
	
	private static final String TITULOS = "SELECT h.nome_selecao, h.titulos from historico h where h.titulos > 0;";
	
	public RelatorioDAO(Connection connection) {
		super(connection);
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
	
	public List<Historico> porcPart() throws SQLException {
		List<Historico> historicoList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(PORC_PART);
		        ResultSet result = statement.executeQuery()) {
		    while (result.next()) {
		        Historico historico = new Historico();
		        historico.setNome_selecao(result.getString("nome_selecao"));
		        historico.setParticipacoes(result.getInt("participacoes"));
		        historico.setPorc_part(result.getDouble("porc_part"));
		        
		        historicoList.add(historico);
		    }
		    return historicoList;
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    throw new SQLException("Erro ao listar históricos.");
		}
		
	}
	
	@Override
	public List<Historico> titulos() throws SQLException {
		List<Historico> historicoList = new ArrayList<>();
		
		try (PreparedStatement statement = connection.prepareStatement(TITULOS);
		        ResultSet result = statement.executeQuery()) {
		    while (result.next()) {
		        Historico historico = new Historico();
		        historico.setNome_selecao(result.getString("nome_selecao"));
		        historico.setTitulos(result.getInt("titulos"));
		        
		        historicoList.add(historico);
		    }
		    return historicoList;
		} catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		
		    throw new SQLException("Erro ao listar históricos.");
		}
	}

	@Override
	public void create(Historico t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Historico read(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Historico t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String nome) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Historico> all() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}

