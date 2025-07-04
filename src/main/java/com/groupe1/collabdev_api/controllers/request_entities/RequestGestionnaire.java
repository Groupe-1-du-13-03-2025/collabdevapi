package com.groupe1.collabdev_api.controllers.request_entities;

import com.groupe1.collabdev_api.entities.enums.Genre;
import com.groupe1.collabdev_api.entities.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestGestionnaire extends RequestUtilisateur {
    private String uriCv;

    public RequestGestionnaire(String prenom, String nom, String email, String motDePasse, Genre genre, String uriCv) {
        super(prenom, nom, email, motDePasse, genre);
        this.uriCv = uriCv;
    }
}
