package tud.project.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "Candle.getAll", query = "Select c from Candle c"),
	@NamedQuery(name = "Candle.getByColor", query = "Select c from Candle c where c.color= :color")
})
public class Candle {
	
	private Long id;
	private String color;
	private String figure;
	
	public Candle(){}
	
	public Candle(String color, String figure){
		this.color = color;
		this.figure = figure;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFigure() {
		return figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}
}
