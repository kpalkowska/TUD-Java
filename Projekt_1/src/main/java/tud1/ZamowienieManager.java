package tud1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ZamowienieManager {
	private Connection connection;
	private String url = "jdbc:hsqldb:hsql://localhost/workdb";
	private String createTableZamowienie = "CREATE TABLE Zamowienie(id_zamowienie bigint GENERATED BY DEFAULT AS IDENTITY, waga double, data varchar(10))";

	
	private PreparedStatement DodajZamowienie;
	private PreparedStatement UsunZamowienie;
	private PreparedStatement UsunZamowienia;
	private PreparedStatement GetZamowienie;
	private PreparedStatement UpdateZamowienie;
	
	private Statement statement;
	
	public ZamowienieManager(){
		try{
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
			
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while(rs.next()){
				if("Zamowienie".equalsIgnoreCase(rs.getString("TABLE_NAME"))){
					tableExists = true;
					break;
					
				}
			}
			
			if(!tableExists)
				statement.executeUpdate(createTableZamowienie);
			
			DodajZamowienie = connection.prepareStatement("INSERT INTO Zamowienie(waga, data) VALUES (?, ?)");
			UsunZamowienie = connection.prepareStatement("DELETE FROM Zamowienie WHERE id_zamowienie = ?");
			UsunZamowienia = connection.prepareStatement("DELETE FROM Zamowienie");
			GetZamowienie = connection.prepareStatement("SELECT id_zamowienie, waga, data FROM Zamowienie");
			UpdateZamowienie = connection.prepareStatement("UPDATE Zamowienie SET waga = ?, data = ? WHERE id_zamowienie = ?");
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		return connection;
	}
	
	public void wyczyscZamowienia(){
		try{
			UsunZamowienia.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public int usunZamowienie(Zamowienie zamowienie){
		int count = 0;
		try {
			UsunZamowienie.setLong(1, zamowienie.getId());
			
			count = UsunZamowienie.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
	
	public int dodajZamowienie(Zamowienie zamowienie){
		int count = 0;
		try {
			DodajZamowienie.setDouble(1, zamowienie.getWaga());
			DodajZamowienie.setString(2, zamowienie.getData());
			
			count = DodajZamowienie.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Zamowienie> getZamowienia(){
		List<Zamowienie> zamowienia = new ArrayList<Zamowienie>();
		
		try{
			ResultSet rs = GetZamowienie.executeQuery();
			
			while(rs.next()){
				Zamowienie z = new Zamowienie();
				z.setId(rs.getInt("id_zamowienie"));
				z.setWaga(rs.getDouble("waga"));
				z.setData(rs.getString("data"));
				zamowienia.add(z);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return zamowienia;
	}
	
	public int updateZamowienie(Zamowienie zamowienie){
		int count = 0;
		try {
			UpdateZamowienie.setDouble(1, zamowienie.getWaga());
			UpdateZamowienie.setString(2, zamowienie.getData());
			UpdateZamowienie.setLong(3, zamowienie.getId());
			
			count = UpdateZamowienie.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
}