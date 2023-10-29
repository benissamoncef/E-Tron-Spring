package com.example.demo.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.TypeAbonnementService; // Importez votre service
import com.example.demo.model.TypeAbonnement;

import java.util.List;

@RestController
@RequestMapping("/typeabonnements")
@CrossOrigin(origins="*")
public class TypeAbonnementApi {

    private final TypeAbonnementService typeAbonnementService; // Utilisez le service au lieu du DAO

    public TypeAbonnementApi(TypeAbonnementService typeAbonnementService) {
        this.typeAbonnementService = typeAbonnementService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{idType}")
    public TypeAbonnement getTypeAbonnementById(@PathVariable int idType) {
    	return typeAbonnementService.getTypeAbonnementById(idType);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<TypeAbonnement> getAllTypeAbonnements() {
    	return typeAbonnementService.getAllTypeAbonnements();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addTypeAbonnement(@RequestBody TypeAbonnement typeAbonnement) {
        return typeAbonnementService.addTypeAbonnement(typeAbonnement); // Utilisez le service pour ajouter une entité
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{idType}")
    public String deleteTypeAbonnement(@PathVariable int idType) {
        return typeAbonnementService.deleteTypeAbonnement(idType); // Utilisez le service pour supprimer l'entité
    }
    
    
}
