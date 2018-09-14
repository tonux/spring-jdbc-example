package com.tonux.spring.jdbc.main;

import java.util.List;
import java.util.Random;

import com.tonux.spring.jdbc.model.Utilisateur;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tonux.spring.jdbc.dao.UtilisateurDAO;

public class SpringMain {

	public static void main(String[] args) {
		//Get the Spring Context
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		//Get the UtilisateurDAO Bean
		//UtilisateurDAO utilisateurDAO = ctx.getBean("utilisateurDAO", UtilisateurDAO.class);
		//To use JdbcTemplate
		UtilisateurDAO utilisateurDAO = ctx.getBean("utilisateurDAOJDBCTemplate", UtilisateurDAO.class);
		
		//Run some tests for JDBC CRUD operations
		Utilisateur user = new Utilisateur();
		int rand = new Random().nextInt(1000);
        user.setId(rand);
        user.setName("Matthieu");
        user.setRole("Java Developer");
		
		//Create
		utilisateurDAO.save(user);
		
		//Read
		Utilisateur user1 = utilisateurDAO.getById(rand);
		System.out.println("Utilisateur Retrieved::"+user1);
		
		//Update
		user.setRole("CEO");
		utilisateurDAO.update(user);
		
		//Get All
		List<Utilisateur> empList = utilisateurDAO.getAll();
		System.out.println(empList);
		
		//Delete
		utilisateurDAO.deleteById(rand);
		
		//Close Spring Context
		ctx.close();
		
		System.out.println("DONE");
	}


}
