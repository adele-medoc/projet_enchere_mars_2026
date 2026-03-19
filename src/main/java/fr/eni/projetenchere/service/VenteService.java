package fr.eni.projetenchere.service;

import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Utilisateur;

import java.util.List;

public interface VenteService {
    void CreerNouvelleVente(Article article);
    void supprimerVente(long id);
    List<Categorie> consulterCategories();
    List<Article> consulterArticles();
    Utilisateur consulterUserAuthentifier();

}
