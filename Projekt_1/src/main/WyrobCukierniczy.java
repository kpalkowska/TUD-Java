package main;

public class WyrobCukierniczy {
	static long id_wyrob;
	private String nazwa;
	private String cena;
	
	public WyrobCukierniczy() {}
	
	public WyrobCukierniczy(String nazwa, String cena){
		super();
		this.nazwa = nazwa;
		this.cena = cena;
	}
	
	public long getId(){
		return id_wyrob;
	}
	
	public void setId(long id_wyrob){
		this.id_wyrob = id_wyrob;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}
	
	
	
	
}
