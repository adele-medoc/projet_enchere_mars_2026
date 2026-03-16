package fr.eni.projetenchere.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enchere {
    private long idEnchere;
    private int montantEnchere;
    private LocalDate dateEnchere;
    private Utilisateur utilisateur;
    private Article article;

    public Enchere(int montantEnchere, LocalDate dateEnchere, Utilisateur utilisateur, Article article) {
        this.montantEnchere = montantEnchere;
        this.dateEnchere = dateEnchere;
        this.utilisateur = utilisateur;
        this.article = article;
    }
}
