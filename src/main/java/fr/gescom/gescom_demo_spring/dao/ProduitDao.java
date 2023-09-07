package fr.gescom.gescom_demo_spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gescom.gescom_demo_spring.entities.Produit;
import fr.gescom.gescom_demo_spring.repository.ProduitRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor
public class ProduitDao {
	@Autowired
	private ProduitRepository produitRepository;
	
	public List<Produit> getAllProduits(){
		return this.produitRepository.findAll();
	}
	public Produit getOneProduitById(int id) {
		return this.produitRepository.getById(id);
	}
	public void insertProduit(Produit produit) {
		this.produitRepository.save(produit);
	}
	public void deleteProduit(Produit produit) {
		this.produitRepository.delete(produit);
	}
	public void updateProduit(Produit produit) {
		this.produitRepository.save(produit);
	}

}
