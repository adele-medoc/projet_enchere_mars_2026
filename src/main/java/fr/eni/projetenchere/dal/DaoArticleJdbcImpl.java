package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.*;
import fr.eni.projetenchere.dal.rowmappers.ArticleRowMapper;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoArticleJdbcImpl implements DaoArticle {
    private static final String SELECT_ARTICLES = """
                                                       SELECT a.*, 
                                                              u.username_utilisateur, u.credit_utilisateur, 
                                                              ad.rue_adresse, ad.code_postale_adresse, ad.ville_adresse
                                                       FROM ARTICLE a
                                                       INNER JOIN UTILISATEUR U ON u.id_utilisateur = a.id_utilisateur
                                                       INNER JOIN CATEGORIE ON a.id_categorie = CATEGORIE.id_categorie
                                                       INNER JOIN ADRESSE ad ON ad.id_adresse = a.id_adresse
                                                    """;
    private static final String SELECT_ARTICLE_BY_ID = """
              SELECT ARTICLE.*, rue_adresse, code_postale_adresse, ville_adresse, UTILISATEUR.id_utilisateur,username_utilisateur,credit_utilisateur
              FROM ARTICLE
              JOIN UTILISATEUR ON ARTICLE.id_utilisateur = UTILISATEUR.id_utilisateur
              JOIN CATEGORIE ON ARTICLE.id_categorie = CATEGORIE.id_categorie
              JOIN ADRESSE ON ARTICLE.id_adresse = ADRESSE.id_adresse
            WHERE id_article = ?
            """;
    private static final String SELECT_ARTICLES_EN_COURS = SELECT_ARTICLES + " " + """ 
            WHERE date_fin_vente_article > GETDATE() AND prix_vente_article IS NULL
            """;

    private static final String INSERT_ARTICLE = """
            INSERT INTO ARTICLE(nom_article,description_article,date_debut_vente_article,date_fin_vente_article,prix_initial_article,id_utilisateur,id_categorie,id_adresse)
            VALUES (:nom,:description,:dateDebut,:dateFin,:prixInitial,:idVendeur,:idCategorie,:idAdresse)
            """;

    private static final String UPDATE_ARTICLE =
            """ 
        UPDATE ARTICLE
        SET nom_article = ?, description_article =?, date_debut_vente_article = ?, date_fin_vente_article = ?,
        prix_initial_article = ?,id_categorie = ?
        where id_article = ?
        """;
    private static final String UPDATE_PRIX_VENTE_ARTICLE = """
            UPDATE ARTICLE
            SET prix_vente_article = ?
            where id_article = ?
            """;


    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void insertArticle(Article article, UtilisateurSpringSecurity user) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("nom",article.getNom())
                .addValue("description",article.getDescription())
                .addValue("dateDebut",article.getDateDebut())
                .addValue("dateFin",article.getDateFin())
                .addValue("prixInitial",article.getMiseAPrix())
                .addValue("idVendeur",user.getUtilisateur().getIdUtilisateur())
                .addValue("idCategorie",article.getNoCategorie())
                .addValue("idAdresse",article.getAdresseRetrait().getIdAdresse());

        namedParameterJdbcTemplate.update(INSERT_ARTICLE,params);
    }

    @Override
    public List<Article> selectArticles() {
        return jdbcTemplate.query(SELECT_ARTICLES, new ArticleRowMapper());
    }

    @Override
    public Article selectArticleById(long idArticle) {
        return jdbcTemplate.queryForObject(SELECT_ARTICLE_BY_ID, new ArticleRowMapper(), idArticle);
    }

    @Override
    public List<Article> selectEncheresOuvertes() {
        return jdbcTemplate.query(SELECT_ARTICLES_EN_COURS, new ArticleRowMapper());
    }

    @Override
    public void deleteArticle(long id) {

    }

    @Override
    public void updateArticle(Article article) {
        jdbcTemplate.update(UPDATE_ARTICLE,article.getNom(),article.getDescription(),article.getDateDebut(),article.getDateFin(),article.getMiseAPrix(),article.getNoCategorie(),article.getIdArticle());
    }
    public void updatePrixVenteArticle(long idArticle, Enchere enchere){
        jdbcTemplate.update(UPDATE_PRIX_VENTE_ARTICLE,enchere.getMontantEnchere(), idArticle);
    }
}
