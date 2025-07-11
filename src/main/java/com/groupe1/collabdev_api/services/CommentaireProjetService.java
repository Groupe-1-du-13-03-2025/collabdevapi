package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.request_dto.RequestCommentaireProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseCommentaireProjet;
import com.groupe1.collabdev_api.entities.CommentaireProjet;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.repositories.CommentaireProjetRepository;
import com.groupe1.collabdev_api.repositories.ProjetRepository;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentaireProjetService {

    @Autowired
    private CommentaireProjetRepository commentaireProjetRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ProjetRepository projetRepository;

    public CommentaireProjet chercherParId(int id) {
        return commentaireProjetRepository.findById(id).orElse(null);
    }

    public List<CommentaireProjet> chercherTous() {
        return commentaireProjetRepository.findAll();
    }

    public CommentaireProjet ajouter(CommentaireProjet commentaireProjet) {
        Utilisateur user = utilisateurRepository.findById(commentaireProjet.getUtilisateur().getId()).orElseThrow(
                () -> new RuntimeException("Cet utilisateur n'existe pas")
        );
        Projet projet = projetRepository.findById(commentaireProjet.getProjet().getId()).orElseThrow(
                () -> new RuntimeException("Ce projet n'existe pas")
        );

        CommentaireProjet commentaire = new CommentaireProjet();
        commentaire.setUtilisateur(user);
        commentaire.setProjet(projet);
        commentaire.setDateCommentaire(commentaireProjet.getDateCommentaire());
        commentaire.setContenu(commentaireProjet.getContenu());
        return commentaireProjetRepository.save(commentaire);
    }

    public CommentaireProjet modifier(int id, RequestCommentaireProjet requestCommentaireProjet) {
        CommentaireProjet commentaireProjetMod = commentaireProjetRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Commentaire non trouvé!"));
        commentaireProjetMod.setContenu(requestCommentaireProjet.getContenu());
        commentaireProjetMod.setDateCommentaire(LocalDate.now());
        return commentaireProjetRepository.save(commentaireProjetMod);
    }

    public Boolean supprimerParId(int id) {
        commentaireProjetRepository.deleteById(id);
        return true;
    }

    public List<ResponseCommentaireProjet> listerParIdeeProjet(int id) {
        List<CommentaireProjet> commentaireProjets = commentaireProjetRepository.findByProjetId(id);
        List<ResponseCommentaireProjet> responseCommentaireProjets = new ArrayList<>();
        for (CommentaireProjet commentaireProjet : commentaireProjets) {
            responseCommentaireProjets.add(commentaireProjet.toResponse());
        }
        return responseCommentaireProjets;
    }
}

