package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.entities.GestionAdminProjet;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.entities.enums.TypeGestionProjet;
import com.groupe1.collabdev_api.repositories.AdministrateurRepository;
import com.groupe1.collabdev_api.repositories.GestionAdminProjetRepository;
import com.groupe1.collabdev_api.repositories.ProjetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GestionAdminProjetService {

    @Autowired
    private GestionAdminProjetRepository gestionAdminProjetRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private AdministrateurRepository administrateurRepository;

    public GestionAdminProjet chercherParId(int id){
        return gestionAdminProjetRepository.findById(id).orElse(null);
    }

    public List<GestionAdminProjet> chercherTous(){
        return gestionAdminProjetRepository.findAll();
    }

    public GestionAdminProjet ajouter(GestionAdminProjet gestionAdminProjet){
        return gestionAdminProjetRepository.save(gestionAdminProjet);
    }

    public GestionAdminProjet modifier(GestionAdminProjet gestionAdminProjet){
        return gestionAdminProjetRepository.save(gestionAdminProjet);
    }

    public Boolean supprimerParId(int id, int idAdmin){
        Administrateur administrateur = administrateurRepository.findById(idAdmin).orElseThrow(
                () -> new EntityNotFoundException("Administrateur introuvable!")
        );
        Projet projet = projetRepository.findById(id)
                        .orElseThrow(
                                ()-> new EntityNotFoundException("Projet introuvable!")
                        );
        projetRepository.deleteById(id);
        gestionAdminProjetRepository.save(
                new GestionAdminProjet(
                        0,
                        TypeGestionProjet.SUPPRIMER,
                        LocalDate.now(),
                        administrateur,
                        projet
                )
        );
        return true;
    }

    //Pour recuperer les projet qui sont dans le systeme en fin de les manupiler :
    public List<Projet> afficherListeProjet() {
        return  projetRepository.findAll();
    }

    //Pour activer un projet :
    public Projet activerProjet(int id,int idAdmin) {
        Projet projet = projetRepository.findById(id).orElse(null);
        Administrateur admin = administrateurRepository.findById(idAdmin).orElse(null);
        if(projet == null || admin == null){
            return null;
        }
        GestionAdminProjet gProjet = new GestionAdminProjet();
        gProjet.setType(TypeGestionProjet.ACTIVER);
        projetRepository.findById(id).ifPresent(projet1 -> {

            projet1.setEtat(true);
            projetRepository.save(projet1);

            gProjet.setProjet(projet1);
            gProjet.setAdministrateur(admin);
            gProjet.setDateGestion(LocalDate.now());
            gestionAdminProjetRepository.save(gProjet);

        });
        return projetRepository.findById(id).orElse(null);
    }

    //Pour desactiver un projet :
    public Projet desactiverProjet(int id,int idAdmin) {
        Projet projet = projetRepository.findById(id).orElse(null);
        Administrateur admin = administrateurRepository.findById(idAdmin).orElse(null);
        if(projet == null || admin == null){
            return null;
        }
        GestionAdminProjet gProjet = new GestionAdminProjet();
        gProjet.setType(TypeGestionProjet.DESACTIVER);
        projetRepository.findById(id).ifPresent(projet1 -> {

            projet1.setEtat(false);
            projetRepository.save(projet1);

            gProjet.setProjet(projet1);
            gProjet.setAdministrateur(admin);
            gProjet.setDateGestion(LocalDate.now());
            gestionAdminProjetRepository.save(gProjet);

        });
        return projetRepository.findById(id).orElse(null);
    }


}
