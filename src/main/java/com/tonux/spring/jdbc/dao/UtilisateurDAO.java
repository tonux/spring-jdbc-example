package com.tonux.spring.jdbc.dao;

import java.util.List;

import com.tonux.spring.jdbc.model.Utilisateur;

//CRUD operations
public interface UtilisateurDAO {
	
	//Create
	public void save(Utilisateur utilisateur);
	//Read
	public Utilisateur getById(int id);
	//Update
	public void update(Utilisateur utilisateur);
	//Delete
	public void deleteById(int id);
	//Get All
	public List<Utilisateur> getAll();
}
