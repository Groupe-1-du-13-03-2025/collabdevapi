package com.groupe1.collabdev_api.dto.request_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;

public class RequestPorteurProjet extends RequestUtilisateur {
    public RequestPorteurProjet(String prenom, String nom, String email, String motDePasse, Genre genre) {
        super(prenom, nom, email, motDePasse, genre);
    }
}
