package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;

import java.util.List;

public interface DaoEnchere {
    List<Enchere> selectEncheres();
    Enchere selectEnchereById();
    void insertEnchere(Enchere enchere, long idArticle, UtilisateurSpringSecurity user);
    Enchere selectMeilleurOffreArticle(long idArticle);
}
