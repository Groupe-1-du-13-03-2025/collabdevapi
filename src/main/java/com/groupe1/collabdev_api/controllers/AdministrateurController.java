package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.request_dto.RequestAdministrateur;
import com.groupe1.collabdev_api.dto.response_dto.ResponseAdministrateur;
import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.services.AdministrateurService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "administrateur", description = """
        Ce contrôleur permet d'ajouter des administrateurs, les lister, les modifier et les supprimer""")
@RestController
@RequestMapping("/administrateurs")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    //Methode pour la creation des autres Administrateurs :
    @PostMapping
    public ResponseAdministrateur add(@RequestBody RequestAdministrateur admin) {
        admin.setMotDePasse(BCrypt.hashpw(admin.getMotDePasse(), BCrypt.gensalt()));
        return administrateurService.ajouter(new Administrateur(0, admin.getEmail(), admin.getMotDePasse(), Role.ADMIN, new ArrayList<>(), new ArrayList<>())).toResponse();
    }

    //Methode pour la modification d'un administrateur par id :
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody RequestAdministrateur admin) {
        return administrateurService.updateAdmin(id, new Administrateur(id, admin.getEmail(), admin.getMotDePasse(), Role.ADMIN, new ArrayList<>(), new ArrayList<>()));
    }

    //Methode pour la liste des Administrateurs :
    @GetMapping
    public ResponseEntity<?> list() {
        List<Administrateur> administrateurs = administrateurService.chercherTous();
        if (administrateurs.isEmpty()) {
            return new ResponseEntity<>("Aucun administrateur dans le système!", HttpStatus.OK);
        }
        List<ResponseAdministrateur> administrateurList = new ArrayList<>();
        for (Administrateur administrateur : administrateurs) {
            administrateurList.add(administrateur.toResponse());
        }
        return new ResponseEntity<>(administrateurList, HttpStatus.OK);
    }

    //Methode pour afficher un seul administrateur :
    @GetMapping("/{id}")
    public ResponseAdministrateur get(@PathVariable Integer id) {
        return administrateurService.chercherParId(id).toResponse();
    }

    //Methode pour la suppression d'un administrateur :
    @DeleteMapping("/{id}")
    public Boolean deleteAdmin(@PathVariable Integer id) {
        return administrateurService.supprimerParId(id);
    }

}
