package fr.gescom.gescom_demo_spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_categorie")
@JsonIgnoreProperties(
			value = {"hibernateLazyInitializer", "handler"}
		)
public class Categorie implements Serializable{
	//1-	Attributs
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_cat")
		private int idCat;
		@Column(name = "nom_cat")
		private String nomCat;
		
		//2-1	Attribut pour la jointure
		@OneToMany(mappedBy = "categorieJoin",targetEntity = Produit.class)
		private List<Produit> listeProd=new ArrayList<Produit>();
		
	//2-	Constructeur
		public Categorie() {
			
		}
		public Categorie(int idCat, String nomCat) {
			super();
			this.idCat = idCat;
			this.nomCat = nomCat;
		}


	//3-	getteurs et setteurs
		public int getIdCat() {
			return idCat;
		}
		public void setIdCat(int idCat) {
			this.idCat = idCat;
		}
		public String getNomCat() {
			return nomCat;
		}
		public void setNomCat(String nomCat) {
			this.nomCat = nomCat;
		}
	//4-	toString()
		@Override
		public String toString() {
			return "Categorie [idCat=" + idCat + ", nomCat=" + nomCat + "]";
		}
}
