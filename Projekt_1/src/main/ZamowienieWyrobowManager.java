package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ZamowienieWyrobowManager {
	private static Connection connection;
	private String url = "jdbc:hsqldb:hsql://localhost/workdb";
	private String createTableZamowienieWyrobow = "CREATE TABLE ZamowienieWyrobow(Zamowienie_id_zamowienie, Wybor_id_wybor)";

	
	private static PreparedStatement DodajZamowienieWyrobow;
	private static PreparedStatement UsunZamowienieWyrobow;
	private static PreparedStatement GetZamowienieWyrobow;
	
	private Statement statement;
	
	public ZamowienieWyrobowManager(){
		try{
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
			
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while(rs.next()){
				if("ZamowienieWyrobow".equalsIgnoreCase(rs.getString("TABLE_NAME"))){
					tableExists = true;
					break;
					
				}
			}
			
			if(!tableExists)
				statement.executeUpdate(createTableZamowienieWyrobow);
			
			DodajZamowienieWyrobow = connection.prepareStatement("INSERT INTO ZamowienieWyrobow(zamowienie_id_zamowienie, wyrob_id_wyrob) VALUES (?, ?)");
			UsunZamowienieWyrobow = connection.prepareStatement("DELETE FROM ZamowienieWyrobow");
			GetZamowienieWyrobow = connection.prepareStatement("SELECT zamowienie_id_zamowienie, wyrob_id_wyrob FROM ZamowienieWyrobow");
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}
	
	public static void wyczyscZamowienia(){
		try{
			UsunZamowienieWyrobow.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static int dodajZamowienieWyrobow(ZamowienieWyrobow zamowienieWyrobow){
		int count = 0;
		try {
			DodajZamowienieWyrobow.setLong(1, zamowienieWyrobow.getZamowienie_id_zamowienie());
			DodajZamowienieWyrobow.setLong(2, zamowienieWyrobow.getWyrob_id_wyrob());
			
			count = DodajZamowienieWyrobow.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
	
	public static List<ZamowienieWyrobow> getZamowienieWyrobow(){
		List<ZamowienieWyrobow> zamowienieWyrobow = new ArrayList<ZamowienieWyrobow>();
		
		try{
			ResultSet rs = GetZamowienieWyrobow.executeQuery();
			
			while(rs.next()){
				ZamowienieWyrobow zw = new ZamowienieWyrobow();
				zw.setZamowienie_id_zamowienie(rs.getInt("Zamowienie_id_zamowienie"));
				zw.setWyrob_id_wyrob(rs.getInt("Wyrob_id_wyrob"));
				zamowienieWyrobow.add(zw);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return zamowienieWyrobow;
	}
}
