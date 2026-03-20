package fr.eni.projetenchere.controller;

import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class VenteController {

    //TODO : choisir UN service

    @Autowired
    private VenteService venteService;

    @GetMapping
    public String getListeEncheres(Model model) {
        model.addAttribute("listeArticlesEnCours", venteService.consulterArticles());
        return "accueil";
    }



    @GetMapping("/enchere/{id}")
    public String getDetailArticle(@PathVariable long id, Model model){
        //model.addAttribute("article", ArticleService.consulterArticleParId(id));
        return "";
    }

    @GetMapping("/enchere/nouvelArticle")
        public String getNouvelleVente(Model model){
        model.addAttribute("categorie", venteService.consulterCategories());
        return "nouvelleVente";
    }

    @PostMapping("/enchere/nouvelArticle")
    public String postNouvelleVente(Model model){
        model.addAttribute("article",  new Article());
        model.addAttribute("adresse",new Adresse());
        return "nouvelleVente";
    }

}
