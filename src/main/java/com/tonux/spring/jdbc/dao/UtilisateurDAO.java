package com.tonux.spring.jdbc.dao;

import java.util.List;

import com.tonux.spring.jdbc.model.Utilisateur;


public interface UtilisateurDAO {
	
	//Creation
	public void save(Utilisateur utilisateur);
	//recuoeration
	public Utilisateur getById(int id);
	//mise a jour
	public void update(Utilisateur utilisateur);
	//supression
	public void deleteById(int id);
	//tout recuperer
	public List<Utilisateur> getAll();
}
