package com.tonux.spring.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.tonux.spring.jdbc.model.Utilisateur;
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
			System.out.println("Utilisateur saved with id="+ utilisateur.getId());
		}else System.out.println("Utilisateur save failed with id="+ utilisateur.getId());
	}

	public Utilisateur getById(int id) {
		String query = "select id, name, role from Utilisateurs where id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//using RowMapper anonymous class, we can create a separate RowMapper for reuse
		Utilisateur user = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Utilisateur>(){

			public Utilisateur mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
				return user;
			}});
		
		return user;
	}

	public void update(Utilisateur utilisateur) {
		String query = "update Utilisateurs set name=?, role=? where id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] args = new Object[] {utilisateur.getName(), utilisateur.getRole(), utilisateur.getId()};
		
		int out = jdbcTemplate.update(query, args);
		if(out !=0){
			System.out.println("Utilisateur updated with id="+ utilisateur.getId());
		}else System.out.println("No Utilisateur found with id="+ utilisateur.getId());
	}

	public void deleteById(int id) {

		String query = "delete from Utilisateurs where id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		int out = jdbcTemplate.update(query, id);
		if(out !=0){
			System.out.println("Utilisateur deleted with id="+id);
		}else System.out.println("No Utilisateur found with id="+id);
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
