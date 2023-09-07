package fr.gescom.gescom_demo_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.gescom.gescom_demo_spring.entities.AppUser;

@Repository
public interface AppUserRepository 
	extends JpaRepository<AppUser, Integer>{

	public AppUser findUserByUsername(String username);
}
