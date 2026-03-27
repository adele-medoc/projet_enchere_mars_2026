package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.dal.rowmappers.EnchereRowMapper;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoEnchereJdbcImpl implements DaoEnchere{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static String INSERT_ENCHERE = "INSERT INTO ENCHERE(montant_enchere,date_enchere,id_utilisateur,id_article) VALUES(?,GETUTCDATE(),?,?)";
    private static String SELECT_MEILLEUR_OFFRE = """
                  SELECT id_enchere, montant_enchere,date_enchere, UTILISATEUR.*,Article.nom_article,ARTICLE.description_article, ARTICLE.id_article,date_debut_vente_article,date_fin_vente_article,prix_initial_article,prix_vente_article,id_categorie
            FROM ENCHERE
            JOIN ARTICLE on ENCHERE.id_article = ARTICLE.id_article
            JOIN UTILISATEUR on ENCHERE.id_utilisateur = UTILISATEUR.id_utilisateur
            WHERE ENCHERE.id_article = ? AND montant_enchere = (
                            									SELECT MAX(montant_enchere)
                            									FROM ENCHERE
                            									WHERE id_article = ?);""";
    @Override
    public void insertEnchere(Enchere enchere, long idArticle, UtilisateurSpringSecurity user) {
        jdbcTemplate.update(INSERT_ENCHERE,enchere.getMontantEnchere(),user.getUtilisateur().getIdUtilisateur(),idArticle);
    }

    @Override
    public Enchere selectMeilleurOffreArticle(long idArticle) {

        List<Enchere> rs =  jdbcTemplate.query(SELECT_MEILLEUR_OFFRE,new EnchereRowMapper(),idArticle,idArticle);
        if(rs.isEmpty()){
            return null;
        }else{
            System.out.println("-------------------------------- meilleur offre = " + rs);
            return rs.get(0);
        }
    }

    private static String SELECT_ENCHERES = "";
    private static String SELECT_ENCHERE_BY_USER_ID = "";
    private static String SELECT_ENCHERE_BY_ID = "";

    @Override
    public List<Enchere> selectEncheres() {
        return List.of();
    }

    @Override
    public Enchere selectEnchereById() {
        return null;
    }


}
