package fr.gescom.gescom_demo_spring.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.gescom.gescom_demo_spring.dao.ProduitDao;
import fr.gescom.gescom_demo_spring.entities.Categorie;
import fr.gescom.gescom_demo_spring.entities.Produit;

@RestController
@RequestMapping("/api/v1/produits")
public class ProduitControllerRest {
	private ProduitDao produitDao;

	public ProduitControllerRest(ProduitDao produitDao) {
		this.produitDao = produitDao;
	}
	
	@GetMapping("/all")
	public List<Produit> getAllProduits(){
		return this.produitDao.getAllProduits();
	}
	@PostMapping("/create_produit")
	public void addCategorie(@RequestBody Produit produit) {
		this.produitDao.insertProduit(produit);
	}
	
	@DeleteMapping("/delete_produit")
	public void deleteProduit(@RequestBody Produit produit) {
		this.produitDao.deleteProduit(produit);
	}
	
	@PutMapping("/update_produit")
	public void updateProduit(@RequestBody Produit produit) {
		this.produitDao.updateProduit(produit);
	}
	@GetMapping("/product/{id}")
	public Produit getOneProduit(@PathVariable("id") int id) {
		return this.produitDao.getOneProduitById(id);
	}
	
	@GetMapping("/product2") 
	public Produit getOneProduit2(@RequestParam int id) { 
		return this.produitDao.getOneProduitById(id);
	}
	  
	@GetMapping("/product3") 
	public Produit getOneProduit3(int id) { 
		return this.produitDao.getOneProduitById(id);
	}
	

}
