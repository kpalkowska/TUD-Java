package tud.project.hibernate.service;

import java.util.List;

import tud.project.hibernate.domain.Cake;

public interface CakeManager {
	
	List<Cake> getAllCakes();
	Cake getCakeById(Cake cake);
	void addCake(Cake cake);
	void editCake(Cake cake);
	void removeCake(Cake cake);
}

