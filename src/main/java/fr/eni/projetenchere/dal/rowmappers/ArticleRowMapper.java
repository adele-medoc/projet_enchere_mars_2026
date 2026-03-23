package fr.eni.projetenchere.dal.rowmappers;

import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {

        // 1 - créer article vide
        Article article = new Article();

        // 2 - je lui initialise ses attributs à partir du resultat SQL
        article.setIdArticle(rs.getLong("id_article"));
        article.setNom(rs.getString("nom_article"));
        article.setDescription(rs.getString("description_article"));
        article.setDateDebut(rs.getDate("date_debut_vente_article").toLocalDate());
        article.setDateFin(rs.getDate("date_fin_vente_article").toLocalDate());
        article.setMiseAPrix(rs.getInt("prix_initial_article"));

        // 3 - gestion du prix de fin de vente qui doit pouvoir être nul au début
            Integer prixVenteFinal = rs.getObject("prix_vente_article", Integer.class);
            article.setPrixVente(prixVenteFinal);

        // Ajout de l'objet adresse
        Adresse adresse = new Adresse();
        adresse.setIdAdresse(adresse.getIdAdresse());
        adresse.setRue(rs.getString("rue_adresse"));
        adresse.setCodePostal(rs.getString("code_postale_adresse"));
        adresse.setVille(rs.getString("ville_adresse"));


        // Ajout de l'objet utilisateur
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(rs.getLong("id_utilisateur"));
        utilisateur.setUsername(rs.getString("username_utilisateur"));
        utilisateur.setCredit(rs.getInt("credit_utilisateur"));
        utilisateur.setAdresse(adresse);

        article.setUtilisateur(utilisateur);

        return article;
    }

}
