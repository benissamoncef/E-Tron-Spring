package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.dao.TypeAbonnementDao;
import com.example.demo.model.TypeAbonnement;

@Service
public class TypeAbonnementService {

    private final TypeAbonnementDao typeAbonnementDao;

    public TypeAbonnementService(TypeAbonnementDao typeAbonnementDao) {
        this.typeAbonnementDao = typeAbonnementDao;
    }

    public TypeAbonnement getTypeAbonnementById(int idType) {
        try {
            TypeAbonnement typeAbonnement = this.typeAbonnementDao.findById(idType);
            if (typeAbonnement != null) {
                typeAbonnement.setContrats(null);
            }
            return typeAbonnement;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche du type d'abonnement par ID.", e);
        }
    }

    public List<TypeAbonnement> getAllTypeAbonnements() {
        try {
            List<TypeAbonnement> types = this.typeAbonnementDao.findAll();
            for (TypeAbonnement type : types) {
                if (type != null) {
                    type.setContrats(null);
                }
            }
            return types;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de tous les types d'abonnements.", e);
        }
    }

    public String addTypeAbonnement(TypeAbonnement typeAbonnement) {
        try {
            typeAbonnementDao.save(typeAbonnement);
            return "Succès : Type d'abonnement ajouté.";
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }

    public String deleteTypeAbonnement(int idType) {
        try {
            if (typeAbonnementDao.findById(idType) != null) {
                typeAbonnementDao.deleteById(idType);
                return "Succès : Type d'abonnement supprimé.";
            }
            return "Erreur : Type d'abonnement n'existe pas.";
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }
}
