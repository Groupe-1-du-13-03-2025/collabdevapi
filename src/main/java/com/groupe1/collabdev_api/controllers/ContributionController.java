package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.ContributionDto;
import com.groupe1.collabdev_api.dto.ProjetDto;
import com.groupe1.collabdev_api.services.ContributeurService;
import com.groupe1.collabdev_api.services.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs/contributeurs")
public class ContributionController {

    @Autowired
    private ContributionService contributionService;
    @Autowired
    private ContributeurService contributeurService;

    @GetMapping("/contributions/{id}")
    public ResponseEntity<?> chercherParId(@PathVariable int id) {
        return contributionService.chercherParId(id);
    }

    @GetMapping("/contributions")
    public List<ContributionDto> chercherTous() {
        return contributionService.chercherTous();
    }

    @PatchMapping("/contributions/{id}/valide")
    public ResponseEntity<ContributionDto> validerContribution(
            @PathVariable int id) {
        ContributionDto modifieDto = contributionService.validerContribution(id);
        return ResponseEntity.ok(modifieDto);
    }


    @DeleteMapping("/contributions/{id}")
    public Boolean supprimerById(@PathVariable int id) {
        return contributionService.supprimerParId(id);
    }

    @GetMapping("/{idContributeur}/contributions")
    public List<ContributionDto> chercherParContributeurId(@PathVariable int idContributeur) {
        return contributionService.chercherParContributeurId(idContributeur);
    }

    @GetMapping("/contributions/projets/{idProjet}")
    public List<ContributionDto> chercherParProjetId(@PathVariable int idProjet) {
        return contributionService.chercherParProjetId(idProjet);
    }

    //contributions/contributeur/{idContributeur}/projet/{idProjet}/valides?valide=true or false
    @GetMapping("/{idContributeur}/projets/{idProjet}/valide/{estValide}")
    public List<ContributionDto> chercherContributionValide(
            @PathVariable int idContributeur,
            @PathVariable int idProjet,
            @PathVariable boolean estValide) {
        return contributionService.chercherContributionValide(idContributeur, idProjet, estValide);
    }

    @GetMapping("/{id}/projets")
    public ResponseEntity<List<ProjetDto>> projetList(
            @PathVariable int id
    ) {
        List<ProjetDto> projets = contributeurService.chercherProjetsParContributeur(id);
        return ResponseEntity.ok(projets);
    }

}
