package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.request_dto.RequestAuthentification;
import com.groupe1.collabdev_api.exceptions.UserNotFoundException;
import com.groupe1.collabdev_api.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentification")
@Tag(name = "utilisateur",
        description = """
                Ce contrôleur permet aux administrateurs et utilisateurs de s'authentifier""")
public class AuthentificationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> seConnecter(
            @RequestBody RequestAuthentification user
    ) {
        try {
            boolean isAuthenticatedUser = authenticationService.authenticate(user);
            if (isAuthenticatedUser) {
                return
                        new ResponseEntity<>(
                                "Authentification réussie!",
                                HttpStatus.OK
                        );
            } else {
                return
                        new ResponseEntity<>(
                                "Authentification échouée!",
                                HttpStatus.OK
                        );
            }
        } catch (UserNotFoundException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.NOT_FOUND
                    );
        } catch (RuntimeException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.FORBIDDEN
                    );
        } catch (Exception e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR
                    );
        }
    }
}
