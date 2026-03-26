package fr.eni.projetenchere.controller;

import fr.eni.projetenchere.bo.Utilisateur;
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

    @GetMapping("/login")
    String login() {
        return "login";

    }

//    @GetMapping("/login")
//    public String getUtilisateurs(Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user){
//        model.addAttribute("utilisateurs", utilisateurService.consulterUtilisateurs());
//        model.addAttribute("utilisateur", new Utilisateur());
//        return "login";
//    }
//
//    @PostMapping
//    public String postUtilisateurs(Utilisateur utilisateur){
//        utilisateurService.creerUtilisateur(utilisateur);
//        return "redirect:/";
//    }

}
