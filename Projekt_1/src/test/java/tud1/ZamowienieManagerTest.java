package tud1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class ZamowienieManagerTest {
ZamowienieManager zamowienieManager = new ZamowienieManager();
	
	private final static double waga_1 = 0.6;
	private final static String data_1 = "12-12-2012";
	private final static double nowa_waga = 0.4;
	private final static String nowa_data = "09-09-2009";
	
	private static final double DELTA = 1e-15;
	
	@Test
	public void checkConnection(){
		assertNotNull(zamowienieManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		Zamowienie zamowienie = new Zamowienie(waga_1, data_1);
		
		zamowienieManager.wyczyscZamowienia();
		assertEquals(1,zamowienieManager.dodajZamowienie(zamowienie));
		
		List<Zamowienie> zamowienia = zamowienieManager.getZamowienia();
		Zamowienie zamowieniePrzetwarzane = zamowienia.get(0);
		
		assertEquals(waga_1, zamowieniePrzetwarzane.getWaga(), DELTA);
		assertEquals(data_1, zamowieniePrzetwarzane.getData());
	}
	
	@Test
	public void checkUpdate(){
		Zamowienie zamowienie = new Zamowienie(waga_1, data_1);
		
		zamowienieManager.wyczyscZamowienia();
		assertEquals(1,zamowienieManager.dodajZamowienie(zamowienie));
		
		List<Zamowienie> zamowienia = zamowienieManager.getZamowienia();
		Zamowienie zamowieniePrzetwarzane = zamowienia.get(0);
		
		zamowieniePrzetwarzane.setWaga(nowa_waga);
		zamowieniePrzetwarzane.setData(nowa_data);
		
		assertEquals(1, zamowienieManager.updateZamowienie(zamowieniePrzetwarzane));
		
		List<Zamowienie> zam = zamowienieManager.getZamowienia();
		Zamowienie zamowieniePrzetwarzane2 = zam.get(0);
		
		assertEquals(nowa_waga, zamowieniePrzetwarzane2.getWaga(), DELTA);
		assertEquals(nowa_data, zamowieniePrzetwarzane2.getData());
		assertEquals(zamowieniePrzetwarzane2.getId(), zamowieniePrzetwarzane2.getId());
	}
	
	@Test
	public void checkDelete() {
		Zamowienie zamowienie = new Zamowienie(waga_1, data_1);
		
		zamowienieManager.wyczyscZamowienia();
		assertEquals(1,zamowienieManager.dodajZamowienie(zamowienie));
		
		List<Zamowienie> zamowienia = zamowienieManager.getZamowienia();
		Zamowienie zamowieniePrzetwarzane = zamowienia.get(0);

		assertEquals(1, zamowienieManager.usunZamowienie(zamowieniePrzetwarzane));

	}
}
