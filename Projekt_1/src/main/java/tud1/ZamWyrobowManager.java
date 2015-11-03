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
	private String createTableZamWyrobow = "CREATE TABLE ZamWyrobow(zamowienie_id bigint, wyrob_id bigint)";
	
	private static PreparedStatement DodajZamWyrobow;
	private static PreparedStatement UsunZamWyrobow;
	private static PreparedStatement GetZamWyrobow;
	private static PreparedStatement GetWyrobById;
	private static PreparedStatement GetWyrobByZam;
	
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
			
			DodajZamWyrobow = connection.prepareStatement("INSERT INTO ZamWyrobow(zamowienie_id, wyrob_id) VALUES (?, ?)");
			UsunZamWyrobow = connection.prepareStatement("DELETE FROM ZamWyrobow");
			GetZamWyrobow = connection.prepareStatement("SELECT zamowienie_id, wyrob_id FROM ZamWyrobow");
			GetWyrobById = connection.prepareStatement("SELECT zamowienie_id, wyrob_id FROM ZamWyrobow WHERE wyrob_id = ?");
			
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
	
	public static int dodajZamWyrobow(WyrobCukierniczy w, Zamowienie z){
		int count = 0;
		try {
			DodajZamWyrobow.setLong(1, z.getId());
			DodajZamWyrobow.setLong(2, w.getId());
			
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
				zamWyrobow.add(zw);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return zamWyrobow;
	}
	
	public WyrobCukierniczy getWyrobById(WyrobCukierniczy w) {
		WyrobCukierniczy wyrob = new WyrobCukierniczy();
		try {
			GetWyrobById.setLong(1, w.getId());
			ResultSet rs = GetWyrobById.executeQuery();
			rs.next();
			wyrob.setId(rs.getInt("id_wyrob"));
			wyrob.setNazwa(rs.getString("nazwa"));
			wyrob.setCena(rs.getDouble("cena"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wyrob;
	}
	
	public List<WyrobCukierniczy> getWyrobByZam(ZamWyrobow z) {
		List<WyrobCukierniczy> wyroby = new ArrayList<WyrobCukierniczy>();

		try {

			GetWyrobByZam.setLong(1, z.getZamowienie_id());

			ResultSet rs = GetWyrobByZam.executeQuery();

			while (rs.next()) {
				WyrobCukierniczy w = new WyrobCukierniczy();
				w.setId(rs.getInt("id_wyrob"));
				w.setNazwa(rs.getString("nazwa"));
				w.setCena(rs.getDouble("cena"));
				wyroby.add(w);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wyroby;
	}
}
