package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.rowmappers.UtilisateurRowMapper;
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
            SELECT id_utilisateur, pseudo_utilisateur,nom_utilisateur,prenom_utilisateur,email_utilisateur,telephone_utilisateur ,mot_de_passe_utilisateur,credit_utilisateur,administrateur_utilisateur, ADRESSE.id_adresse ,rue_adresse,code_postale_adresse,ville_adresse
            FROM UTILISATEUR
            Join ADRESSE on ADRESSE.id_adresse = UTILISATEUR.id_adresse
            WHERE pseudo_utilisateur =?;
            """;

    @Override
    public Utilisateur consultUserById(long idUtilisateur) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, new BeanPropertyRowMapper<>(Utilisateur.class),idUtilisateur);
    }

    public Utilisateur consultUserByUsername(String username){
        return jdbcTemplate.queryForObject(SELECT_BY_USERNAME, new UtilisateurRowMapper(),username);
    }

}
