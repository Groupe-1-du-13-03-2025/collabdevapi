package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.dto.request_dto.RequestAuthentification;
import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.entities.Utilisateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private AdministrateurService administrateurService;

    public Boolean authenticate(RequestAuthentification user) throws UserNotFoundException {
        switch (user.getRole()) {
            case CONTRIBUTEUR, GESTIONNAIRE, PORTEUR_PROJET -> {
                return authenticateUser(user.getEmail(), user.getMotDePasse(), user.getRole());
            }
            case ADMIN, SUPER_ADMIN -> {
                return authenticateAdmin(user.getEmail(), user.getMotDePasse(), user.getRole());
            }
            default -> {
                return false;
            }
        }
    }

    private Boolean authenticateUser(String email, String motDePasse, Role role) throws UserNotFoundException, RuntimeException {
        Utilisateur utilisateur = utilisateurService.chercherParEmail(email);
        if (utilisateur == null) {
            throw new UserNotFoundException(role);
        }
        if(!utilisateur.isEtat()){
            throw new RuntimeException("Votre compte est bloqué, impossible de se connecter!");
        }
        return BCrypt.checkpw(
                motDePasse,
                utilisateur.getMotDePasse()
        );
    }

    private Boolean authenticateAdmin(String email, String motDePasse, Role role) throws UserNotFoundException {
        Administrateur administrateur = administrateurService.chercherParEmail(email);
        if (administrateur == null) {
            throw new UserNotFoundException(role);
        }
        return BCrypt.checkpw(
                motDePasse,
                administrateur.getMotDePasse()
        );
    }
}
