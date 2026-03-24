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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/utilisateurs")
public class UtilisateurController {



@GetMapping("/gestion")
    public String getUtilisateur(){
    return "gestionCompteUtilisateur";
}

    @Autowired
   private  UtilisateurService utilisateurService;

    @GetMapping
    public String getUtilisateurs(Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user){
        model.addAttribute("utilisateurs", utilisateurService.consulterUtilisateurs());
        model.addAttribute("utilisateur", new Utilisateur());
        return "profilSetup";
    }

    @PostMapping
    public String postUtilisateurs(Utilisateur utilisateur){
        utilisateurService.creerUtilisateur(utilisateur);
        return "redirect:/";
    }

    @PostMapping("/supprimer")
    public String suppressionUtilisateurs(long idUtilisateurASupprimer){
        utilisateurService.supprimerUtilisateur(idUtilisateurASupprimer);
        return "redirect:/utilisateurs";
    }
}
