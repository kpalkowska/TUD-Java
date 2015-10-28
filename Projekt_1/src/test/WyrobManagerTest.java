package test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import main.WyrobCukierniczy;
import main.WyrobManager;

public class WyrobManagerTest {
	WyrobManager wyrobManager = new WyrobManager();
	
	private final static String nazwa_1 = "piernik";
	private final static String cena_1 = "15.0";
	
	@Test
	public void checkConnection(){
		assertNotNull(wyrobManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		WyrobCukierniczy wyrob = new WyrobCukierniczy(nazwa_1, cena_1);
		
		wyrobManager.wyczyscWyroby();
		assertEquals(1,wyrobManager.dodajWyrob(wyrob));
		
		List<WyrobCukierniczy> wyroby = wyrobManager.getWyroby();
		WyrobCukierniczy wyrobPrzetwarzany = wyroby.get(0);
		
		assertEquals(nazwa_1, wyrobPrzetwarzany.getNazwa());
		assertEquals(cena_1, wyrobPrzetwarzany.getCena());
	}
}
