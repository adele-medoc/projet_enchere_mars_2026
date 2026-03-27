package fr.eni.projetenchere.controller;

import fr.eni.projetenchere.bo.*;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;
import fr.eni.projetenchere.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

import java.util.List;

@Controller
@RequestMapping()
public class VenteController {


    @Autowired
    private VenteService venteService;

    @ModelAttribute("categories")
    public List<Categorie> getCategories(){
        return venteService.consulterCategories();
    }

    @ModelAttribute("articles")public List<Article> getArticles(){
        return venteService.consulterArticles(); }

    @GetMapping
    public String getListeEncheres(Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user) {
        //model.addAttribute("listeArticlesEnCours", venteService.consulterArticles());
        model.addAttribute("user", user);
        model.addAttribute("now", LocalDate.now());
//        model.addAttribute("achat",true);
        model.addAttribute("articles",venteService.consulterArticles());
        model.addAttribute("enchereOuverte",true);
        model.addAttribute("enchereEnCours",true);
        model.addAttribute("enchereRemportee",true);

//        model.addAttribute("vente",false);
        model.addAttribute("venteNonDebute",true);
        model.addAttribute("venteEnCours",true);
        model.addAttribute("venteTermine",true);
        model.addAttribute("filtreRecherche",new FiltreRecherche());
        return "accueil";
    }

    @GetMapping("/enchere/{id}")
    public String getDetailArticle(@PathVariable long id, Model model,@AuthenticationPrincipal UtilisateurSpringSecurity user){
        venteService.retraitArticle(user,id);
        model.addAttribute("article", venteService.consulterArticleById(id));
        model.addAttribute("enchere",new Enchere());
        model.addAttribute("meilleurOffre",venteService.consulterMeilleurOffreEnchere(id));
        model.addAttribute("userActif",user);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Utilisateur actif "+ user.toString() +"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Utilisateur article "+ venteService.consulterArticleById(id).getUtilisateur().toString() +"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
        return "detailVente";
    }

    @PostMapping("/enchere/{id}")
    public String postEnchereArticle(Enchere enchere, @PathVariable long id,@AuthenticationPrincipal UtilisateurSpringSecurity user, Model model,RedirectAttributes redirectAttributes){
        model.addAttribute("article", venteService.consulterArticleById(id));
        //enchere.getArticle().setIdArticle(id);
        try {
            venteService.creerNouvelleEnchere(enchere,id,user);
            redirectAttributes.addFlashAttribute("success", "Enchère effectuée !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            System.out.println(e.getMessage());
        }
        return "redirect:/enchere/{id}";
    }

    @GetMapping("/enchere/nouvelArticle")
        public String getNouvelleVente(Model model,@AuthenticationPrincipal UtilisateurSpringSecurity user){
        model.addAttribute("origine", "nouveau");
        model.addAttribute("article", new Article());
        model.addAttribute("adresseVendeur",user.getUtilisateur().getAdresse());
        return "nouvelleVente";
    }


    @PostMapping("/enchere/nouvelArticle")
    public String postNouvelleVente(Article article, Model model,RedirectAttributes modelRedirect,@AuthenticationPrincipal UtilisateurSpringSecurity user){
        model.addAttribute("origine", "nouveau");
        venteService.creerNouvelleVente(article,user);
        modelRedirect.addFlashAttribute("messageConfirmation", "L'article a bien été enregistrée !");
        return "redirect:/";
    }

    @GetMapping("/enchere/{id}/modifier")
    public String getModifierArticle(Model model,@PathVariable long id){
        System.out.println("********************************"+ venteService.consulterArticleById(id).toString() +"********************************************");
        model.addAttribute("origine", "modifier");
        model.addAttribute("article",venteService.consulterArticleById(id));
        model.addAttribute("article",new Article());

        return "nouvelleVente";
    }

    @PostMapping("/enchere/{id}/modifier")
    public String postModifierArticle(Article article,Model model,@PathVariable long id){
        venteService.modifierArticle(article);
        return "redirect:/enchere/{id}";
    }

    @GetMapping("/enchere/{id}/retraitArticle")
    public String getRetraitArticle(@PathVariable long id,@AuthenticationPrincipal UtilisateurSpringSecurity user){
        venteService.retraitArticle(user,id);

        return "accueil";
    }

}
