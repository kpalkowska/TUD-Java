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
	private String createTableZamWyrobow = "CREATE TABLE ZamWyrobow(zamowienie_id bigint FOREIGN KEY REFERENCES Zamowienie(zamowienie_id),"
			+ " wyrob_id bigint FOREIGN KEY REFERENCES WyrobCukierniczy(id_wyrob))";
	
	private static PreparedStatement DodajZamWyrobow;
	private static PreparedStatement UsunZamWyrobow;
	private static PreparedStatement WyczyscZamWyrobow;
	private static PreparedStatement GetZamWyrobow;
	private static PreparedStatement GetZWByWyrob;
	private static PreparedStatement GetZWByZam;
	private static PreparedStatement UpdateZamWyrobow;
	
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
			UsunZamWyrobow = connection.prepareStatement("DELETE FROM ZamWyrobow WHERE zamowienie_id=?");
			WyczyscZamWyrobow = connection.prepareStatement("DELETE FROM ZamWyrobow");
			GetZamWyrobow = connection.prepareStatement("SELECT zamowienie_id, wyrob_id FROM ZamWyrobow");
			GetZWByWyrob = connection.prepareStatement("SELECT zamowienie_id, wyrob_id FROM ZamWyrobow WHERE wyrob_id = ?");
			GetZWByZam = connection.prepareStatement("SELECT zamowienie_id, wyrob_id FROM ZamWyrobow WHERE zamowienie_id = ?");
			UpdateZamWyrobow = connection.prepareStatement("UPDATE ZamWyrobow SET wyrob_id = ? WHERE zamowienie_id = ?");
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}
	
	public static void wyczyscZamWyrobow(){
		try{
			WyczyscZamWyrobow.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public int usunZamWyrobow(ZamWyrobow zw) {
		int count = 0;
		try {
			UsunZamWyrobow.setLong(1, zw.getZamowienie_id());

			count = UsunZamWyrobow.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public static int dodajZamWyrobow(ZamWyrobow zw){
		int count = 0;
		try {
			DodajZamWyrobow.setLong(1, zw.getZamowienie_id());
			DodajZamWyrobow.setLong(2, zw.getWyrob_id());
			
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
	
	public WyrobCukierniczy getZWByWyrob(WyrobCukierniczy w) {
		WyrobCukierniczy wyrob = new WyrobCukierniczy();
		try {
			GetZWByWyrob.setLong(1, w.getId());
			ResultSet rs = GetZWByWyrob.executeQuery();
			rs.next();
			wyrob.setId(rs.getInt("id_wyrob"));
			wyrob.setNazwa(rs.getString("nazwa"));
			wyrob.setCena(rs.getDouble("cena"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wyrob;
	}
	
	public List<WyrobCukierniczy> getZWByZam(ZamWyrobow z) {
		List<WyrobCukierniczy> wyroby = new ArrayList<WyrobCukierniczy>();

		try {

			GetZWByZam.setLong(1, z.getZamowienie_id());

			ResultSet rs = GetZWByZam.executeQuery();

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
	
	public int updateZamWyrobow(ZamWyrobow zw){
		int count = 0;
		try{
			UpdateZamWyrobow.setLong(1, zw.getWyrob_id());
			UpdateZamWyrobow.setLong(2, zw.getZamowienie_id());
			
			count = UpdateZamWyrobow.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
}
