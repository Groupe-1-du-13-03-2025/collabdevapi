package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.CommentaireProjet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireProjetRepository extends JpaRepository<CommentaireProjet, Integer> {
    List<CommentaireProjet> findByProjetId(int idProjet);
}
