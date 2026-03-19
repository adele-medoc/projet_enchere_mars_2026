package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Categorie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoCategorie {
    void insertCategorie(Categorie categorie);
    Categorie selectCategorieById(long idCategorie);
    List<Categorie> selectCategories();
    void deleteCategorie(long idCategorie);
    void updateCategorie(long idCategorie);
}
