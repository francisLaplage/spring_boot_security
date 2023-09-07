package fr.gescom.gescom_demo_spring;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.gescom.gescom_demo_spring.dao.CategorieDao;
import fr.gescom.gescom_demo_spring.dao.GestionUserDao;
import fr.gescom.gescom_demo_spring.dao.ProduitDao;
import fr.gescom.gescom_demo_spring.entities.AppRole;
import fr.gescom.gescom_demo_spring.entities.AppUser;
import fr.gescom.gescom_demo_spring.entities.Categorie;
import fr.gescom.gescom_demo_spring.entities.Produit;


@SpringBootApplication
public class GescomDemoSpringApplication implements CommandLineRunner {
	@Autowired
	private CategorieDao categorieDao;
	
	@Autowired
	private ProduitDao produitDao;
	
	@Autowired
	private GestionUserDao gestionUserDao;
	
	public static void main(String[] args) {
		SpringApplication.run(GescomDemoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//1-	Insert de plusieurs catégories
			this.categorieDao.insertCategorie(new Categorie(0,"Livre"));
			this.categorieDao.insertCategorie(new Categorie(0,"Ordinateur"));
			this.categorieDao.insertCategorie(new Categorie(0,"Jeux"));
			this.categorieDao.insertCategorie(new Categorie(0,"DVD"));
			
		//2-	Insertion de plusieurs produits
			this.produitDao.insertProduit(
					new Produit(0,"Spring Boot",
							"Livre pour les nuls",45, 150,"img.jpg", 
					new Categorie(1,"Livre")));
			
			this.produitDao.insertProduit(
					new Produit(0,"Les Misérables",
							" 120 mins",5, 50,"img2.jpg", 
					new Categorie(4,"Livre")));
			
		//3-	Récupération de la liste des produits
			this.produitDao.getAllProduits()
						   .forEach(p->System.out.println(p));
		//4-	Insert de plusieurs users
		
		  List<AppRole> liste = new ArrayList<AppRole>();
		  this.gestionUserDao.insertUser( new AppUser(0,"admin","Test1234",liste));
		  this.gestionUserDao.insertUser( new AppUser(0,"francis","Test1234",liste));
		  this.gestionUserDao.insertUser( new AppUser(0,"bart","Test1234",liste));
		  this.gestionUserDao.insertUser( new AppUser(0,"lisa","Test1234",liste));
		  
		  //5- Insert des roles this.gestionUserDao.insertRole(new AppRole(0,"ADMIN"));
		  this.gestionUserDao.insertRole(new AppRole(0,"USER"));
		  this.gestionUserDao.insertRole(new AppRole(0,"MANAGER"));
		  
		  //6- Add Role To User 
		  AppRole roleAdmin = new AppRole();
		  roleAdmin.setIdRole(1); AppRole roleUser = new AppRole();
		  roleUser.setIdRole(2);
		  
		  AppUser userFrancis = new AppUser(); userFrancis.setIdUser(2); //francis
		  AppUser userBart = new AppUser(); userBart.setIdUser(3); //francis
		  this.gestionUserDao.addRoleToUser(roleAdmin, userFrancis);
		  this.gestionUserDao.addRoleToUser(roleUser, userFrancis);
		  this.gestionUserDao.addRoleToUser(roleUser, userBart);
		 
	}
	
}
