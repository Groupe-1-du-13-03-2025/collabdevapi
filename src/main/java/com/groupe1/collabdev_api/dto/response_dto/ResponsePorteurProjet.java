package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsePorteurProjet extends ResponseUtilisateur {
    private int idPorteurProjet;

    public ResponsePorteurProjet(int id, String prenom, String nom, String email, String motDePasse, Genre genre) {
        super(id, prenom, nom, email, motDePasse, genre);
    }
}
