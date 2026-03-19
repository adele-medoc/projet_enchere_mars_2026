package fr.eni.projetenchere.service;

import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.DaoArticle;
import fr.eni.projetenchere.dal.DaoUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenteServiceJdbcImpl implements VenteService {

    @Autowired
    DaoArticle daoArticle;
    @Autowired
    DaoUtilisateur daoUtilisateur;

    @Override
    public void CreerNouvelleVente(Article article) {
    }

    @Override
    public Article supprimerVente() {
        return null;
    }

    @Override
    public List<Categorie> consulterCategories() {
        return List.of();
    }

    @Override
    public List<Article> consulterArticles() {
        return List.of();
    }

    @Override
    public Utilisateur consulterUserAuthentifier() {
        return null;
    }
}
