package fr.eni.projetenchere.service;

import fr.eni.projetenchere.bo.*;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;

import java.util.List;

public interface VenteService {
    void creerNouvelleVente(Article article, UtilisateurSpringSecurity user);
    void supprimerVente(long id);

    void creerNouvelleEnchere(Enchere enchere, long idarticle, UtilisateurSpringSecurity user) throws Exception;
    Enchere consulterMeilleurOffreEnchere(long idArticle);

    List<Categorie> consulterCategories();
    List<Article> consulterArticles();
    Article consulterArticleById(long id);
    Utilisateur consulterUserAuthentifier();

    void modifierArticle(Article article);
    void retraitArticle(UtilisateurSpringSecurity vendeur,long idArticle);
    List<Article> rechercheAvecFiltres(FiltreRecherche r,UtilisateurSpringSecurity user);
}
