package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.rowmappers.UtilisateurRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
            SELECT id_utilisateur, pseudo_utilisateur,nom_utilisateur,prenom_utilisateur,email_utilisateur,telephone_utilisateur ,mot_de_passe_utilisateur,credit_utilisateur,administrateur_utilisateur, 
                   ADRESSE.id_adresse ,rue_adresse,code_postale_adresse,ville_adresse       
            FROM UTILISATEUR        
            Join ADRESSE on ADRESSE.id_adresse = UTILISATEUR.id_adresse       
            WHERE pseudo_utilisateur = ?;
            """;

    private static final String UPDATE_BY_ID = """
                                               UPDATE UTILISATEUR
                                               SET pseudo_utilisateur = :pseudo,
                                                   nom_utilisateur = :nom,
                                                   prenom_utilisateur = :prenom,
                                                   email_utilisateur = :email,
                                                   telephone_utilisateur = :telephone,
                                                   mot_de_passe_utilisateur = :motDePasse
                                               WHERE id_utilisateur = :id
                                               """;
    private static final String UPDATE_USER_ADRESS = """                                           
                                                        UPDATE a
                                                         SET a.rue_adresse = :rue,
                                                         a.code_postale_adresse = :codePostal,
                                                         a.ville_adresse = :ville
                                                         FROM ADRESSE a
                                                         INNER JOIN UTILISATEUR u
                                                         ON u.id_adresse = a.id_adresse
                                                         WHERE u.id_utilisateur = :id;
                                                     """;

    @Override
    public Utilisateur consultUserById(long idUtilisateur) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, new BeanPropertyRowMapper<>(Utilisateur.class),idUtilisateur);
    }

    public Utilisateur consultUserByUsername(String username){
        return jdbcTemplate.queryForObject(SELECT_BY_USERNAME, new UtilisateurRowMapper() ,username);
    }

    // TODO : finir méthode update
    @Override
    public void updateUserInfo(long id, Utilisateur utilisateur) {
        MapSqlParameterSource paramsUtilisateur = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("pseudo", utilisateur.getPseudo())
                .addValue("nom", utilisateur.getNom())
                .addValue("prenom", utilisateur.getPrenom())
                .addValue("email", utilisateur.getEmail())
                .addValue("telephone", utilisateur.getTelephone())
                .addValue("motDePasse", utilisateur.getMotDePasse());

            MapSqlParameterSource paramsAdresse = new MapSqlParameterSource()
                    .addValue("id", utilisateur.getAdresse().getIdAdresse())
                    .addValue("rue", utilisateur.getAdresse().getRue())
                    .addValue("codePostal", utilisateur.getAdresse().getCodePostal())
                    .addValue("ville", utilisateur.getAdresse().getVille());

        jdbcTemplate.update(UPDATE_USER_ADRESS, paramsAdresse);
        jdbcTemplate.update(UPDATE_BY_ID, paramsUtilisateur);

    }
}
