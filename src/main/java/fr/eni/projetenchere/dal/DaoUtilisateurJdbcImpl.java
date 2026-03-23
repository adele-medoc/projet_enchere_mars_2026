package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DaoUtilisateurJdbcImpl implements DaoUtilisateur{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_BY_ID = """
            SELECT *
            FROM UTILISATEUR
            WHERE id_utilisateur = ?
            """;
    private static final String SELECT_BY_USERNAME = """
            SELECT *
            FROM UTILISATEUR
            WHERE pseudo_utilisateur = ?
            """;

    @Override
    public Utilisateur consultUserById(long idUtilisateur) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, new BeanPropertyRowMapper<>(Utilisateur.class),idUtilisateur);
    }

    public Utilisateur consultUserByUsername(String username){
        return jdbcTemplate.queryForObject(SELECT_BY_USERNAME, new BeanPropertyRowMapper<>(Utilisateur.class),username);
    }

}
