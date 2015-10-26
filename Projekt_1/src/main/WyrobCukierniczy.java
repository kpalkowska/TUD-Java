package main;

public class WyrobCukierniczy {
	private long id_wyrob;
	private String nazwa;
	private double cena;
	
	public WyrobCukierniczy() {}
	
	public WyrobCukierniczy(String nazwa, double cena){
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

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	
	
	
	
}
