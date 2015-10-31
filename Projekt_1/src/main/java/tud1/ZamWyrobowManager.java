package tud1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ZamWyrobowManager {
	private static Connection connection;
	private String url = "jdbc:hsqldb:hsql://localhost/workdb";
	private String createTableZamWyrobow = "CREATE TABLE ZamWyrobow(zamowienie_id bigint, wyrob_id bigint, dodatkoweInfo varchar(100), CONSTRAINT fk1 FOREIGN KEY(zamowienie_id) REFERENCES Zamowienie(id_zamowienie), CONSTRAINT fk2 FOREIGN KEY(wyrob_id) REFERENCES WyrobCukierniczy(id_wyrob))";
	
	private static PreparedStatement DodajZamWyrobow;
	private static PreparedStatement UsunZamWyrobow;
	private static PreparedStatement GetZamWyrobow;
	
	private Statement statement;
	
	public ZamWyrobowManager(){
		try{
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
			
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while(rs.next()){
				if("ZamWyrobow".equalsIgnoreCase(rs.getString("TABLE_NAME"))){
					tableExists = true;
					break;
					
				}
			}
			
			if(!tableExists)
				statement.executeUpdate(createTableZamWyrobow);
			
			DodajZamWyrobow = connection.prepareStatement("INSERT INTO ZamWyrobow(zamowienie_id, wyrob_id, dodatkoweInfo) VALUES (?, ?, ?)");
			UsunZamWyrobow = connection.prepareStatement("DELETE FROM ZamWyrobow");
			GetZamWyrobow = connection.prepareStatement("SELECT zamowienie_id, wyrob_id, dodatkoweInfo FROM ZamowienieWyrobow");
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}
	
	public static void wyczyscZamWyrobow(){
		try{
			UsunZamWyrobow.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static int dodajZamWyrobow(ZamWyrobow zw){
		int count = 0;
		try {
			DodajZamWyrobow.setLong(1, zw.getZamowienie_id());
			DodajZamWyrobow.setLong(2, zw.getWyrob_id());
			DodajZamWyrobow.setString(3, zw.getDodatkoweInfo());
			
			count = DodajZamWyrobow.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
	
	public static List<ZamWyrobow> getZamWyrobow(){
		List<ZamWyrobow> zamWyrobow = new ArrayList<ZamWyrobow>();
		
		try{
			ResultSet rs = GetZamWyrobow.executeQuery();
			
			while(rs.next()){
				ZamWyrobow zw = new ZamWyrobow();
				zw.setZamowienie_id(rs.getInt("zamowienie_id"));
				zw.setWyrob_id(rs.getInt("wyrob_id"));
				zw.setDodatkoweInfo(rs.getString("dodatkoweInfo"));
				zamWyrobow.add(zw);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return zamWyrobow;
	}
}
