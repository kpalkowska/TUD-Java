package tud1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class ZamWyrobowManagerTest {
	ZamWyrobowManager zwManager = new ZamWyrobowManager();
	WyrobManager wyrobManager = new WyrobManager();
	ZamowienieManager zamowienieManager = new ZamowienieManager();
	
	private final static String nazwa_1 = "piernik";
	private final static double cena_1 = 8.99;
	private final static double waga_1 = 0.6;
	private final static String data_1 = "12-12-2012";
	
	private static final double DELTA = 1e-15;
	
	@Test
	public void checkConnection(){
		assertNotNull(ZamWyrobowManager.getConnection());
	}
	
	@Test
	public void checkAddWyrob(){
		WyrobCukierniczy wyrob = new WyrobCukierniczy(nazwa_1, cena_1);
		
		wyrobManager.wyczyscWyroby();
		assertEquals(1, wyrobManager.dodajWyrob(wyrob));
		
		List<WyrobCukierniczy> wc = wyrobManager.getWyroby();
		WyrobCukierniczy wyrobPrzetwarzany = wc.get(0);
		
		assertEquals(nazwa_1, wyrobPrzetwarzany.getNazwa());
		assertEquals(cena_1, wyrobPrzetwarzany.getCena(), DELTA);
	}
	
	@Test
	public void checkAddZamowienie(){
		Zamowienie zamowienie = new Zamowienie(waga_1, data_1);
		
		zamowienieManager.wyczyscZamowienia();
		assertEquals(1,zamowienieManager.dodajZamowienie(zamowienie));
		
		List<Zamowienie> zamowienia = zamowienieManager.getZamowienia();
		Zamowienie zamowieniePrzetwarzane = zamowienia.get(0);
		
		assertEquals(waga_1, zamowieniePrzetwarzane.getWaga(), DELTA);
		assertEquals(data_1, zamowieniePrzetwarzane.getData());
	}
	
	@Test
	public void checkAddZamWyrobow(){
		WyrobCukierniczy wyrob = new WyrobCukierniczy(nazwa_1, cena_1);
		
		wyrobManager.wyczyscWyroby();
		assertEquals(1, wyrobManager.dodajWyrob(wyrob));
		
		List<WyrobCukierniczy> wc = wyrobManager.getWyroby();
		WyrobCukierniczy wyrobPrzetwarzany = wc.get(0);
		
		Zamowienie zamowienie = new Zamowienie(waga_1, data_1);
		
		zamowienieManager.wyczyscZamowienia();
		assertEquals(1,zamowienieManager.dodajZamowienie(zamowienie));
		
		List<Zamowienie> zamowienia = zamowienieManager.getZamowienia();
		Zamowienie zamowieniePrzetwarzane = zamowienia.get(0);

		assertEquals(1, zwManager.dodajZamWyrobow(wyrobPrzetwarzany, zamowieniePrzetwarzane));
	}
}
