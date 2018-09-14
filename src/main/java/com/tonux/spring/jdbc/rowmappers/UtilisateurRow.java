package com.tonux.spring.jdbc.rowmappers;

import com.tonux.spring.jdbc.model.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UtilisateurRow implements RowMapper<Utilisateur> {


    public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
        Utilisateur user = new Utilisateur();

        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setRole(rs.getString("role"));

        return user;
    }


}
