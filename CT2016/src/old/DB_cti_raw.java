package old;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DB_cti_raw {
	public static void main(String[] args) {
		try {
			Connection con = null;

			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cti",
					"root", "ct2012");

			java.sql.Statement st = null;
			java.sql.PreparedStatement pst = null;
			
			ResultSet rs = null;
			st = con.createStatement();
			
			// CTI_RAW DB Query Example
			String query = "insert into cti_raw"
					+ "(stime, stime_ms, etime, etime_ms, pid, stype, sid, path)"
					+ "values "
					+ "( ?, ?, ?, ?, ?, ?, ?, ? )";
			
			pst = con.prepareStatement(query);
			
			// For instance, stime is 2013-01-16 12:35:12.452 
			Timestamp stime = Timestamp.valueOf("2013-01-16 12:35:12");
			int stime_ms = 452;
			// For instance, etime is 2013-01-16 12:35:20.12 or 2013-01-16 12:35:20.120
			Timestamp etime = Timestamp.valueOf("2013-01-16 12:35:20");
			int etime_ms = 120;
			
			int pid = 1; // PERSON ID.
			// Sensor Type ( face, motion, bio, acoustic )
			String stype = "acoustic";  	
			int sid = 1; // SENSOR ID.
			String path = "C:\\rawdata\\acoustic\\1.wav"; // path for raw data
			
			pst.setTimestamp(1, stime); // stime (timestamp - start time of event)
			pst.setInt(2, stime_ms); // stime ms (milisecond unit)
			pst.setTimestamp(3, etime); // etime (timestamp - end time of event)
			pst.setInt(4, etime_ms); // etime ms (milisecond unit)
			
			pst.setInt(5, pid); // pid (performance id)
			pst.setString(6, stype); // stype (face,pose,bio,acoustic)
			pst.setInt(7, sid); // sid (sensor id)
			pst.setString(8, path); // path (file path)
			
			pst.executeUpdate();
			System.out.println("complete");
			
			
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}

	}
}