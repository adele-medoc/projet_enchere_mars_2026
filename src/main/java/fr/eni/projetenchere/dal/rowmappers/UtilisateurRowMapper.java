package fr.eni.projetenchere.dal.rowmappers;

import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurRowMapper implements RowMapper<Utilisateur> {

    @Override
    public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {

        // 1 - créer utilisateur vide
        Utilisateur utilisateur = new Utilisateur();

        // 2 - je lui initialise ses attributs à partir du resultat SQL
//        utilisateur.setIdUtilisateur(rs.getLong("id_utilisateur"));
        utilisateur.setUsername(rs.getString(" username_utilisateur"));
        utilisateur.setNom(rs.getString("nom_utilisateur"));
        utilisateur.setPrenom(rs.getString("prenom_utilisateur"));
        utilisateur.setEmail(rs.getString("email_utilisateur"));
        utilisateur.setTelephone(rs.getString("telephone_utilisateur"));
        utilisateur.setMotDePasse(rs.getString(" mot_de_passe_utilisateur"));
        utilisateur.setCredit(rs.getInt("credit_utilisateur"));
        utilisateur.setAdministrateur(rs.getBoolean("administrateur_utilisateur"));

        // Ajout de l'objet adresse
        Adresse adresse = new Adresse();
        adresse.setIdAdresse(adresse.getIdAdresse());
        adresse.setRue(rs.getString("rue_adresse"));
        adresse.setCodePostal(rs.getString("code_postale_adresse"));
        adresse.setVille(rs.getString("ville_adresse"));

        return utilisateur;
    }
}
