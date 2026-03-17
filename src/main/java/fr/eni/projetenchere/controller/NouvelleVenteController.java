package fr.eni.projetenchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nouvelleVente")
public class NouvelleVenteController {

@GetMapping
    public String getNouvelleVente(){
    return "nouvelleVente";
}

}
