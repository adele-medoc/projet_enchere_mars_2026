package fr.eni.projetenchere.service;

import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;

import java.util.List;

public interface VenteService {
    void CreerNouvelleVente(Article article, UtilisateurSpringSecurity user);
    void supprimerVente(long id);
    List<Categorie> consulterCategories();
    List<Article> consulterArticles();
    Article selectArticleById(long id);
    Utilisateur consulterUserAuthentifier();

}
