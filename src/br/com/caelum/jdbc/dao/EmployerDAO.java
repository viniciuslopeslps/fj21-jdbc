package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.model.Contact;
import br.com.caelum.jdbc.model.Employer;

public class EmployerDAO {
	private Connection connection;

	public EmployerDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void insert(Employer employer) {
		String sql = "insert into funcionarios(nome, usuario, senha) values(?,?,?)";

		try {
			// prepara inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, employer.getName());
			stmt.setString(3, employer.getUser());
			stmt.setString(2, employer.getPassword());

			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Employer employer) {
		String sql = "update funcionarios set nome=?, usuario=?, senha =? where id=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, employer.getName());
			stmt.setString(2, employer.getUser());
			stmt.setString(3, employer.getPassword());
			stmt.setLong(4, employer.getId());
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Employer employer) {
		String sql = "delete from funcionarios where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, employer.getId());
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Employer findOne(Long id) {
		String sql = "select * from funcrionarios where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			rs.next();

			Employer employer = new Employer();
			employer.setName(rs.getString("nome"));
			employer.setUser(rs.getString("user"));
			employer.setPassword(rs.getString("endereco"));
			employer.setId(rs.getLong("id"));
			return employer;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
