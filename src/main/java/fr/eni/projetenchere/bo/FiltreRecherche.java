package fr.eni.projetenchere.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltreRecherche {

    private String searchbar;
    private long categorie;
    private boolean enchereOuverte;
    private boolean enchereEnCours;
    private boolean enchereRemportee;
    private boolean venteNonDebute;
    private boolean venteEnCours;
    private boolean venteTermine;
}
