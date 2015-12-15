package tud.project.hibernate.service;

import java.util.List;

import tud.project.hibernate.domain.Cake;
import tud.project.hibernate.domain.Candle;

public interface CandleManager {
	
	List<Candle> getAllCandles();
	Candle getCandleById(Candle candle);
	List<Candle> getCandleByColor(String color);
	List<Candle> getCandleByCake(Cake cake);
	void addCandle(Candle candle);
	void addCandleToCake(Candle candle, Cake cake);
	void editCandle(Candle candle);
	void removeCandle(Candle candle);
	
}
