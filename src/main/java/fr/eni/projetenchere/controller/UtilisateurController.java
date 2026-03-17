package fr.eni.projetenchere.controller;

import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UtilisateurController {
@GetMapping("/")
    public String accueil(){
        return "accueil";
    }



//    @Autowired
//    UtilisateurService utilisateurService;
//
//    @GetMapping
//    public String getUtilisateurs(Model model){
//        model.addAttribute("utilisateurs", utilisateurService.consulterUtilisateurs());
//        model.addAttribute("utilisateur", new Utilisateur());
//        return "header";
//    }
//
//    @PostMapping
//    public String postUtilisateurs(Utilisateur utilisateur){
//        utilisateurService.creerUtilisateur(utilisateur);
//        return "redirect:/utilisateurs";
//    }
//
//    @PostMapping("/supprimer")
//    public String suppressionUtilisateurs(long idUtilisateurASupprimer){
//        utilisateurService.supprimerUtilisateur(idUtilisateurASupprimer);
//        return "redirect:/utilisateurs";
//    }
}
