package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.Historico;

public abstract class DAO<T> {
	protected Connection connection;
    
    DAO(Connection connection) {
        this.connection = connection;
    }
    
    public abstract void create(T t) throws SQLException;
    public abstract T read(String id) throws SQLException;
    public abstract void update(T t) throws SQLException;
    public abstract void delete(String nome) throws SQLException;

    public abstract List<T> all() throws SQLException;

    public abstract List<Historico> aprov() throws SQLException;
    public abstract List<Historico> mediaCartoes() throws SQLException;
	public abstract List<Historico> porcPart() throws SQLException;
	public abstract List<Historico> titulos() throws SQLException;

}
