package tud1;

public class Zamowienie {
	private long id_zamowienie;
	private double waga;
	private String data;
	
	public Zamowienie(){}
	
	public Zamowienie(double waga, String data){
		super();
		this.waga = waga;
		this.data = data;
	}

	public long getId() {
		return id_zamowienie;
	}

	public void setId(long id_zamowienie) {
		this.id_zamowienie = id_zamowienie;
	}

	public double getWaga() {
		return waga;
	}

	public void setWaga(double waga) {
		this.waga = waga;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
