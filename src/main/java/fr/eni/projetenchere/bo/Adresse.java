package fr.eni.projetenchere.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adresse {
    private Long idAdresse;
    private String rue;
    private String codePostal;
    private String ville;

    public Adresse(String rue,String codePostal, String ville){
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return Objects.equals(rue, adresse.rue) && Objects.equals(codePostal, adresse.codePostal) && Objects.equals(ville, adresse.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rue, codePostal, ville);
    }
}
