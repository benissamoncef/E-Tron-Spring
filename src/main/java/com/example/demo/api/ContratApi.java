package com.example.demo.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ContratService;
import com.example.demo.model.Contrat;
import java.util.List;

@RestController
@RequestMapping("/contrats")
@CrossOrigin(origins="*")
public class ContratApi {

    private final ContratService contratService;

    public ContratApi(ContratService contratService) {
        this.contratService = contratService;
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{idContrat}")
    public Contrat getContratById(@PathVariable int idContrat) {
    	return contratService.findContratById(idContrat);
    }    
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Contrat> getAllContrats() {
    	return contratService.findAllContrats();
    }
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    public String addContrat(@RequestBody Contrat contrat) {
        return contratService.createContrat(contrat);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{idContrat}")
    public String updateContrat(@PathVariable int idContrat, @RequestBody Contrat updatedContrat) {
        return contratService.updateContrat(idContrat, updatedContrat);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{idContrat}")
    public String deleteRecharge(@PathVariable int idContrat) {
        return contratService.deleteContrat(idContrat);
    }

}
