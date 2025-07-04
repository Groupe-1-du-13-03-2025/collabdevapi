package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur chercherParId(int id){

        return utilisateurRepository.findById(id).orElse(null);
    }

    public List<Utilisateur> chercherTous(){

        return utilisateurRepository.findAll();
    }

    public Utilisateur ajouter(Utilisateur utilisateur){

        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur modifier(Utilisateur utilisateur){

        return utilisateurRepository.save(utilisateur);
    }

    public Boolean supprimerParId(int id){
        utilisateurRepository.deleteById(id);
        return true;
    }

    public String debloqueUser(int id){
        return "user debloquer";
    }


    public String bloqueUser(int id){
        return "user bloquer";
    }

    public String validerDemande(int id){
        return "demande valider";
    }

    public String rejeterDemande(int id) {
        return "demande rejetter";
    }


    /*  pour bloquer les utilisateurs
    @PostMapping("/utilisateur/{id}/bloquer")
    public static String bloquerUtilisateur(@PathVariable Long id) {
        UtilisateurService.bloquerUtilisateur(id);
        return "redirect:/admin";
    }
    // pour debloquer un utilisateur
    @PostMapping("/utilisateur/{id}/debloquer")
    public static String debloquerUtilisateur(@PathVariable Long id) {
        UtilisateurService.debloquerUtilisateur(id);
        return "redirect:/admin";
    }

    // Validation des demandes
    @PostMapping("/demande/{id}/valider")
    public String validerDemande(@PathVariable Long id) {
        UtilisateurService demandeService = null;
        demandeService.validerDemande(id);
        return "redirect:/admin";
    }

    //rejet des demandes

    @PostMapping("/demande/{id}/rejeter")
    public String rejeterDemande(@PathVariable Long id) {
        UtilisateurService demandeService = null;
        String n = demandeService.rejeterDemande(id);
        return "redirect:/admin";
    } */
}

