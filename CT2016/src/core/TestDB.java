package core;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import util.DBFunction;

public class TestDB {

	public static void main(String args[]) throws Exception{

		Connection conn;
		Statement stmt;
		
		DBFunction dbUtil;

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

			

			stmt.close();
			conn.close();
			
			System.out.println("Complete");

		}catch(SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}

	}

}
