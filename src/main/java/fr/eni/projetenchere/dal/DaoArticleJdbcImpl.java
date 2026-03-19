package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Utilisateur;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoArticleJdbcImpl implements DaoArticle {
    private static final String SELECT_ARTICLES = """
                                                        SELECT  
                                                            id_article AS idArticle,
                                                            id_utilisateur as idUtilisateur,
                                                            nom_article AS nom,
                                                            description_article AS description,
                                                            date_debut_vente_article AS dateDebut,
                                                            date_fin_vente_article AS dateFin,
                                                            prix_initial_article AS miseAPrix,
                                                            prix_vente_article AS prixVente,
                                                            id_categorie AS noCategorie
                                                            FROM ARTICLE 
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
            VALUES (?,?,?,?,?,?,?,?)
            """;
    JdbcTemplate jdbcTemplate;

    @Override
    public void insertArticle(Article article) {
        jdbcTemplate.update(INSERT_ARTICLE);
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
        return jdbcTemplate.query(SELECT_ARTICLES_EN_COURS, new BeanPropertyRowMapper<>(Article.class));
    }

    @Override
    public void deleteArticle() {

    }

    @Override
    public void updateArticle(Article article) {

    }
}
