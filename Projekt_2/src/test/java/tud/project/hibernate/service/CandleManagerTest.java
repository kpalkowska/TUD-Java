package tud.project.hibernate.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import tud.project.hibernate.domain.Cake;
import tud.project.hibernate.domain.Candle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class CandleManagerTest {

	private final String cake_name = "Tort orzechowy";
	private final Double cake_price = 28.00;
	private final String candle_color = "blue";
	private final String candle_figure = "six";
	private final String candle_color2 = "orange";
	private final String candle_figure2 = "five";
	private final String candle_color3 = "pink";
	
	@Autowired
	CakeManager cakeM;
	
	@Autowired
	CandleManager candleM;
	
	@Test
	public void checkAddCandle(){
		
		int n = candleM.getAllCandles().size();
		
		Candle candle = new Candle(candle_color, candle_figure);
		candleM.addCandle(candle);
		
		Candle candleRetrived = candleM.getCandleById(candle);
		assertEquals(candle.getId(), candleRetrived.getId());
		assertEquals(candle_color, candleRetrived.getColor());
		assertEquals(candle_figure, candleRetrived.getFigure());
		
		assertEquals(n+1, candleM.getAllCandles().size());
	}
	
	@Test
	public void checkEditCandle(){
		
		Candle candle = new Candle(candle_color, candle_figure);
		candleM.addCandle(candle);
		
		Candle candleRetrived = candleM.getCandleById(candle);
		assertEquals(candle.getId(), candleRetrived.getId());
		assertEquals(candle_color, candleRetrived.getColor());
		assertEquals(candle_figure, candleRetrived.getFigure());
		
		candleRetrived.setColor("red");
		candleRetrived.setFigure("two");
		candleM.editCandle(candleRetrived);
		
		Candle candleRetrived2 = candleM.getCandleById(candleRetrived);
		assertEquals(candleRetrived.getId(), candleRetrived2.getId());
		assertEquals("red", candleRetrived2.getColor());
		assertEquals("two", candleRetrived2.getFigure());
	}
	
	@Test
	public void checkRemoveCandle(){
		
		Candle candle = new Candle(candle_color, candle_figure);
		candleM.addCandle(candle);
		
		int n = candleM.getAllCandles().size();
		
		Candle candleRetrived = candleM.getCandleById(candle);
		assertEquals(candle.getId(), candleRetrived.getId());
		assertEquals(candle_color, candleRetrived.getColor());
		assertEquals(candle_figure, candleRetrived.getFigure());
		
		candleM.removeCandle(candle);

		assertNull(candleM.getCandleById(candleRetrived));
		assertEquals(n-1, candleM.getAllCandles().size());
	}
	
	@Test
	public void checkAddCandleToCake(){
		
		Candle candle = new Candle(candle_color2, candle_figure2);
		candleM.addCandle(candle);
		
		Candle candleRetrived = candleM.getCandleById(candle);
		assertEquals(candle.getId(), candleRetrived.getId());
		assertEquals(candle_color2, candleRetrived.getColor());
		assertEquals(candle_figure2, candleRetrived.getFigure());
		
		Candle candle2 = new Candle(candle_color2, candle_figure2);
		candleM.addCandle(candle2);
		
		Candle candleRetrived2 = candleM.getCandleById(candle2);
		assertEquals(candle2.getId(), candleRetrived2.getId());
		assertEquals(candle_color2, candleRetrived2.getColor());
		assertEquals(candle_figure2, candleRetrived2.getFigure());
		
		Cake cake = new Cake(cake_name, cake_price);
		cakeM.addCake(cake);
		
		Cake cakeRetrived = cakeM.getCakeById(cake);
		assertEquals(cake.getId(), cakeRetrived.getId());
		assertEquals(cake_name, cakeRetrived.getName());
		assertEquals(cake_price, cakeRetrived.getPrice());
		
		List<Candle> candlesOnCake = candleM.getCandleByCake(cakeRetrived);
		int n = candlesOnCake.size();
		
		candleM.addCandleToCake(candleRetrived, cakeRetrived);
		candleM.addCandleToCake(candleRetrived2, cakeRetrived);
		
		assertEquals(n+2, candlesOnCake.size());
		
		for(Candle c : candlesOnCake){
			Candle candle3 = candleM.getCandleById(c);
			assertEquals(c.getId(), candle3.getId());
			assertEquals(c.getColor(), candle3.getColor());
			assertEquals(c.getFigure(), candle3.getFigure());
		}
	}
	
	@Test
	public void checkRemoveCakeWithCandles(){
		
		Candle candle = new Candle(candle_color2, candle_figure2);
		candleM.addCandle(candle);
		
		Candle candleRetrived = candleM.getCandleById(candle);
		assertEquals(candle.getId(), candleRetrived.getId());
		assertEquals(candle_color2, candleRetrived.getColor());
		assertEquals(candle_figure2, candleRetrived.getFigure());
		
		Candle candle2 = new Candle(candle_color2, candle_figure2);
		candleM.addCandle(candle2);
		
		Candle candleRetrived2 = candleM.getCandleById(candle2);
		assertEquals(candle2.getId(), candleRetrived2.getId());
		assertEquals(candle_color2, candleRetrived2.getColor());
		assertEquals(candle_figure2, candleRetrived2.getFigure());
		
		Cake cake = new Cake(cake_name, cake_price);
		cakeM.addCake(cake);
		
		Cake cakeRetrived = cakeM.getCakeById(cake);
		assertEquals(cake.getId(), cakeRetrived.getId());
		assertEquals(cake_name, cakeRetrived.getName());
		assertEquals(cake_price, cakeRetrived.getPrice());
		
		List<Candle> candlesOnCake = candleM.getCandleByCake(cakeRetrived);
		int s = candlesOnCake.size();
		
		candleM.addCandleToCake(candleRetrived, cakeRetrived);
		candleM.addCandleToCake(candleRetrived2, cakeRetrived);
		
		assertEquals(s+2, candlesOnCake.size());
		
		for(Candle c : candlesOnCake){
			Candle candle3 = candleM.getCandleById(c);
			assertEquals(c.getId(), candle3.getId());
			assertEquals(c.getColor(), candle3.getColor());
			assertEquals(c.getFigure(), candle3.getFigure());
		}
		
		int m = candleM.getAllCandles().size();
		int n = cakeM.getAllCakes().size();

		cakeM.removeCake(cake);

		assertNull(cakeM.getCakeById(cakeRetrived));
		assertEquals(n-1, cakeM.getAllCakes().size());
		assertEquals(m-2, candleM.getAllCandles().size());
	}
	
	@Test
	public void checkGetCandleByColor(){
		
		List<Candle> candlesColor1 = candleM.getCandleByColor(candle_color3);
		int n = candlesColor1.size();
		
		Candle candle = new Candle(candle_color3, candle_figure2);
		candleM.addCandle(candle);
		
		Candle candleRetrived = candleM.getCandleById(candle);
		assertEquals(candle.getId(), candleRetrived.getId());
		assertEquals(candle_color3, candleRetrived.getColor());
		assertEquals(candle_figure2, candleRetrived.getFigure());
		
		Candle candle2 = new Candle(candle_color3, candle_figure);
		candleM.addCandle(candle2);
		
		Candle candleRetrived2 = candleM.getCandleById(candle2);
		assertEquals(candle2.getId(), candleRetrived2.getId());
		assertEquals(candle_color3, candleRetrived2.getColor());
		assertEquals(candle_figure, candleRetrived2.getFigure());
		
		List<Candle> candlesColor = candleM.getCandleByColor(candle_color3);
		
		for(Candle c : candlesColor){
			Candle candle3 = candleM.getCandleById(c);
			assertEquals(c.getId(), candle3.getId());
			assertEquals(c.getColor(), candle3.getColor());
			assertEquals(c.getFigure(), candle3.getFigure());
		}

		assertEquals(n+2, candlesColor.size());
	}
}
