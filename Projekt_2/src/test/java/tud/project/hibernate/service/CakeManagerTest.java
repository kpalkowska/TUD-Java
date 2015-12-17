package tud.project.hibernate.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import tud.project.hibernate.domain.Cake;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class CakeManagerTest {
	
	private final String cake_name = "Torcik wiedenski";
	private final Double cake_price = 23.00;
	private final String cake_name2 = "Tort Oreo";
	private final Double cake_price2 = 18.00;
	
	private static final double DELTA = 1e-15;
	
	@Autowired
	CakeManager cakeM;
	
	@Test
	public void checkAddCake(){
		
		int n = cakeM.getAllCakes().size();
		
		Cake cake = new Cake(cake_name, cake_price);
		cakeM.addCake(cake);
		
		Cake cakeRetrived = cakeM.getCakeById(cake);
		assertEquals(cake.getId(), cakeRetrived.getId());
		assertEquals(cake_name, cakeRetrived.getName());
		assertEquals(cake_price, cakeRetrived.getPrice());
		
		assertEquals(n+1, cakeM.getAllCakes().size());
	}
	
	@Test
	public void checkEditCake(){
		
		Cake cake = new Cake(cake_name, cake_price);
		cakeM.addCake(cake);
		
		Cake cakeRetrived = cakeM.getCakeById(cake);
		assertEquals(cake.getId(), cakeRetrived.getId());
		assertEquals(cake_name, cakeRetrived.getName());
		assertEquals(cake_price, cakeRetrived.getPrice());
		
		cakeRetrived.setName(cake_name2);
		cakeRetrived.setPrice(cake_price2);
		cakeM.editCake(cakeRetrived);
		
		Cake cakeRetrived2 = cakeM.getCakeById(cakeRetrived);
		assertEquals(cakeRetrived.getId(), cakeRetrived2.getId());
		assertEquals(cake_name2, cakeRetrived2.getName());
		assertEquals(cake_price2, cakeRetrived2.getPrice(), DELTA);
		
	}
	
	@Test
	public void checkRemoveCake() {
		
		Cake cake = new Cake(cake_name, cake_price);
		cakeM.addCake(cake);
		
		int n = cakeM.getAllCakes().size();
	
		Cake cakeRetrived = cakeM.getCakeById(cake);
		assertEquals(cake.getId(), cakeRetrived.getId());
		assertEquals(cake_name, cakeRetrived.getName());
		assertEquals(cake_price, cakeRetrived.getPrice());
		
		cakeM.removeCake(cake);

		assertNull(cakeM.getCakeById(cakeRetrived));
		assertEquals(n-1, cakeM.getAllCakes().size());
		}
}
