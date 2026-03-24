package fr.eni.projetenchere.dal.rowmappers;

import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurRowMapper implements RowMapper<Utilisateur> {
    @Override
    public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
        Utilisateur user= new Utilisateur();

        user.setIdUtilisateur(rs.getLong("id_utilisateur"));
        user.setUsername(rs.getString("pseudo_utilisateur"));
        user.setNom(rs.getString("nom_utilisateur"));
        user.setPrenom(rs.getString("prenom_utilisateur"));
        user.setEmail(rs.getString("email_utilisateur"));
        user.setTelephone(rs.getString("telephone_utilisateur"));
        user.setMotDePasse(rs.getString("mot_de_passe_utilisateur"));
        user.setCredit(rs.getInt("credit_utilisateur"));
        user.setAdministrateur(rs.getBoolean("administrateur_utilisateur"));
        Adresse adresse = new Adresse();
        adresse.setIdAdresse(rs.getLong("id_adresse"));
        adresse.setRue(rs.getString("rue_adresse"));
        adresse.setVille(rs.getString("ville_adresse"));
        adresse.setCodePostal(rs.getString("code_postale_adresse"));
        user.setAdresse(adresse);
        return user;
    }
}
