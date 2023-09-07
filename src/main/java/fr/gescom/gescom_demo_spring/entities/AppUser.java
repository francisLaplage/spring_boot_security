package fr.gescom.gescom_demo_spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_user")
@Data @NoArgsConstructor @AllArgsConstructor
public class AppUser implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<AppRole> listRoles = new ArrayList<AppRole>();
}
