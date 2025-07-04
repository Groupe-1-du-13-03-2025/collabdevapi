package com.groupe1.collabdev_api.services;

import com.groupe1.collabdev_api.entities.Administrateur;
import com.groupe1.collabdev_api.entities.enums.Role;
import com.groupe1.collabdev_api.repositories.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    //Pour la creation de Super-Admin Par le systeme :

    public  void superAdmin(){
        List<Administrateur> adminList = administrateurRepository.findAll();
        if(adminList.isEmpty()){
            Administrateur admin = new Administrateur();
            String email = "super.admin@collab.dev";
            String password = BCrypt.hashpw("admin1234", BCrypt.gensalt());
            admin.setEmail(email);
            admin.setMotDePasse(password);
            admin.setRole(Role.SUPER_ADMIN);
            administrateurRepository.save(admin);
        }
    }

    //Pour la mise a jour d'un administrateur :

    public ResponseEntity<?>  updateAdmin(int id, Administrateur admin){

        if (id == 0)
            return ResponseEntity.badRequest().body("Veuillez spécifier l'id");

        Administrateur existant = administrateurRepository.findById(id).orElse(null);
        if (existant == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrateur introuvable");

        existant.setEmail(admin.getEmail() != null ? admin.getEmail() : existant.getEmail());
        existant.setRole(admin.getRole() != null ? admin.getRole() : existant.getRole());
        if (admin.getMotDePasse() != null && !admin.getMotDePasse().isEmpty())
            existant.setMotDePasse(BCrypt.hashpw(admin.getMotDePasse(), BCrypt.gensalt()));

        Administrateur modifie = administrateurRepository.save(existant);
        return ResponseEntity.ok(modifie);
    }

    public Administrateur chercherParId(int id){
        return administrateurRepository.findById(id).orElse(null);
    }

    public List<Administrateur> chercherTous(){
        if (administrateurRepository.findAll().isEmpty()){
            return null;
        }
        return administrateurRepository.findAll();
    }

    public Administrateur ajouter(Administrateur administrateur){
        return administrateurRepository.save(administrateur);
    }

    public Administrateur modifier(Administrateur administrateur){
        return administrateurRepository.save(administrateur);
    }

    public Boolean supprimerParId(int id){
        administrateurRepository.deleteById(id);
        return true;
    }
}
