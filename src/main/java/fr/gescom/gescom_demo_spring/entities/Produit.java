package fr.gescom.gescom_demo_spring.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_produit")
@Data @NoArgsConstructor @AllArgsConstructor
public class Produit implements Serializable{
	//1-	Attributs
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_prod")
		private int idProd;
		@Column(name = "nom_prod")
		private String nomProd;
		@Column(name = "description_prod")
		private String descriptionProd;
		@Column(name = "prix_prod")
		private double prixProd;
		@Column(name = "stock_prod")
		private int stockProd;
		@Column(name = "image_prod")
		private String imageProd;
	//2-	attribut de type objet pour la jointure
		@ManyToOne
		@JoinColumn(name = "idCat")
		private Categorie categorieJoin;
}
