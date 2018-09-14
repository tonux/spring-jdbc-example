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

		//To use JdbcTemplate spring
		UtilisateurDAO utilisateurDAO = ctx.getBean("utilisateurDAOJDBCTemplate", UtilisateurDAO.class);


		Utilisateur user = new Utilisateur();
		int rand = new Random().nextInt(1000);
        user.setId(rand);
        user.setName("Matthieu");
        user.setRole("Java Developer");
		
		//Creer utilisateur
		utilisateurDAO.save(user);
		
		//Recuperer utilisateur
		Utilisateur user1 = utilisateurDAO.getById(rand);
		System.out.println("Utilisateur trouvé::"+user1);
		
		//Update utilisateur
		user.setRole("CEO");
		utilisateurDAO.update(user);
		
		//Get All Utilisateurs
		List<Utilisateur> empList = utilisateurDAO.getAll();
		System.out.println(empList);
		
		//Supprimer utilisateur
		utilisateurDAO.deleteById(rand);
		
		//Close Spring Context
		ctx.close();
		
		System.out.println("DONE");
	}


}
