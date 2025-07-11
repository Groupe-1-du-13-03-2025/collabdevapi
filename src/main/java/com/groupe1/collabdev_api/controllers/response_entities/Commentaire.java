package com.groupe1.collabdev_api.controllers.response_entities;

import com.groupe1.collabdev_api.entities.IdeeProjet;
import com.groupe1.collabdev_api.entities.Projet;
import com.groupe1.collabdev_api.entities.Utilisateur;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Commentaire {
    private String  contenu;
    private Projet projet;
    private Utilisateur user;
    private IdeeProjet ideeProjet;
}
