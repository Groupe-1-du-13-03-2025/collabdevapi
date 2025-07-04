package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.IdeeProjet;
import com.groupe1.collabdev_api.services.IdeeProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs/idees-projet")
public class IdeeProjetController {
    @Autowired
    private IdeeProjetService ideeProjetService;

    @PostMapping
    public IdeeProjet ajouterIdeeProjet(@RequestBody IdeeProjet ideeProjet) {
        ideeProjet.setNombreDeSoutien(0);
        return ideeProjetService.ajouter(ideeProjet);
    }

    @GetMapping
    public List<IdeeProjet> listeIdeeProjet() {
        return ideeProjetService.chercherTous();
    }

    @PutMapping
    public IdeeProjet modifierIdeeProjet(@RequestBody IdeeProjet ideeProjet) {
        return ideeProjetService.modifier(ideeProjet);
    }

    @DeleteMapping("/{id}")
    public Boolean supprimerIdeeProjet(@PathVariable int id) {
        return ideeProjetService.supprimerParId(id);
    }

    @GetMapping("/{id}")
    public IdeeProjet chercherParId(@RequestParam int id) {
        return ideeProjetService.chercherParId(id);
    }

    @PutMapping("/nombre-soutien/{id}")
    public IdeeProjet soutenir(@PathVariable int id) {
        return ideeProjetService.soutenirIdeeProjet(id);
    }
}
