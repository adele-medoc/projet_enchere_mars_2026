package fr.eni.projetenchere.service;

import fr.eni.projetenchere.bo.*;
import fr.eni.projetenchere.dal.*;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenteServiceJdbcImpl implements VenteService {

    @Autowired
    DaoArticle daoArticle;
    @Autowired
    DaoCategorie daoCategorie;
    @Autowired
    DaoAdresse daoAdresse;
    @Autowired
    DaoEnchere daoEnchere;

    @Override
    public void CreerNouvelleVente(Article article, UtilisateurSpringSecurity user) {
        // Si l'@ de retrait est vide par défaut c'est celle du vendeur
        if(article.getAdresseRetrait().getRue().isEmpty() && article.getAdresseRetrait().getCodePostal().isEmpty() && article.getAdresseRetrait().getVille().isEmpty()){
            article.setAdresseRetrait(user.getUtilisateur().getAdresse());

        }else {
        Adresse adresseRetrait= new Adresse(article.getAdresseRetrait().getRue(),article.getAdresseRetrait().getCodePostal(),article.getAdresseRetrait().getVille());
        // Si l'@ de retrait et l'@ de l'utilisateur actif sont les mêmes alors l'article à pour id_adresse le même que l'utilisateur
        if(adresseRetrait.equals(user.getUtilisateur().getAdresse()) ){
            article.getAdresseRetrait().setIdAdresse(user.getUtilisateur().getAdresse().getIdAdresse());
        // Si non on créer l'@ en base et on retourne l'id pour l'associer à l'article
        }else{
            long idAdresse = daoAdresse.insertAdresseAndGetID(article.getAdresseRetrait().getRue(),article.getAdresseRetrait().getCodePostal(),article.getAdresseRetrait().getVille());
            article.getAdresseRetrait().setIdAdresse(idAdresse);
        }}

        daoArticle.insertArticle(article,user);
    }

    @Override
    public void supprimerVente(long id) {
         daoArticle.deleteArticle(id);
    }

    @Override
    public void CreerNouvelleEnchere(Enchere enchere, long idarticle, UtilisateurSpringSecurity user) {
        daoEnchere.insertEnchere(enchere,idarticle,user);
    }
    public Enchere consulterMeilleurOffreEnchere(long idArticle){
        return daoEnchere.selectMeilleurOffreArticle(idArticle);
    }

    @Override
    public List<Categorie> consulterCategories() {

        return daoCategorie.selectCategories();
    }

    @Override
    public List<Article> consulterArticles() {
        return daoArticle.selectEncheresOuvertes();

    }

    public Article consulterArticleById(long id){
        return daoArticle.selectArticleById(id);
    }


    @Override
    public Utilisateur consulterUserAuthentifier() {
        return null;
    }


}
