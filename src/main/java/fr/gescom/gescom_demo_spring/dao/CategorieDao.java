package fr.gescom.gescom_demo_spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.gescom.gescom_demo_spring.entities.Categorie;
import fr.gescom.gescom_demo_spring.repository.CategorieRepository;
import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;

//@Component
@Service
public class CategorieDao {
	
	@Autowired
	private CategorieRepository categorieRepository;
	/**
	 * Cette méthode retourne la liste des catégories
	 * @return
	 */
	public List<Categorie> getAllCategorie(){
		return this.categorieRepository.findAll();
	}
	/*
	 * 
	 */
	public void insertCategorie(Categorie categorie) {
		this.categorieRepository.save(categorie);
	}
	public void deleteCategorie(Categorie categorie) {
		this.categorieRepository.delete(categorie);
	}
	public void updateCategorie(Categorie categorie) {
		//this.insertCategorie(categorie);
		this.categorieRepository.save(categorie);
	}
	public Categorie getOnecategorieById(int id) {
		//return this.categorieRepository.findById(id);
		return this.categorieRepository.getById(id);
	}
}
