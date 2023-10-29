package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.dao.ContratDao;
import com.example.demo.model.Contrat;

@Service
public class ContratService {
    private final ContratDao contratDao;

    public ContratService(ContratDao contratDao) {
        this.contratDao = contratDao;
    } 

    public String createContrat(Contrat contrat) {
        try {
            if (contrat.getUser().getContrat() != null) {
                return "Erreur : Contrat déjà existant pour l'utilisateur.";
            }

            if (contrat.getDureeContrat() < 12) {
                return "Erreur : Durée du contrat inférieure à 12 mois.";
            }

            Contrat savedContrat = contratDao.save(contrat);
            if (savedContrat != null) {
                return "Succès : Enregistrement du contrat réussi.";
            } else {
                return "Erreur : Problème lors de l'enregistrement du contrat.";
            }
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }

    public Contrat findContratById(int idContrat) {
        try {
            Contrat contrat = this.contratDao.findById(idContrat);
            if (contrat != null) {
                contrat.setUser(null);
                contrat.setFactures(null);
            }
            return contrat;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche du contrat par ID.", e);
        }
    }

    public List<Contrat> findAllContrats() {
        try {
            List<Contrat> contrats = this.contratDao.findAll();

            for (Contrat contrat : contrats) {
                if (contrat != null) {
                    contrat.setTypeAbonnement(null);
                    contrat.setUser(null);
                    contrat.setFactures(null);
                }
            }
            return contrats;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de tous les contrats.", e);
        }
    }

    public String updateContrat(int idContrat, Contrat updatedContrat) {
        try {
            Contrat existingContrat = contratDao.findById(idContrat);

            if (existingContrat != null) {
                existingContrat.setDateSignature(updatedContrat.getDateSignature());
                existingContrat.setDureeContrat(updatedContrat.getDureeContrat());
                existingContrat.setFactures(updatedContrat.getFactures());
                existingContrat.setTypeAbonnement(updatedContrat.getTypeAbonnement());

                if (contratDao.save(existingContrat) != null) {
                    return "Succès : Mise à jour du contrat réussie.";
                } else {
                    return "Erreur : Problème lors de la mise à jour du contrat.";
                }
            } else {
                return "Erreur : L'entité Contrat n'existe pas.";
            }
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }

    public String deleteContrat(int idContrat) {
        try {
            if (this.contratDao.findById(idContrat) != null) {
                contratDao.deleteById(idContrat);
                return "Succès : Suppression du contrat réussie.";
            }
            return "Erreur : Contrat n'existe pas.";
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }
}
