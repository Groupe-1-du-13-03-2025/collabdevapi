package com.groupe1.collabdev_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groupe1.collabdev_api.entities.enums.Genre;
import com.groupe1.collabdev_api.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private boolean etat = true;


    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<CommentaireProjet> commentairesProjet = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<CommentaireIdeeProjet> commentairesIdeeProjet = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    private List<GestionAdminUtilisateur> gestionsAdminUtilisateur = new ArrayList<>();
}
