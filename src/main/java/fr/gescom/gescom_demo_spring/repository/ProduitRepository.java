package fr.gescom.gescom_demo_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.gescom.gescom_demo_spring.entities.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {

}
