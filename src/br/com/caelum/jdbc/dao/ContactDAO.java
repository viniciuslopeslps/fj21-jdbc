package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.model.Contact;

public class ContactDAO {
	private Connection connection;

	public ContactDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void insert(Contact contact) {
		String sql = "insert into contatos(nome, email, endereco, dataNascimento) values(?,?,?,?)";

		try {
			// prepara inserção
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getEmail());
			stmt.setString(3, contact.getAddress());
			stmt.setDate(4, new Date(contact.getBirthday().getTimeInMillis()));

			// executa
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Contact contact) {
		String sql = "update contatos set nome=?, email=?, endereco =?, dataNascimento=? where id=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getEmail());
			stmt.setString(3, contact.getAddress());
			stmt.setDate(4, new Date(contact.getBirthday().getTimeInMillis()));
			stmt.setLong(5, contact.getId());
			stmt.execute();
			stmt.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Contact contact) {
		String sql = "delete from contatos where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, contact.getId());
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Contact> findAll() {
		List<Contact> contacts = new ArrayList<>();
		String sql = "select * from contatos";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setName(rs.getString("nome"));
				contact.setEmail(rs.getString("email"));
				contact.setAddress(rs.getString("endereco"));
				contact.setId(rs.getLong("id"));

				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contact.setBirthday(data);

				contacts.add(contact);
			}
			return contacts;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Contact findOne(Long id) {
		String sql = "select * from contatos where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			rs.next();

			Contact contact = new Contact();
			contact.setName(rs.getString("nome"));
			contact.setEmail(rs.getString("email"));
			contact.setAddress(rs.getString("endereco"));
			contact.setId(rs.getLong("id"));
			return contact;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
