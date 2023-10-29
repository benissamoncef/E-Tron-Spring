package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.dao.RechargeDao;
import com.example.demo.model.Contrat;
import com.example.demo.model.Recharge;

@Service
public class RechargeService {

    private final RechargeDao rechargeDao;

    public RechargeService(RechargeDao rechargeDao) {
        this.rechargeDao = rechargeDao;
    }

    public List<Recharge> findByContrat(Contrat contrat) {
        try {
            List<Recharge> recharges = this.rechargeDao.findByContrat(contrat);
            for (Recharge recharge : recharges) {
                if (recharge != null) {
                    recharge.setFacture(null);
                    recharge.setContrat(null);
                }
            }
            return recharges;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de recharges par contrat.", e);
        }
    }

    public Recharge findRechargeById(int idRecharge) {
        try {
            Recharge recharge = this.rechargeDao.findById(idRecharge);

            if (recharge != null) {
                recharge.setFacture(null);
                recharge.setContrat(null);
            }
            return recharge;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de recharge par ID.", e);
        }
    }

    public List<Recharge> getAllRecharges() {
        try {
            List<Recharge> recharges = this.rechargeDao.findAll();
            for (Recharge recharge : recharges) {
                if (recharge != null) {
                    recharge.setFacture(null);
                    recharge.setContrat(null);
                }
            }
            return recharges;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de toutes les recharges.", e);
        }
    }

    public String addRecharge(Recharge recharge) {
        try {
            rechargeDao.save(recharge);
            return "Succès : Recharge ajoutée.";
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }

    public String updateRecharge(int idRecharge, Recharge updatedRecharge) {
        try {
            Recharge existingRecharge = this.rechargeDao.findById(idRecharge);
            if (existingRecharge != null) {
                existingRecharge.setKwh(updatedRecharge.getKwh());
                existingRecharge.setDateDebutRecharge(updatedRecharge.getDateDebutRecharge());
                existingRecharge.setDateFinRecharge(updatedRecharge.getDateFinRecharge());
                existingRecharge.setFacture(updatedRecharge.getFacture());
                existingRecharge.setContrat(updatedRecharge.getContrat());

                if (rechargeDao.save(existingRecharge) != null) {
                    return "Succès : Mise à jour de la recharge réussie.";
                } else {
                    return "Erreur : Problème lors de la mise à jour de la recharge.";
                }
            } else {
                return "Erreur : L'entité Recharge n'existe pas.";
            }
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }

    public String deleteRecharge(int idRecharge) {
        try {
            if (rechargeDao.findById(idRecharge) != null) {
                return "Succès : Recharge supprimée.";
            }
            return "Erreur : Recharge n'existe pas.";
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }
}
