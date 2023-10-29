package com.example.demo.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.RechargeService; // Importez votre service
import com.example.demo.model.Contrat;
import com.example.demo.model.Recharge;
import java.util.List;

@RestController
@RequestMapping("/recharges")
@CrossOrigin(origins="*")

public class RechargeApi {

    private final RechargeService rechargeService; // Utilisez le service au lieu du DAO

    public RechargeApi(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{idRecharge}")
    public Recharge getRechargeById(@PathVariable int idRecharge) {
    	return rechargeService.findRechargeById(idRecharge);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/contrat")
    public List<Recharge> getByContrat(@RequestBody Contrat contrat) {
    	return this.rechargeService.findByContrat(contrat); 
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Recharge> getAllRecharges() {	
    	return rechargeService.getAllRecharges();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    public String addRecharge(@RequestBody Recharge recharge) {
        return rechargeService.addRecharge(recharge); // Utilisez le service pour ajouter une entité
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{idRecharge}")
    public String updateRecharge(@PathVariable int idRecharge, @RequestBody Recharge updatedRecharge) {
        return rechargeService.updateRecharge(idRecharge, updatedRecharge); // Utilisez le service pour mettre à jour l'entité
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{idRecharge}")
    public String deleteRecharge(@PathVariable int idRecharge) {
        return rechargeService.deleteRecharge(idRecharge); // Utilisez le service pour supprimer l'entité
    }
}
