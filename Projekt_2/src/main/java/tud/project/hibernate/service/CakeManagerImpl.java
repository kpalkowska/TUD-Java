package tud.project.hibernate.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import tud.project.hibernate.domain.Cake;

@Component
@Transactional
public class CakeManagerImpl implements CakeManager{
	
	@Autowired
	private SessionFactory session;
	
	public SessionFactory getSessionFactory(){
		return session;
	}
	
	public void setSessionFactory(SessionFactory session){
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cake> getAllCakes(){
		return session.getCurrentSession().getNamedQuery("Cake.getAll").list();
	}
	
	@Override
	public Cake getCakeById(Cake cake){
		return (Cake) session.getCurrentSession().get(Cake.class, cake.getId());
	}
	
	@Override
	public void addCake(Cake cake){
		cake.setId(null);
		session.getCurrentSession().persist(cake);
	}
	
	@Override
	public void editCake(Cake cake){
		session.getCurrentSession().merge(cake);
	}
	
	@Override
	public void removeCake(Cake cake){
		session.getCurrentSession().delete(cake);
	}
}