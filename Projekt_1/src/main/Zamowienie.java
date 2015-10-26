package TUD;

import java.util.Date;

public class Zamowienie {
	private long id;
	private double waga;
	private Date data;
	
	public Zamowienie(){}
	
	public Zamowienie(double waga, Date data){
		super();
		this.waga = waga;
		this.data = data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
