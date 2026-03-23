package fr.eni.projetenchere.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class DaoAdresseJdbcImpl implements DaoAdresse {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static String INSERT_ADRESSE = "  INSERT INTO ADRESSE(rue_adresse,code_postale_adresse,ville_adresse) VALUES (:rue,:codePostal,:ville)";
    private static String SELECT_ADRESSE_BY_ID = " SELECT * FROM ADRESSE WHERE id_adresse =  ";

    public void insertAdresse(String rue,String codePostal, String ville){
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("rue",rue)
                .addValue("codePostal",codePostal)
                .addValue("ville",ville);
        namedParameterJdbcTemplate.update(INSERT_ADRESSE,params);
    }

    public long insertAdresseAndGetID(String rue,String codePostal, String ville){
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("rue",rue)
                .addValue("codePostal",codePostal)
                .addValue("ville",ville);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT_ADRESSE,params,keyHolder);

        if(keyHolder !=null && keyHolder.getKey()!=null){
            long idAdresse= keyHolder.getKey().longValue();
            return idAdresse;
        }else{
            return 0;
        }
    }
}
