package br.com.caelum.jdbc.test;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.caelum.jdbc.dao.ContactDAO;
import br.com.caelum.jdbc.model.Contact;

public class TestList {

	public static void main(String[] args) {
		ContactDAO contactDAO = new ContactDAO();
		List<Contact> contacts = contactDAO.findAll();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		contacts.stream().forEach(o -> System.out.println("id: " + o.getId() + ",Nome: " + o.getName() + ", Email: "
				+ o.getEmail() + ", Data: " + sdf.format(o.getBirthday().getTime()) + ", Endereço :" + o.getAddress()));
		
		Contact contact = contactDAO.findOne(2L);
		System.out.println("Primeiro: " + contact.getName());
	}

}
