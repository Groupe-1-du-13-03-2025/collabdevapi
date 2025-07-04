package com.groupe1.collabdev_api.entities;

import com.groupe1.collabdev_api.entities.enums.Role;
import jakarta.persistence.*;
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
public class Administrateur {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @Column(nullable = false)
    private String email;

    @Setter
    @Getter
    @Column(nullable = false)
    private String motDePasse;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "administrateur")
    private List<GestionAdminUtilisateur> gestionsAdminUtilisateur = new ArrayList<>();

    @OneToMany(mappedBy = "administrateur")
    private List<GestionAdminProjet> gestionsAdminProjet = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
