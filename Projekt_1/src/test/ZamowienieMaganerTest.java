package test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import main.Zamowienie;
import main.ZamowienieManager;

public class ZamowienieMaganerTest {
	ZamowienieManager zamowienieManager = new ZamowienieManager();
	
	private final static String waga_1 = "0.6kg";
	private final static String data_1 = "12-12-2012";
	
	@Test
	public void checkConnection(){
		assertNotNull(zamowienieManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		Zamowienie zamowienie = new Zamowienie(waga_1, data_1);
		
		zamowienieManager.wyczyscZamowienia();
		assertEquals(1,zamowienieManager.dodajZamowienie(zamowienie));
		
		List<Zamowienie> zamowienia = zamowienieManager.getWyroby();
		Zamowienie zamowieniePrzetwarzane = zamowienia.get(0);
		
		assertEquals(waga_1, zamowieniePrzetwarzane.getWaga());
		assertEquals(data_1, zamowieniePrzetwarzane.getData());
	}
}
