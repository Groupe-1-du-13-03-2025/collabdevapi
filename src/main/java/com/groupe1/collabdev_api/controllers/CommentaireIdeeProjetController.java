package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.controllers.response_entities.Commentaire;
import com.groupe1.collabdev_api.dto.response_dto.ResponseCommentaireIdeeProjet;
import com.groupe1.collabdev_api.entities.CommentaireIdeeProjet;
import com.groupe1.collabdev_api.services.CommentaireIdeeProjetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "idees-projet",
        description = """
                Ce contrôleur permet d'ajouter des commentaires de projets, les lister, modifier et supprimer""")
@RequestMapping("/utilisateurs/commentaires-projets")
public class CommentaireIdeeProjetController {
    @Autowired
    private CommentaireIdeeProjetService commentaireIdeeProjetService;

    @PostMapping
    public ResponseEntity<?> ajouterCommentaire(@RequestBody CommentaireIdeeProjet commentaire) {
        try {
            CommentaireIdeeProjet commentaireIdeeProjet = commentaireIdeeProjetService.ajouter(commentaire);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentaireIdeeProjet.toResponse());

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/projets/{idProjet}")
    public List<ResponseCommentaireIdeeProjet> listerCommentaireParIdProjet(
            @PathVariable int idProjet
    ) {
        return commentaireIdeeProjetService.listerParIdeeProjet(idProjet);
    }

    @GetMapping("/{id}")
    public ResponseCommentaireIdeeProjet chercherParId(@PathVariable Integer id) {
        return commentaireIdeeProjetService.chercherParId(id).toResponse();
    }

    @DeleteMapping("/{id}")
    public boolean supprimerCommentaire(@PathVariable Integer id) {
        return commentaireIdeeProjetService.supprimerParId(id);
    }

    @PatchMapping("/{id}")
    public ResponseCommentaireIdeeProjet PratModification(@PathVariable Integer id, @RequestBody Commentaire commentaireProjet) {
        return commentaireIdeeProjetService.modifier(id, commentaireProjet).toResponse();
    }

}
