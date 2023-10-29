package com.example.demo.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.FactureService; // Importez votre service
import com.example.demo.model.Facture;
import java.util.List;

@RestController
@RequestMapping("/factures")
@CrossOrigin(origins="*")
public class FactureApi {

    private final FactureService factureService; // Utilisez le service au lieu du DAO

    public FactureApi(FactureService factureService) {
        this.factureService = factureService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{idFacture}")
    public Facture getFactureById(@PathVariable int idFacture) {
        return factureService.findFactureById(idFacture); // Utilisez le service pour obtenir l'entité par ID
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<Facture> getAllFactures() {
        return factureService.findAll(); // Utilisez le service pour obtenir toutes les entités
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addFacture(@RequestBody Facture facture) {
        return factureService.addFacture(facture); // Utilisez le service pour ajouter une entité
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{idFacture}")
    public String updateFacture(@PathVariable int idFacture, @RequestBody Facture updatedFacture) {
        return factureService.updateFacture(idFacture, updatedFacture); // Utilisez le service pour mettre à jour l'entité
    }

    
}
