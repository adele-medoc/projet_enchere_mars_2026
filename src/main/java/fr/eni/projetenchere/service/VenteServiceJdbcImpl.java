package fr.eni.projetenchere.service;

import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.DaoArticle;
import fr.eni.projetenchere.dal.DaoCategorie;
import fr.eni.projetenchere.dal.DaoUtilisateur;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenteServiceJdbcImpl implements VenteService {

    @Autowired
    DaoArticle daoArticle;
    @Autowired
    DaoUtilisateur daoUtilisateur;
    @Autowired
    DaoCategorie daoCategorie;

    @Override
    public void CreerNouvelleVente(Article article, UtilisateurSpringSecurity user) {
        daoArticle.insertArticle(article,user);
    }

    @Override
    public void supprimerVente(long id) {
         daoArticle.deleteArticle(id);
    }

    @Override
    public List<Categorie> consulterCategories() {

        return daoCategorie.selectCategories();
    }

    @Override
    public List<Article> consulterArticles() {
        return daoArticle.selectEncheresOuvertes();

    }

    @Override
    public Article selectArticleById(long id) {
        return daoArticle.selectArticleById(id);
    }

    @Override
    public Utilisateur consulterUserAuthentifier() {
        return null;
    }
}
