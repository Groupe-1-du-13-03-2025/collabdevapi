package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.controllers.response_entities.Commentaire;
import com.groupe1.collabdev_api.dto.request_dto.RequestCommentaireIdeeProjet;
import com.groupe1.collabdev_api.dto.response_dto.ResponseCommentaireIdeeProjet;
import com.groupe1.collabdev_api.entities.CommentaireIdeeProjet;
import com.groupe1.collabdev_api.entities.CommentaireProjet;
import com.groupe1.collabdev_api.entities.IdeeProjet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.repositories.CommentaireIdeeProjetRepository;
import com.groupe1.collabdev_api.repositories.IdeeProjetRepository;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentaireIdeeProjetService {

    @Autowired
    private CommentaireIdeeProjetRepository commentaireIdeeProjetRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private IdeeProjetRepository ideeProjetRepository;

    public CommentaireIdeeProjet chercherParId(int id){
        return commentaireIdeeProjetRepository.findById(id).orElse(null);
    }

    public List<CommentaireIdeeProjet> chercherTous(){
        return commentaireIdeeProjetRepository.findAll();
    }

    public CommentaireIdeeProjet ajouter(CommentaireIdeeProjet commentaireIdeeProjet) throws RuntimeException{
        if(commentaireIdeeProjet.getUtilisateur()==null){
            throw new RuntimeException("l'utilisateur est vide");
        }
        Optional<Utilisateur> user=utilisateurRepository.findById(commentaireIdeeProjet.getUtilisateur().getId());
                if(user.isPresent()){
                    commentaireIdeeProjet.setUtilisateur(user.get());
                }else {
                   System.out.println("Utilisateur non trouvé");
                }
        IdeeProjet ideeProjet= ideeProjetRepository.findById(commentaireIdeeProjet.getIdeeProjet().getId()).orElseThrow( () -> new RuntimeException("Idée de projet introuvable"))  ;
                CommentaireIdeeProjet commentaire=new CommentaireIdeeProjet();
                commentaire.setIdeeProjet(ideeProjet);
                commentaire.setContenu(commentaireIdeeProjet.getContenu());
                commentaire.setDateCommentaire(commentaireIdeeProjet.getDateCommentaire());


        return commentaireIdeeProjetRepository.save(commentaire);


    }

    public CommentaireIdeeProjet modifier(int id, Commentaire commentaireProjet){
        CommentaireIdeeProjet commentaireProjetmod = commentaireIdeeProjetRepository.findById(id).
                orElseThrow(() -> new RuntimeException("commentaire non trouvé"));
        if (commentaireProjet.getUser()!=null){
            commentaireProjetmod.setUtilisateur(commentaireProjet.getUser());
        }
        if (commentaireProjet.getProjet()!=null){
            commentaireProjetmod.setIdeeProjet(commentaireProjet.getIdeeProjet());
        }
        if (commentaireProjet.getContenu()!=null){
            commentaireProjetmod.setContenu(commentaireProjet.getContenu());
        }
        return commentaireIdeeProjetRepository.save(commentaireProjetmod);


    }

    public Boolean supprimerParId(int id){
        commentaireIdeeProjetRepository.deleteById(id);
        return true;
    }

    public List<ResponseCommentaireIdeeProjet> listerParIdeeProjet(int id){
        List<CommentaireIdeeProjet> commentaireIdeeProjets = commentaireIdeeProjetRepository.findByIdeeProjetId(id);
        List<ResponseCommentaireIdeeProjet> responseCommentaireIdeeProjets = new ArrayList<>();
        for (CommentaireIdeeProjet commentaireIdeeProjet : commentaireIdeeProjets) {
            responseCommentaireIdeeProjets.add(commentaireIdeeProjet.toResponse());
        }
        return responseCommentaireIdeeProjets;
    }
}
