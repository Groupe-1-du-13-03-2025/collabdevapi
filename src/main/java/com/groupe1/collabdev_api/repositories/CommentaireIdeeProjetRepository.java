package com.groupe1.collabdev_api.repositories;

import com.groupe1.collabdev_api.entities.CommentaireIdeeProjet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireIdeeProjetRepository extends JpaRepository<CommentaireIdeeProjet, Integer> {
    List<CommentaireIdeeProjet> findByIdeeProjetId(int idProjet);
}
