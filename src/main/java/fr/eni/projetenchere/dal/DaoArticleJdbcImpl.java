package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Utilisateur;
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
                                                              u.pseudo_utilisateur, u.credit_utilisateur, 
                                                              ad.rue_adresse, ad.code_postale_adresse, ad.ville_adresse
                                                       FROM ARTICLE a
                                                       INNER JOIN UTILISATEUR U ON u.id_utilisateur = a.id_utilisateur
                                                       INNER JOIN ADRESSE ad ON ad.id_adresse = a.id_adresse
                                                    """;
    private static final String SELECT_ARTICLE_BY_ID = """
            SELECT *
            FROM ARTICLE
            WHERE id_article = ?
            """;
    private static final String SELECT_ARTICLES_EN_COURS = SELECT_ARTICLES + " " + """ 
            WHERE date_fin_vente_article > GETDATE() AND prix_vente_article IS NULL
            """;

    private static final String INSERT_ARTICLE = """
            INSERT INTO ARTICLE(nom_article,description_article,date_debut_vente_article,date_fin_vente_article,prix_initial_article,id_utilisateur,id_categorie,id_adresse)
            VALUES (:nom,:description,:dateDebut,:dateFin,:prixInitial,:idVendeur,:idCategorie,:idAdresse)
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
                .addValue("idVendeur",user.getUserId())
                .addValue("idCategorie",article.getNoCategorie())
                .addValue("idAdresse",article.getAdresseRetrait().getIdAdresse());

        namedParameterJdbcTemplate.update(INSERT_ARTICLE,params);
    }

    @Override
    public List<Article> selectArticles() {
        return jdbcTemplate.query(SELECT_ARTICLES, new BeanPropertyRowMapper<>(Article.class));
    }

    @Override
    public Article selectArticleById(long idArticle) {
        return jdbcTemplate.queryForObject(SELECT_ARTICLE_BY_ID, new BeanPropertyRowMapper<>(Article.class), idArticle);
    }

    //TODO: il faudra un ArticleRowMapper pour récupérer les infos de l'utilisateur (pseudo, id) et de la Catégorie (libellé)
    @Override
    public List<Article> selectEncheresOuvertes() {
        return jdbcTemplate.query(SELECT_ARTICLES_EN_COURS, new ArticleRowMapper());
    }

    @Override
    public void deleteArticle(long id) {

    }

    @Override
    public void updateArticle(Article article) {

    }
}
