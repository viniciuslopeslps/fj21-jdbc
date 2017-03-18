package br.com.caelum.jdbc.test;

import java.util.Calendar;

import br.com.caelum.jdbc.dao.ContactDAO;
import br.com.caelum.jdbc.dao.EmployerDAO;
import br.com.caelum.jdbc.model.Contact;
import br.com.caelum.jdbc.model.Employer;

public class TestDAO {

	public static void main(String[] args) {
		Contact contact = new Contact();
		contact.setName("caelum");
		contact.setAddress("R. Vergueiro 3185");
		contact.setBirthday(Calendar.getInstance());
		contact.setEmail("contato@caelum.com.br");

		//ContactDAO contactDAO = new ContactDAO();
		System.out.println("Inserindo");
		//contactDAO.insert(contact);
		//System.out.println("Atualizando");
		//contactDAO.update(contact);
		//System.out.println("Deletando");
		//contactDAO.delete(contact);
		
		
		Employer employer = new Employer();
		employer.setName("employer");
		employer.setPassword("123");
		employer.setUser("usuario");
		
		EmployerDAO employerDAO = new EmployerDAO();
		employerDAO.insert(employer);
	}

}
