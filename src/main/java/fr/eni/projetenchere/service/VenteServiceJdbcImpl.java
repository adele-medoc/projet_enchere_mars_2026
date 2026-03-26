package fr.eni.projetenchere.service;

import fr.eni.projetenchere.bo.*;
import fr.eni.projetenchere.dal.*;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    @Autowired
    DaoUtilisateur daoUser;

    @Override
    public void creerNouvelleVente(Article article, UtilisateurSpringSecurity user) {
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
    public void creerNouvelleEnchere(Enchere enchere, long idArticle, UtilisateurSpringSecurity user) throws Exception {
        Enchere meilleurEnchere = daoEnchere.selectMeilleurOffreArticle(idArticle);
        // si l'enchère emise par l'acheteur est inférieur à son credit et que l'enchère est plus haute que la meilleur enchere pour l'instant alors l'enchère est enregistrer dans la base de donnée
        if(user.getUtilisateur().getCredit() > enchere.getMontantEnchere() && enchere.getMontantEnchere() > meilleurEnchere.getMontantEnchere()) {
            daoEnchere.insertEnchere(enchere,idArticle,user);
        }else{
            throw new Exception("Enchère impossible à réaliser. solde insuffisant ou enchère inférieur à la meilleur offre");
        }
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
        return daoArticle.selectArticles();

    }

    public Article consulterArticleById(long id){
        return daoArticle.selectArticleById(id);
    }


    @Override
    public Utilisateur consulterUserAuthentifier() {
        return null;
    }

    @Override
    public void modifierArticle(Article article) {
        daoArticle.updateArticle(article);
    }

    public void retraitArticle(UtilisateurSpringSecurity vendeur,long idArticle){
        Article article = consulterArticleById(idArticle);
        // si il n'y a pas de prix de vente et que la date de fin d'enchère est passé et que la meilleur offre existe
        // alors on fait les changement en bdd et on ajout le prix de vente de l'enchère
        if( article.getPrixVente() != null && article.getDateFin().isBefore(LocalDate.now()) && consulterMeilleurOffreEnchere(article.getIdArticle())!=null){
            Enchere meilleurOffre = consulterMeilleurOffreEnchere(idArticle);
            Utilisateur acheteur= meilleurOffre.getUtilisateur();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++ acheteur = "+acheteur.toString());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++ vendeur = "+ vendeur.toString());
            daoUser.updateCreditUtilisateur(vendeur.getUserId(),vendeur.getUtilisateur().getCredit() + meilleurOffre.getMontantEnchere());
            daoUser.updateCreditUtilisateur(acheteur.getIdUtilisateur(),acheteur.getCredit()-meilleurOffre.getMontantEnchere());
            daoArticle.updatePrixVenteArticle(idArticle,meilleurOffre);
            article.setPrixVente(meilleurOffre.getMontantEnchere());

        }
    }


}
