package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.PorteurProjet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.services.PorteurProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs/porteurs-projet")
public class PorteurProjetController {
    @Autowired
    private PorteurProjetService porteurProjetService;

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
