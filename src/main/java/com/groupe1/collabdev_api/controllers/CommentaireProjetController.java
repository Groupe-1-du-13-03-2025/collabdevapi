package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.request_dto.RequestCommentaireProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseCommentaireProjet;
import com.groupe1.collabdev_api.entities.CommentaireProjet;
import com.groupe1.collabdev_api.repositories.CommentaireProjetRepository;
import com.groupe1.collabdev_api.services.CommentaireProjetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs/commentaires-idees-projet")
@Tag(name = "projets",
        description = """
                Ce contrôleur permet d'ajouter des commentaires de projets, les lister, modifier et supprimer""")
public class CommentaireProjetController {
    @Autowired
    private CommentaireProjetService commentaireProjetService;
    private CommentaireProjetRepository commentaireProjetRepository;

    @PostMapping
    public CommentaireProjet ajouterCommentaire(@RequestBody CommentaireProjet commentaire) {
        return commentaireProjetService.ajouter(commentaire);
    }

    @GetMapping("/projets/{idProjet}")
    public ResponseEntity<?> listeCommentaireParIdProjet(
            @PathVariable int idProjet
    ) {
        List<ResponseCommentaireProjet> commentaireProjets = commentaireProjetService.listerParIdeeProjet(idProjet);
        if (commentaireProjets.isEmpty()) {
            return ResponseEntity.ok("Pas de commentaire pour ce projet");
        }
        return ResponseEntity.ok(commentaireProjets);
    }

    @GetMapping("/{id}")
    public ResponseCommentaireProjet chercherParId(@PathVariable Integer id) {
        return commentaireProjetService.chercherParId(id).toResponse();
    }

    @DeleteMapping("/{id}")
    public boolean supprimerCommentaire(@PathVariable int id) {
        return commentaireProjetService.supprimerParId(id);
    }

    @PutMapping("/{id}")
    public CommentaireProjet modifier(@PathVariable int id, @RequestBody RequestCommentaireProjet requestCommentaireProjet) {
        CommentaireProjet comExiste = commentaireProjetRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Commentaire introuvable!"));
        comExiste.setDateCommentaire(requestCommentaireProjet.getDateCommentaire());
        comExiste.setContenu(requestCommentaireProjet.getContenu());
        return commentaireProjetRepository.save(comExiste);
    }

    @PatchMapping("/{id}")
    public ResponseCommentaireProjet PratModification(@PathVariable Integer id, @RequestBody RequestCommentaireProjet requestCommentaireProjet) {
        return commentaireProjetService.modifier(id, requestCommentaireProjet).toResponse();
    }
}
