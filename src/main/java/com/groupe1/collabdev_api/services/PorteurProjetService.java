package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.PorteurProjet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.repositories.PorteurProjetRepository;
import com.groupe1.collabdev_api.repositories.UtilisateurRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Getter
@Setter
@Service
@NoArgsConstructor
public class PorteurProjetService {

    @Autowired
    private PorteurProjetRepository porteurProjetRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public PorteurProjet chercherParId(int id){
        return porteurProjetRepository.findById(id).orElse(null);
    }

    public List<PorteurProjet> chercherTous(){
        return porteurProjetRepository.findAll();
    }

    public PorteurProjet ajouter(Utilisateur utilisateur){
        utilisateur.setRole(Role.PORTEUR_PROJET);
        Utilisateur user = utilisateurRepository.save(utilisateur);

        PorteurProjet porteurProjet = new PorteurProjet();
        porteurProjet.setUtilisateur(user);
        return porteurProjetRepository.save(porteurProjet);
    }

    public PorteurProjet modifier(int id, Utilisateur utilisateur ) {
        utilisateur.setRole(Role.PORTEUR_PROJET);
        PorteurProjet porteurProjet = porteurProjetRepository.findById(id).orElse(null);
        assert porteurProjet != null;
        int idU = porteurProjet.getUtilisateur().getId();
        utilisateurRepository.findById(idU).ifPresent(user -> {
            user.setRole(Role.PORTEUR_PROJET);
            user.setNom(utilisateur.getNom());
            user.setPrenom(utilisateur.getPrenom());
            user.setEmail(utilisateur.getEmail());
            user.setMotDePasse(utilisateur.getMotDePasse());
            utilisateurRepository.save(user);
        });

        return porteurProjet;
    }

    public Boolean supprimerParId(int id){
        porteurProjetRepository.deleteById(id);
        return true;
    }

}

