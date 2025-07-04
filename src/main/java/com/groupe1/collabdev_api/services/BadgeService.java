package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Badge;
import com.groupe1.collabdev_api.repositories.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.groupe1.collabdev_api.services.UploadFichier.upload;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    public Badge chercherParId(int id){
        return badgeRepository.findById(id).orElse(null);
    }

    public List<Badge> chercherTous(){
        return badgeRepository.findAll();
    }



    //Pour l'ajout des Badges coté admin :
    public ResponseEntity<?> ajouteBadge(String titre, MultipartFile fichier) throws IOException {
        String chemin = upload(fichier,"badges");
        if (!chemin.isEmpty()) {
            Badge badge = new Badge();
            badge.setTitre(titre);
            badge.setUriImage(chemin);
            Badge badge1 = badgeRepository.save(badge);
            return ResponseEntity.ok(badge1);
        }
        return ResponseEntity.badRequest().body("Veillez Choisir une image du format 'JPN' ou 'jpeg' ou 'jpg' !!!! ");
    }

    public ResponseEntity<?> modifieBadge(int id, String titre, MultipartFile fichier) throws IOException {
        // On vérifie que le badge existe vraiment sinon
        Badge badge = badgeRepository.findById(id).orElse(null);
        if (badge == null)
            return ResponseEntity.notFound().build();

        // On vérifie le titre
        if (titre != null && !titre.isEmpty()) {
            badge.setTitre(titre);
        }

        // On vérifie l'image : pas null et pas vide
        if (fichier != null && !fichier.isEmpty()) {
            //On efface l'ancien fichier sur le disque:
            String ancienFichier =System.getProperty("user.dir")+ File.separator + badge.getUriImage();
            File file = new File(ancienFichier);
            file.delete();

            //On cree le nouveau uri pour l'image :
            String uri = upload(fichier, "badges");
            if (!uri.isEmpty()) badge.setUriImage(uri);
        }

        Badge badge1 = badgeRepository.save(badge);
        return ResponseEntity.ok(badge1);
    }



    //Pour La suppression d'un badge coté administrateur :
    public ResponseEntity<?> deleteBadge(int id){
        Badge badge = badgeRepository.findById(id).orElse(null);
        if (badge == null) return ResponseEntity.notFound().build();

        String chemin =System.getProperty("user.dir")+File.separator+ badge.getUriImage();
        File file = new File(chemin);
        file.delete();
        badgeRepository.delete(badge);
        return ResponseEntity.ok(badgeRepository.findAll());
    }

    //Pour affichage des badges :

    public List<Badge> afficheBadge(){
        return badgeRepository.findAll();
    }

    //Pour affichage par id de badge :

    public Badge badgeParId(int id){
        return badgeRepository.findById(id).orElse(null);
    }


    public Badge modifier(Badge badge){
        return badgeRepository.save(badge);
    }

    public Boolean supprimerParId(int id){
        badgeRepository.deleteById(id);
        return true;
    }
}

