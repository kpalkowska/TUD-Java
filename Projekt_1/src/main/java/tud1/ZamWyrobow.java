package tud1;

public class ZamWyrobow {
	private long zamowienie_id;
	private long wyrob_id;
	private String dodatkoweInfo;
	
	public ZamWyrobow(){}

	public String getDodatkoweInfo() {
		return dodatkoweInfo;
	}

	public void setDodatkoweInfo(String dodatkoweInfo) {
		this.dodatkoweInfo = dodatkoweInfo;
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
