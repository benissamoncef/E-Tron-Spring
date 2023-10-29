package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.FactureDao;
import com.example.demo.model.Contrat;
import com.example.demo.model.Facture;
import com.example.demo.model.Recharge;
import com.example.demo.model.TypeAbonnement;

@Service
public class FactureService {

    @Autowired
    private final FactureDao factureDao;

    @Autowired
    private final RechargeService rechargeService;

    @Autowired
    private final TypeAbonnementService typeAbonnementService;

    @Autowired
    private final ContratService contratService;

    public FactureService(FactureDao factureDao, RechargeService rechargeService,
            TypeAbonnementService typeAbonnementService, ContratService contratService) {
        this.factureDao = factureDao;
        this.rechargeService = rechargeService;
        this.typeAbonnementService = typeAbonnementService;
        this.contratService = contratService;
    }

    public Facture findFactureById(int idFacture) {
        try {
            Facture facture = this.factureDao.findById(idFacture);
            if (facture != null) {
                facture.setContrat(null);
                facture.setRecharges(null);
            }
            return facture;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de facture par ID.", e);
        }
    }

    public List<Facture> findAll() {
        try {
            List<Facture> factures = this.factureDao.findAll();

            for (Facture facture : factures) {
                if (facture != null) {
                    facture.setContrat(null);
                    facture.setRecharges(null);
                }
            }
            return factures;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de toutes les factures.", e);
        }
    }

    public String updateFacture(int idFacture, Facture updatedFacture) {
        try {
            Facture existingFacture = this.factureDao.findById(idFacture);

            if (existingFacture != null) {
                existingFacture.setDateFacturation(updatedFacture.getDateFacturation());
                existingFacture.setDatePaiement(updatedFacture.getDatePaiement());
                existingFacture.setEtatFacture(updatedFacture.isEtatFacture());
                existingFacture.setMontantFacture(updatedFacture.getMontantFacture());
                existingFacture.setRecharges(updatedFacture.getRecharges());
                existingFacture.setContrat(updatedFacture.getContrat());

                if (this.factureDao.save(existingFacture) != null) {
                    return "Succès : Mise à jour de la facture réussie.";
                } else {
                    return "Erreur : Problème lors de la mise à jour de la facture.";
                }
            } else {
                return "Erreur : L'entité Facture n'existe pas.";
            }
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }

    public String addFacture(Facture facture) {
        try {
            // Contrat de la facture | typeAbonnement | Recharges:
            Contrat tempContrat = contratService.findContratById(facture.getContrat().getIdContrat());
            TypeAbonnement tempAbonnement = tempContrat.getTypeAbonnement();

            // Tri de recharges non facturées:
            List<Recharge> tempRecharges = new ArrayList<>();

            for (Recharge recharge : facture.getRecharges()) {
                Recharge tempRecharge = rechargeService.findRechargeById(recharge.getIdRecharge());
                if (!tempRecharge.isFactured()) {
                    tempRecharges.add(tempRecharge);
                }
            }

            // Calcul du montant
            float montant = calculateTotalMontant(tempRecharges, tempAbonnement);

            if (montant == 0)
                return "Erreur : Montant nul.";

            // Enregistrement de la facture
            facture.setMontantFacture(montant);
            facture.setEtatFacture(false);
            if (factureDao.save(facture) == null) {
                return "Erreur : Erreur lors de l'enregistrement de la facture.";
            }

            // Mise à jour de la facture dans les recharges
            for (Recharge recharge : tempRecharges) {
                Recharge tempRecharge = rechargeService.findRechargeById(recharge.getIdRecharge());
                tempRecharge.setFacture(facture);
                tempRecharge.setFactured(true);
                this.rechargeService.updateRecharge(tempRecharge.getIdRecharge(), tempRecharge);
            }
            
            return "Succès : Facture ajoutée.";
        } catch (Exception e) {
            return "Erreur :" + e.getMessage();
        }
    }

    private float calculateTotalMontant(List<Recharge> recharges, TypeAbonnement typeAbonnement) {
        float totalMontant = 0;

        for (Recharge recharge : recharges) {
            totalMontant += calculateMontantForRecharge(recharge, typeAbonnement);
        }

        return totalMontant;
    }

    private float calculateMontantForRecharge(Recharge recharge, TypeAbonnement typeAbonnement) {
        TypeAbonnement tempTypeAbonnement = typeAbonnementService.getTypeAbonnementById(typeAbonnement.getIdType());
        String typeRecharge = recharge.getTypeRecharge();
        float kwh = recharge.getKwh();

        switch (typeRecharge) {
            case "ac":
                return kwh * tempTypeAbonnement.getAc();
            case "dc":
                return kwh * tempTypeAbonnement.getDcHpc();
            case "prhp":
                return kwh * tempTypeAbonnement.getPartenaireHP();
            default:
                return 0;
        }
    }
}
