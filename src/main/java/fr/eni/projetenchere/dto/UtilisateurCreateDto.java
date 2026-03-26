package fr.eni.projetenchere.dto;

import fr.eni.projetenchere.bo.Adresse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurCreateDto {

    @NotBlank
    private String username;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @Email
    private String email;

    @NotBlank
    private String telephone;

    @NotBlank
    private String motDePasse;

    @NotBlank
    private String confirmationMotDePasse;

    @NotNull
    private Adresse adresse;

}
