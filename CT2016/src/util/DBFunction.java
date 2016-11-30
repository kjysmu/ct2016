package util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DBFunction{

	public static void clearTable(Connection conn, String tableName) throws Exception{

	}

	public static void clearTableAll(Connection conn) throws Exception{

		PreparedStatement pst = null;
		Statement stmt = null;
		stmt = conn.createStatement();

		String query = "SET FOREIGN_KEY_CHECKS=0";
		stmt.executeUpdate(query);
		DatabaseMetaData md = conn.getMetaData();

		ResultSet rs = md.getTables(null, null, "%", null);
		while (rs.next()) {
			String tableName = rs.getString(3);
			query = "truncate table " + tableName;
			stmt.executeUpdate(query);

		}

		query = "SET FOREIGN_KEY_CHECKS=1";
		stmt.executeUpdate(query);

	}

	public static int getTableCount(Connection conn, String tableName) throws Exception {

		// Statements allow to issue SQL queries to the database
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from " + tableName);

		int count = 0;

		while (rs.next()) {
			count = rs.getInt(1);
		}

		return count;
	}
	
	
	
	

	public static Map<String, Double> getNorm (Map<String, Double> map){
		Map<String, Double> termFrequencies = new HashMap<String, Double>();
		double count = 0;
		for (Map.Entry<String, Double> termcount : map.entrySet()){
			count += termcount.getValue();
		}
		for (Map.Entry<String, Double> termCount : map.entrySet()){
			termFrequencies.put(termCount.getKey(), termCount.getValue() / (double)count);
		}
		return termFrequencies;		
	}
	
	public static Map<String, Double> SortMap(Map<String, Double> map) {
		DoubleValueComparator bvc =  new DoubleValueComparator(map);
		TreeMap<String, Double> sortedMap = new TreeMap<String, Double>(bvc);
		sortedMap.putAll(map);
		return sortedMap;
	}


}
