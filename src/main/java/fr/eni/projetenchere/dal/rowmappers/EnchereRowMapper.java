package fr.eni.projetenchere.dal.rowmappers;

import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnchereRowMapper implements RowMapper<Enchere> {
    @Override
    public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
        Enchere enchere = new Enchere();
        enchere.setIdEnchere(rs.getLong("id_enchere"));
        enchere.setMontantEnchere(rs.getInt("montant_enchere"));
        enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());

        Utilisateur user = new Utilisateur();
        user.setIdUtilisateur(rs.getLong("id_utilisateur"));
        user.setUsername(rs.getString("username_utilisateur"));
        user.setNom(rs.getString("nom_utilisateur"));
        user.setPrenom(rs.getString("prenom_utilisateur"));
        user.setEmail(rs.getString("email_utilisateur"));
        user.setTelephone(rs.getString("telephone_utilisateur"));
        user.setMotDePasse(rs.getString("mot_de_passe_utilisateur"));
        user.setCredit(rs.getInt("credit_utilisateur"));
        user.setAdministrateur(rs.getBoolean("administrateur_utilisateur"));

        enchere.setUtilisateur(user);

        Article article = new Article();
        article.setIdArticle(rs.getLong("id_article"));
        article.setNom(rs.getString("nom_article"));
        article.setDescription(rs.getString("description_article"));
        article.setDateDebut(rs.getDate("date_debut_vente_article").toLocalDate());
        article.setDateFin(rs.getDate("date_fin_vente_article").toLocalDate());
        article.setMiseAPrix(rs.getInt("prix_initial_article"));
        article.setNoCategorie(rs.getInt("id_categorie"));

        enchere.setArticle(article);

        return enchere;
    }
}
