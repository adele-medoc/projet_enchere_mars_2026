package fr.eni.projetenchere.controller;

import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dto.UtilisateurCreateDto;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;
import fr.eni.projetenchere.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UtilisateurService utilisateurService;

//    @GetMapping("/login")
//    String login() {
//        return "login";
//    }

    @GetMapping("/login")
    String login(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());

        return "login";

    }

    @PostMapping
    public String postUtilisateurs(UtilisateurCreateDto utilisateurDto){
        utilisateurService.creerUtilisateur(utilisateurDto);
        return "redirect:/";
    }

}
