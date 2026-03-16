package fr.eni.projetenchere.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {
    private long idCategorie;
    private String libelle;

    public Categorie(String libelle) {
        this.libelle = libelle;
    }
}


