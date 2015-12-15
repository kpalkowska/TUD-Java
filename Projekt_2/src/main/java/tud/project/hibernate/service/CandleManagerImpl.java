package tud.project.hibernate.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import tud.project.hibernate.domain.Cake;
import tud.project.hibernate.domain.Candle;

@Component
@Transactional
public class CandleManagerImpl implements CandleManager{
	
	@Autowired
	private SessionFactory session;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Candle> getAllCandles() {
		return session.getCurrentSession().getNamedQuery("Candle.getAll").list();
	}

	@Override
	public Candle getCandleById(Candle candle) {
		return (Candle) session.getCurrentSession().get(Candle.class, candle.getId());
	}
	
	@Override
	public List<Candle> getCandleByColor(String color) {
		return session.getCurrentSession().getNamedQuery("Candle.getByColor").setString("color", color).list();
	}

	@Override
	public List<Candle> getCandleByCake(Cake cake) {
		Cake c = (Cake) session.getCurrentSession().get(Cake.class, cake.getId());
		return c.getCandles();
	}
	
	@Override
	public void addCandle(Candle candle) {
		candle.setId(null);
		session.getCurrentSession().persist(candle);	
	}

	@Override
	public void addCandleToCake(Candle candle, Cake cake) {
		Cake c = (Cake) session.getCurrentSession().get(Cake.class, cake.getId());
		c.getCandles().add(candle);
	}
	
	@Override
	public void editCandle(Candle candle) {
		session.getCurrentSession().merge(candle);	
	}

	@Override
	public void removeCandle(Candle candle) {
		session.getCurrentSession().delete(candle);	
	}
}
