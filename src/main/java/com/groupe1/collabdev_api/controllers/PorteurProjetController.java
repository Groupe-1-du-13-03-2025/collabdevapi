package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.PorteurProjet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.services.PorteurProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/porteurs_projet")
public class PorteurProjetController {
    @Autowired
    private PorteurProjetService porteurProjetService;

    @PostMapping
    public PorteurProjet ajouter(@RequestBody Utilisateur user) {
        return porteurProjetService.ajouter(user);
    }

    @GetMapping("/{id}")
    public PorteurProjet rechercheParId(@RequestParam int id) {
        return porteurProjetService.chercherParId(id);
    }

    @GetMapping
    public List<PorteurProjet> listerPorteurProjet() {
        return porteurProjetService.chercherTous();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PorteurProjet> modifierParId(@PathVariable int id, @RequestBody Utilisateur user) {
        PorteurProjet porteurProjet = porteurProjetService.modifier(id, user);
        return ResponseEntity.ok(porteurProjet);
    }

    @DeleteMapping("/{id}")
    public void supprimerParId(@PathVariable int id) {
        porteurProjetService.supprimerParId(id);
    }


}
