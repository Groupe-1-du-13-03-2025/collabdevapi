package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.entities.Badge;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/admin/")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    @Autowired
    private BadgeService badgeService;
    @Autowired
    private IdeeProjetService ideeProjetService;
    @Autowired
    private ProjetService projetService;

    @Autowired
    private UtilisateurService utilisateurService;

    //Methode pour la creation de superAdmin :
    @GetMapping
    public void index() {
        administrateurService.superAdmin();
    }

    //Methode pour la creation des autres Administrateurs :
    @PostMapping("new")
    public Administrateur add(@RequestBody Administrateur admin) {
        admin.setMotDePasse(BCrypt.hashpw(admin.getMotDePasse(), BCrypt.gensalt()));
        admin.setRole(Role.ADMIN);
        return administrateurService.ajouter(admin);
    }

    //Methode pour la modification d'un administrateur par id :
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Administrateur admin) {
        return administrateurService.updateAdmin(id, admin);
    }

    //Methode pour la liste des Administrateurs :
    @GetMapping("list")
    public List<Administrateur> list() {
        if (administrateurService.chercherTous().isEmpty()) {
            return null;
        }
        return administrateurService.chercherTous();
    }

    //Methode pour afficher un seul administrateur :
    @GetMapping("{id}")
    public Administrateur get(@PathVariable Integer id) {
        return administrateurService.chercherParId(id);
    }

    //Methode pour la suppression d'un administrateur :
    public List<Administrateur> deleteAdmin(@PathVariable Integer id) {
        if (administrateurService.supprimerParId(id)) {
            return administrateurService.chercherTous();
        }
        return null;
    }

    /**
     * Fin pour la gestion des Administrateurs
     **/

    /**
     * Debut pour la gestion des badges
     */
    //Pour l'ajout des badges dans le systemes :
    @PostMapping(value = "badge/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addBadge(
            @RequestParam("titre") String titre,
            @RequestParam("fichier") MultipartFile chemin
    ) throws IOException {
        return badgeService.ajouteBadge(titre, chemin);
    }

    //Pour la modification d'un badge :
    @PutMapping(value = "badge/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateBadge(
            @PathVariable("id") int id,
            @RequestParam(value = "titre", required = false) String titre,
            @RequestParam(value = "fichier", required = false) MultipartFile chemin
    ) throws IOException {
        return badgeService.modifieBadge(id, titre, chemin);
    }

    //Pour la suppression de badge :
    @DeleteMapping("badge/{id}")
    public ResponseEntity<?> deleteBadge(@PathVariable int id) {
        return badgeService.deleteBadge(id);
    }

    //Pour afficher tous les badges :
    @GetMapping("badge")
    public List<Badge> getBadge() {
        return badgeService.afficheBadge();
    }

    // pour bloquer les utilisateurs
    @PostMapping("{id}/bloquer")
    public String bloquerUtilisateur(@PathVariable int id) {
        return utilisateurService.bloqueUser(id);
    }

    // pour debloquer un utilisateur
    @PostMapping("{id}/debloquer")
    public String debloquerUtilisateur(@PathVariable int id) {
        return utilisateurService.debloqueUser(id);
    }

    // Validation des demandes
    @PostMapping("{id}/valider")
    public String validerDemande(@PathVariable int id) {
        return utilisateurService.validerDemande(id);
    }

    //rejet des demandes

    @PostMapping("{id}/rejeter")
    public String rejeterDemande(@PathVariable int id) {
        return utilisateurService.rejeterDemande(id);
    }
}
