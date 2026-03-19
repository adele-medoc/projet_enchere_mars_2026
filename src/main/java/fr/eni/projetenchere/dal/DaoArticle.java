package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Article;

import java.util.List;

public interface DaoArticle {
    void insertArticle(Article article);
    List<Article> selectArticles();
    List<Article> selectEncheresOuvertes();

    Article selectArticleById(long idArticle);
    void deleteArticle();
    void updateArticle(Article article);

    // Methodes propre à un utilisateur
    //List<Article> selectUserEncheresEnCours(long idUser);
    //List<Article> selectUserEncheresremportées(long idUser);
    //List<Article> selectUserVentesEnCours(long idUser);
    //List<Article> selectUserVentesNonDebute(long idUser);
    //List<Article> selectUserVentesTerminer(long idUser);
    Article selectArticleById(long id);
    void deleteArticle(long id);
    void updateArticle(Article article);

}
