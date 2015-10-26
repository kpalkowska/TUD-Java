package TUD;

public class Wyrob_cukierniczy {
	private long id;
	private String nazwa;
	private double cena;
	
	public Wyrob_cukierniczy() {}
	
	public Wyrob_cukierniczy(String nazwa, double cena){
		super();
		this.nazwa = nazwa;
		this.cena = cena;
	}
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
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
