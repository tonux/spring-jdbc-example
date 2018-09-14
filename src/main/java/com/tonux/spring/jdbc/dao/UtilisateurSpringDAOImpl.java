package com.tonux.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.tonux.spring.jdbc.model.Utilisateur;
import com.tonux.spring.jdbc.rowmappers.UtilisateurRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UtilisateurSpringDAOImpl implements UtilisateurDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void save(Utilisateur utilisateur) {
		String query = "insert into Utilisateurs (id, name, role) values (?,?,?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Object[] args = new Object[] {utilisateur.getId(), utilisateur.getName(), utilisateur.getRole()};
		
		int out = jdbcTemplate.update(query, args);
		
		if(out !=0){
			System.out.println("Utilisateur créé id="+ utilisateur.getId());
		}else System.out.println("Utilisateur création échouée  id="+ utilisateur.getId());
	}

	public Utilisateur getById(int id) {
		String query = "select id, name, role from Utilisateurs where id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//using RowMapper
		RowMapper<Utilisateur> userRowMapper = new UtilisateurRow();

		Utilisateur user = jdbcTemplate.queryForObject(query, new Object[]{id}, userRowMapper);
		
		return user;
	}

	public void update(Utilisateur utilisateur) {
		String query = "update Utilisateurs set name=?, role=? where id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] args = new Object[] {utilisateur.getName(), utilisateur.getRole(), utilisateur.getId()};
		
		int out = jdbcTemplate.update(query, args);
		if(out !=0){
			System.out.println("Utilisateur mise a jour id="+ utilisateur.getId());
		}else System.out.println("Pas Utilisateur trouvé  id="+ utilisateur.getId());
	}

	public void deleteById(int id) {

		String query = "delete from Utilisateurs where id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		int out = jdbcTemplate.update(query, id);
		if(out !=0){
			System.out.println("Utilisateur supprimé  id="+id);
		}else System.out.println("Pas Utilisateur trouvé  id="+id);
	}

	public List<Utilisateur> getAll() {
		String query = "select id, name, role from Utilisateurs";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Utilisateur> userList = new ArrayList<Utilisateur>();

		List<Map<String,Object>> userRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> userRow : userRows){
			Utilisateur user = new Utilisateur();
			user.setId(Integer.parseInt(String.valueOf(userRow.get("id"))));
            user.setName(String.valueOf(userRow.get("name")));
            user.setRole(String.valueOf(userRow.get("role")));
            userList.add(user);
		}
		return userList;
	}

}
