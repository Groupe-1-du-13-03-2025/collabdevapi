package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.entities.Badge;
import com.groupe1.collabdev_api.services.BadgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/uploads/badges")
@Tag(name = "Badge Api",
        description = "la gestion CRUD pour les badges")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @Operation(summary = "pour afficher toutes les badges")
    @GetMapping
    public List<Badge> getBadge() {
        return badgeService.afficheBadge();
    }

    @Operation(summary = "pour ajouter les badges dans le système")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addBadge(
            @RequestParam("titre") String titre,
            @RequestParam("fichier") MultipartFile chemin
    ) {
        try {
            return new ResponseEntity<>(
                    badgeService.ajouteBadge(titre, chemin).toResponse(),
                    HttpStatus.CREATED
            );
        } catch (IOException exception) {
            return new
                    ResponseEntity<>(
                    exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (RuntimeException e) {
            return new
                    ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Operation(summary = "pour la modification d'un badge")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateBadge(
            @PathVariable("id") int id,
            @RequestParam(value = "titre", required = false) String titre,
            @RequestParam(value = "fichier", required = false) MultipartFile chemin
    ) {
        try {
            return new ResponseEntity<>(
                    badgeService.modifieBadge(id, titre, chemin).toResponse(),
                    HttpStatus.OK
            );
        } catch (IOException exception) {
            return new
                    ResponseEntity<>(
                    exception.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (RuntimeException e) {
            return new
                    ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Operation(summary = "pour la suppression d'un badge")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBadge(@PathVariable int id) {
        return badgeService.deleteBadge(id);
    }

}
