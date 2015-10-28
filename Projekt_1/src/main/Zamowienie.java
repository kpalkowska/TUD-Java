package main;

public class Zamowienie {
	static long id_zamowienie;
	private String waga;
	private String data;
	
	public Zamowienie(){}
	
	public Zamowienie(String waga, String data){
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

	public String getWaga() {
		return waga;
	}

	public void setWaga(String waga) {
		this.waga = waga;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
