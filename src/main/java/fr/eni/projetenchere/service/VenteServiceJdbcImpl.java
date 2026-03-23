package fr.eni.projetenchere.service;

import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Utilisateur;
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

    @Override
    public void CreerNouvelleVente(Article article, UtilisateurSpringSecurity user) {
        Adresse adresseRetrait= new Adresse(article.getAdresseRetrait().getRue(),article.getAdresseRetrait().getCodePostal(),article.getAdresseRetrait().getVille());
        // Si l'@ de retrait et l'@ de l'utilisateur actif sont les mêmes alors l'article à pour id_adresse le même que l'utilisateur
        if(adresseRetrait.equals(user.getUtilisateur().getAdresse())){
            article.getAdresseRetrait().setIdAdresse(user.getUtilisateur().getAdresse().getIdAdresse());
        // Si non on créer l'@ en base et on retourne l'id pour l'associer à l'article
        }else{
            long idAdresse = daoAdresse.insertAdresseAndGetID(article.getAdresseRetrait().getRue(),article.getAdresseRetrait().getCodePostal(),article.getAdresseRetrait().getVille());
            article.getAdresseRetrait().setIdAdresse(idAdresse);
        }

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
    public Utilisateur consulterUserAuthentifier() {
        return null;
    }
}
