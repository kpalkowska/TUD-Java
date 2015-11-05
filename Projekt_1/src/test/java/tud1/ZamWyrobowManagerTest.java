package tud1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class ZamWyrobowManagerTest {
	ZamWyrobowManager zwManager = new ZamWyrobowManager();
	
	private final static Long zamowienie_id = (long) 1;
	private final static Long wyrob_id = (long) 1;
	private final static Long wyrob_id2 = (long) 2;
	
	@Test
	public void checkConnection(){
		assertNotNull(ZamWyrobowManager.getConnection());
	}
	
	@Test
	public void checkAddZamWyrobow(){
		ZamWyrobow zw = new ZamWyrobow(zamowienie_id, wyrob_id);
		
		zwManager.wyczyscZamWyrobow();
		assertEquals(1, zwManager.dodajZamWyrobow(zw));

	}
	
	@Test
	public void checkUpdateZamWyrobow(){
		ZamWyrobow zw = new ZamWyrobow(zamowienie_id, wyrob_id);
		
		zwManager.wyczyscZamWyrobow();
		assertEquals(1, zwManager.dodajZamWyrobow(zw));
		
		List<ZamWyrobow> zamWyr = zwManager.getZamWyrobow();
		ZamWyrobow zamWyrobowPrzetwarzane = zamWyr.get(0);
		
		zamWyrobowPrzetwarzane.setWyrob_id(wyrob_id2);
		
		assertEquals(1, zwManager.updateZamWyrobow(zamWyrobowPrzetwarzane));
		
		List<ZamWyrobow> zw2 = zwManager.getZamWyrobow();
		ZamWyrobow zamWyrobowPrzetwarzane2 = zw2.get(0);

		assertEquals(1, zwManager.updateZamWyrobow(zamWyrobowPrzetwarzane)); 
	}
}
