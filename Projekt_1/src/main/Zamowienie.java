package main;

import java.util.Date;

public class Zamowienie {
	private long id_zamowienie;
	private double waga;
	private Date data;
	
	public Zamowienie(){}
	
	public Zamowienie(double waga, Date data){
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
