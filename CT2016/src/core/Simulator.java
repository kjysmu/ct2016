package core;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import table.*;
import util.DBFunction;

public class Simulator {

	public static void main(String args[]) throws Exception{

		Connection conn;
		Statement stmt;
				
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.err.print("ClassNotFoundException:");
		}

		try {
			String jdbcUrl = "jdbc:mysql://210.107.182.67:3306/ctinew";

			String userId = "ct2012";
			String userPass = "ct2012";

			conn = DriverManager.getConnection(jdbcUrl, userId, userPass);
			stmt = conn.createStatement();
			System.out.println("Successfully connected");
		
			if(Configure.isInit){
				DBFunction.clearTableAll(conn);
				System.out.println("Truncate Completed");
			}
			
			// Time (1)
			Table_Time table_Time = new Table_Time();
			table_Time.generate(conn);
			System.out.println("Time Generated");

			// User (1)
			Table_User table_User = new Table_User();
			table_User.generate(conn);
			System.out.println("User Generated");

			// Sensor_List (1)
			Table_Sensor_List table_Sensor_List = new Table_Sensor_List();
			table_Sensor_List.generate(conn);
			System.out.println("Sensor_List Generated");
			
			// Emotion_List (1)
			Table_Emotion_List table_Emotion_List = new Table_Emotion_List();
			table_Emotion_List.generate(conn);
			System.out.println("Emotion_List Generated");
			
			// Measured_Values (2)
			Table_Measured_Values table_Measured_Values = new Table_Measured_Values();
			table_Measured_Values.generate(conn);
			System.out.println("Measured_Values Generated");
			
			// Video_Feature (3)
			// Audio_Feature (3)
			// Bio_Feature (3)
			
			// MFCCs (4)
			// Delta_MFCCs (4)
			// Delta_delta_MFCCs (4)
			// EEG_left (4)
		

			stmt.close();
			conn.close();
			
			System.out.println("Complete");

		}catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}

	}

}
