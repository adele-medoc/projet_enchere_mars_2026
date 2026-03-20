package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoCategorieJdbcImpl implements DaoCategorie{
    private static String SELECT_CATEGORIES = "SELECT  id_categorie,libelle_categorie AS libelle FROM [PROJET_ENCHERE].[dbo].[CATEGORIE]";
    private static String SELECT_CATEGORIE_BY_ID = "SELECT * FROM CATEGORIE WHERE id_categorie = ?";
    private static String INSERT_CATEGORIE = "INSERT INTO CATEGORIE(libelle_categorie) VALUES (?)";
    private static String DELETE_CATEGORIE = "DELETE FROM CATEGORIE WHERE id_categorie = ";
    private static String UPDATE_CATEGORIE = "SELECT * FROM CATEGORIE";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insertCategorie(Categorie categorie) {
        jdbcTemplate.update(INSERT_CATEGORIE,categorie.getLibelle());
    }

    @Override
    public Categorie selectCategorieById(long idCategorie) {
        return jdbcTemplate.queryForObject(SELECT_CATEGORIE_BY_ID,new BeanPropertyRowMapper<>(),idCategorie);
    }

    @Override
    public List<Categorie> selectCategories() {
        System.out.println(jdbcTemplate.query(SELECT_CATEGORIES,new BeanPropertyRowMapper<>(Categorie.class)));
        return jdbcTemplate.query(SELECT_CATEGORIES,new BeanPropertyRowMapper<>(Categorie.class));
    }

    @Override
    public void deleteCategorie(long id) {
        jdbcTemplate.update(DELETE_CATEGORIE,id);
    }

    @Override
    public void updateCategorie(long id) {

    }
}
