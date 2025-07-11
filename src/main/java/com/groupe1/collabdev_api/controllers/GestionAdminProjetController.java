package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.services.GestionAdminProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/administrateurs/gestion/projets/")
public class GestionAdminProjetController {
    @Autowired
    private GestionAdminProjetService gestionAdminProjetService;

    //Pour la recuperation des Projets
    @GetMapping
    public List<ProjetDto> getAllProjets() {
        List<Projet> projets = gestionAdminProjetService.afficherListeProjet();
        List<ProjetDto> projetList = new ArrayList<>();
        for (Projet projet : projets) {
            projetList.add(projet.toDto());
        }
        return projetList;
    }

    //Pour activer un Projet :
    @GetMapping("{id}/activer")
    public ProjetDto actviveProjet(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        return gestionAdminProjetService.activerProjet(id, idA).toDto();
    }

    //Pour desactiver un Projet :
    @GetMapping("{id}/desactiver")
    public ProjetDto desactiverProjet(@PathVariable int id, @RequestParam("idAdmin") int idA) {
        return gestionAdminProjetService.desactiverProjet(id, idA).toDto();
    }

    //Pour supprimer un projet :
    @DeleteMapping("{id}")
    public boolean deleteProjet(@PathVariable int id,
                                @RequestParam("idAdmin") int idAdmin) {
        return gestionAdminProjetService.supprimerParId(id, idAdmin);
    }
}
