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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.gescom.gescom_demo_spring.dao.CategorieDao;
import fr.gescom.gescom_demo_spring.entities.Categorie;

@RestController
@RequestMapping("/api/v1/categories") //localhost:8080/api/v1/categories
public class CategorieControllerRest {
	
	private CategorieDao categorieDao;
	
	public CategorieControllerRest(CategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}
	@GetMapping("/all")
	public List<Categorie> getAllCategories(){
		return this.categorieDao.getAllCategorie();
	}
	@PostMapping("/create_categorie")
	public void addCategorie(@RequestBody Categorie categorie) {
		this.categorieDao.insertCategorie(categorie);
	}
	
	@DeleteMapping("/delete_categorie")
	public void deleteCategorie(@RequestBody Categorie categorie) {
		this.categorieDao.deleteCategorie(categorie);
	}
	
	@PutMapping("/update_categorie")
	public void updateCategorie(@RequestBody Categorie categorie) {
		this.categorieDao.updateCategorie(categorie);
	}
	@GetMapping("/category/{id}")
	public Categorie getOneCategorie(@PathVariable("id") int id) {
		return this.categorieDao.getOnecategorieById(id);
	}
	
	  @GetMapping("/category2") 
	  public Categorie getOneCategorie2(@RequestParam int id) { 
		  return   this.categorieDao.getOnecategorieById(id);
	  }
	  
	  @GetMapping("/category3") 
	  public Categorie getOneCategorie3(int id) { 
		  return   this.categorieDao.getOnecategorieById(id);
	  }
}
