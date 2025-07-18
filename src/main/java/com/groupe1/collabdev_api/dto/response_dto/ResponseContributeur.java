package com.groupe1.collabdev_api.dto.response_dto;

import com.groupe1.collabdev_api.entities.enums.Genre;
import com.groupe1.collabdev_api.entities.enums.Niveau;
import com.groupe1.collabdev_api.entities.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseContributeur extends ResponseUtilisateur {

    private Niveau niveau;
    private String specialite;
    private Type type;
    private double pieces;
    private String uriCv;
    private int idContributeur;

    public ResponseContributeur(int id, String prenom, String nom, String email, String motDePasse, Genre genre, Niveau niveau, String specialite, Type type, double pieces, String uriCv, int idContributeur) {
        super(id, prenom, nom, email, motDePasse, genre);
        this.niveau = niveau;
        this.specialite = specialite;
        this.type = type;
        this.pieces = pieces;
        this.uriCv = uriCv;
        this.idContributeur = idContributeur;
    }
}
