package com.tonux.spring.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.tonux.spring.jdbc.model.Utilisateur;

public class UtilisateurDAOImpl implements UtilisateurDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public void save(Utilisateur utilisateur) {
		String query = "insert into Utilisateurs (id, name, role) values (?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, utilisateur.getId());
			ps.setString(2, utilisateur.getName());
			ps.setString(3, utilisateur.getRole());
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Utilisateur créé avec id="+ utilisateur.getId());
			}else System.out.println("Utilisateur création echoué avec id="+ utilisateur.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Utilisateur getById(int id) {
		String query = "select name, role from Utilisateurs where id = ?";
		Utilisateur user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				user = new Utilisateur();
				user.setId(id);
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
				System.out.println("Utilisateur trouvé::"+user);
			}else{
				System.out.println("Pas Utilisateur trouver id="+id);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public void update(Utilisateur utilisateur) {
		String query = "update Utilisateurs set name=?, role=? where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, utilisateur.getName());
			ps.setString(2, utilisateur.getRole());
			ps.setInt(3, utilisateur.getId());
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Utilisateur mise a jour id="+ utilisateur.getId());
			}else System.out.println("Pas Utilisateur trouver id="+ utilisateur.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public void deleteById(int id) {
		String query = "delete from Utilisateurs where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int out = ps.executeUpdate();
			if(out !=0){
				System.out.println("Utilisateur supprimé  id="+id);
			}else System.out.println("Pas Utilisateur trouvé  id="+id);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public List<Utilisateur> getAll() {
		String query = "select id, name, role from Utilisateurs";
		List<Utilisateur> empList = new ArrayList<Utilisateur>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				Utilisateur user = new Utilisateur();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
				empList.add(user);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empList;
	}

}
