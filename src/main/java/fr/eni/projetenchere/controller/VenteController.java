package fr.eni.projetenchere.controller;

import fr.eni.projetenchere.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enchere")
public class VenteController {

    @Autowired
    VenteService nouvelleVenteService;

@GetMapping("/nouvelArticle")
    public String getNouvelleVente(Model model){
    model.addAttribute("categorie");
    return "nouvelleVente";
}

    @PostMapping("/nouvelArticle")
    public String postNouvelleVente(Model model){
        model.addAttribute("");
        return "nouvelleVente";
    }

}
