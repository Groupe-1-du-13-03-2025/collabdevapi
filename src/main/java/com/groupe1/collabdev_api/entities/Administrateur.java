package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.dto.response_dto.ResponseAdministrateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "administrateurs")
@AllArgsConstructor
public class Administrateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "administrateur")
    private List<GestionAdminUtilisateur> gestionsAdminUtilisateur = new ArrayList<>();

    @OneToMany(mappedBy = "administrateur")
    private List<GestionAdminProjet> gestionsAdminProjet = new ArrayList<>();

    public ResponseAdministrateur toResponse() {
        return
                new ResponseAdministrateur(
                        this.id,
                        this.email,
                        this.motDePasse,
                        this.role
                );
    }
}
