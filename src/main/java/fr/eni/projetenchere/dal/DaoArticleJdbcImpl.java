package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoArticleJdbcImpl implements DaoArticle {
    private static final String SELECT_ARTICLES = "";
    JdbcTemplate jdbcTemplate;

    @Override
    public void insertArticle() {

    }

    @Override
    public List<Article> selectArticles() {
        return List.of();
    }

    @Override
    public Article selectArticleById(long id) {
        return null;
    }

    @Override
    public void deleteArticle() {

    }

    @Override
    public void updateArticle(Article article) {

    }
}
