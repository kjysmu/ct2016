package table;

import java.sql.Connection;
import java.sql.PreparedStatement;

import core.Configure;

public class Table_Sensor_List {

	public Table_Sensor_List(){
		
	}
	
	
	public void generate(Connection conn) throws Exception{

		PreparedStatement pst = null;

		String query = "insert into Sensor_List"
				+ "(id, type)"
				+ "values "
				+ "( ?, ? )";
		
		int numType = Configure.sensorType.length;

		for(int i = 0; i < numType; i++){
			int id = i+1;
			String type = Configure.sensorType[i];
			
			pst = conn.prepareStatement(query);
			pst.setInt(1, id); 
			pst.setString(2, type);
			 
			pst.executeUpdate();	
		}

		
		/*
		int numType = Configure.sensorType.length;
		int numSensor = Configure.numSensor;
		int numSensorPerType = (int) numSensor / numType;
		
		for(int i = 0; i < Configure.numSensor; i++){
			pst = conn.prepareStatement(query);
			
			int typeIndex = (int) i/numSensorPerType;

			int id = i+1;
			String type = Configure.sensorType[typeIndex];
			
			pst.setInt(1, id); 
			pst.setString(2, type);
			
			pst.executeUpdate();			
		}
		*/

	}
	
	
}
