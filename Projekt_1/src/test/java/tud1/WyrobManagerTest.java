package tud1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import org.junit.Test;

public class WyrobManagerTest {
WyrobManager wyrobManager = new WyrobManager();
	
	private final static String nazwa_1 = "piernik";
	private final static double cena_1 = 8.99;
	private final static String nowa_nazwa = "p¹czek";
	private final static double nowa_cena = 0.89;
	
	private static final double DELTA = 1e-15;
	
	@Test
	public void checkConnection(){
		assertNotNull(wyrobManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		WyrobCukierniczy wyrob = new WyrobCukierniczy(nazwa_1, cena_1);
		
		wyrobManager.wyczyscWyroby();
		assertEquals(1, wyrobManager.dodajWyrob(wyrob));
		
		List<WyrobCukierniczy> wc = wyrobManager.getWyroby();
		WyrobCukierniczy wyrobPrzetwarzany = wc.get(0);
		
		assertEquals(nazwa_1, wyrobPrzetwarzany.getNazwa());
		assertEquals(cena_1, wyrobPrzetwarzany.getCena(), DELTA);
		
	}
	
	@Test
	public void checkUpdate(){
		WyrobCukierniczy wyrob = new WyrobCukierniczy(nazwa_1, cena_1);

		wyrobManager.wyczyscWyroby();
		assertEquals(1, wyrobManager.dodajWyrob(wyrob));
		
		List<WyrobCukierniczy> wc = wyrobManager.getWyroby();
		WyrobCukierniczy wyrobPrzetwarzany = wc.get(0);
		
		wyrobPrzetwarzany.setNazwa(nowa_nazwa);
		wyrobPrzetwarzany.setCena(nowa_cena);
		
		assertEquals(1, wyrobManager.updateWyrob(wyrobPrzetwarzany));
		
		List<WyrobCukierniczy> wc2 = wyrobManager.getWyroby();
		WyrobCukierniczy wyrobPrzetwarzany2 = wc2.get(0);
		
		assertEquals(nowa_nazwa, wyrobPrzetwarzany2.getNazwa());
		assertEquals(nowa_cena, wyrobPrzetwarzany2.getCena(), DELTA);
		assertEquals(wyrobPrzetwarzany2.getId(), wyrobPrzetwarzany2.getId());
	}
	
	@Test
	public void checkDelete() {
		WyrobCukierniczy wyrob = new WyrobCukierniczy(nazwa_1, cena_1);

		wyrobManager.wyczyscWyroby();
		assertEquals(1, wyrobManager.dodajWyrob(wyrob));
		
		List<WyrobCukierniczy> wc = wyrobManager.getWyroby();
		WyrobCukierniczy wyrobPrzetwarzany = wc.get(0);

		assertEquals(1, wyrobManager.usunWyrob(wyrobPrzetwarzany));

	}
}
