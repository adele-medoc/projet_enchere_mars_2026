package fr.eni.projetenchere.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adresse {
    private String rue;
    private String codePostale;
    private String ville;
}
