package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Utilisateur")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> utilisateurList() {
        return utilisateurService.chercherTous();
    }

    @GetMapping("/{id}")
    public Utilisateur userById(
            @PathVariable int idUser
    ) {
        return utilisateurService.chercherParId(idUser);
    }

    @PostMapping("/new")
    public Utilisateur createUser(
            @RequestBody Utilisateur user
    ) {
        return utilisateurService.ajouter(user);
    }
}
