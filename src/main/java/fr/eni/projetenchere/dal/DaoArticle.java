package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.bo.FiltreRecherche;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;

import java.util.List;

public interface DaoArticle {
    void insertArticle(Article article, UtilisateurSpringSecurity user);
    List<Article> selectArticles();
    List<Article> selectEncheresOuvertes();

    // TODO: faire un tri des méthodes


    // Methodes propre à un utilisateur
    //List<Article> selectUserEncheresEnCours(long idUser);
    //List<Article> selectUserEncheresremportées(long idUser);
    //List<Article> selectUserVentesEnCours(long idUser);
    //List<Article> selectUserVentesNonDebute(long idUser);
    //List<Article> selectUserVentesTerminer(long idUser);
    Article selectArticleById(long id);
    void deleteArticle(long id);
    void updateArticle(Article article);
    void updatePrixVenteArticle(long idArticle, Enchere enchere);

    List<Article> getArticleSearch(FiltreRecherche recherche);

    List<Article> getEnchereNonDebute(UtilisateurSpringSecurity user);
    List<Article> getEnchereEnCours(UtilisateurSpringSecurity user);
    List<Article> getEnchereTermine(UtilisateurSpringSecurity user);
    List<Article> getVenteNonDebute(UtilisateurSpringSecurity user);
    List<Article> getVenteEnCours(UtilisateurSpringSecurity user);
    List<Article> getVenteTermine(UtilisateurSpringSecurity user);


}
