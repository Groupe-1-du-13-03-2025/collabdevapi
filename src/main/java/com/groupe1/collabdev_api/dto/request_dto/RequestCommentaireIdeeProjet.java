package com.groupe1.collabdev_api.dto.request_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCommentaireIdeeProjet {
    private String contenu;
    private LocalDate dateCommentaire;
}
