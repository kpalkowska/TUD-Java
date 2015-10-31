package tud1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import org.junit.Test;

public class ZamWyrobowManagerTest {
	ZamWyrobowManager zwManager = new ZamWyrobowManager();
	private final static long wyrob_id = 1;
	private final static long zamowienie_id = 1;
	
	@Test
	public void checkConnection(){
		assertNotNull(ZamWyrobowManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		ZamWyrobow zw = new ZamWyrobow();
		
		ZamWyrobowManager.wyczyscZamWyrobow();
		assertEquals(1,ZamWyrobowManager.dodajZamWyrobow(zw));

		List<ZamWyrobow> zamWyr = ZamWyrobowManager.getZamWyrobow();
		ZamWyrobow zamWyrobowPrzetwarzane = zamWyr.get(0);
		
		assertEquals(zamowienie_id, zamWyrobowPrzetwarzane.getZamowienie_id());
		assertEquals(wyrob_id, zamWyrobowPrzetwarzane.getWyrob_id());
	}
}
