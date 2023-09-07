package fr.gescom.gescom_demo_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.gescom.gescom_demo_spring.dao.CategorieDao;
import fr.gescom.gescom_demo_spring.entities.Categorie;
@CrossOrigin
@Controller
@RequestMapping("/categories")
public class CategorieController {
	@Autowired
	private CategorieDao categorieDao;
	
	@GetMapping( path = "/all")
	//@RequestMapping(path = "/all",method =RequestMethod.GET)
	public String getAll(Model model) {
		System.out.println(this.categorieDao.getAllCategorie());
		model.addAttribute("listeCat",this.categorieDao.getAllCategorie());
		return "categoriesVue";
	}
	@GetMapping("/delete")
	public String deleteCategorie(int id) {	
		if(id != 0) {
			Categorie cat = this.categorieDao
					.getOnecategorieById(id);
				this.categorieDao.deleteCategorie(cat);
		}	
		return "redirect:/categories/all";
	}
	/**
	 * Ajout d'une catégorie:
	 * 	1-	Get ==> affiche le formulaire
	 * 	2-	POST ==> Ajout d'un objet de type catégorie
	 */
	@GetMapping("/addForm")
	public String afficheForm(Model model) {
		model.addAttribute("cat",new Categorie());
		return "categorie-form";
	}

	@PostMapping(path = {"/insert"})
	public String ajoutCategorie(Categorie cat) {	
		//System.out.println("objet dans insert : " + cat);
		 if(cat != null && !cat.getNomCat().equals("")) {
		  this.categorieDao.insertCategorie(cat); 
		  } 	
		return "redirect:/categories/all";
	}
	/**
	 * 	Mise-à-jour d'une catégorie
	 * 	1-	GET ==> formulaire 
	 * 	2-  post ==> Update 
	 */
	@GetMapping("/update")
	public String afficheFormPourUpdate(int id,Model model) {
		if(id != 0) {
			Categorie cat = this.categorieDao
					.getOnecategorieById(id);
			model.addAttribute("cat", cat);
		}	
		return "categorie-form-update";
	}
	@PostMapping("/update_cat")
	public String miseAjourUneCategorie(Categorie cat) {
		
		if(cat != null && !cat.getNomCat().equals("")) {
			
			this.categorieDao.updateCategorie(cat);
		}		
		return "redirect:/categories/all";
	}
}
