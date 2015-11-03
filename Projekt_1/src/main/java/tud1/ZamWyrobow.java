package tud1;

public class ZamWyrobow {
	private long zamowienie_id;
	private long wyrob_id;
	
	public ZamWyrobow(){}
	
	public ZamWyrobow(long zamowienie_id, long wyrob_id){
		this.zamowienie_id = zamowienie_id;
		this.wyrob_id = wyrob_id;
	}

	public long getZamowienie_id() {
		return zamowienie_id;
	}

	public void setZamowienie_id(long zamowienie_id) {
		this.zamowienie_id = zamowienie_id;
	}

	public long getWyrob_id() {
		return wyrob_id;
	}

	public void setWyrob_id(long wyrob_id) {
		this.wyrob_id = wyrob_id;
	}
	
}
