package table;

import java.sql.Connection;
import java.sql.PreparedStatement;

import core.Configure;

public class Table_Audio_Feature {
	
	public Table_Audio_Feature(){
		
		
	}
	
	public void generate(Connection conn, int id, double angry, double delight, double fear, double happy, double bored, double neutral, int Measured_Values_id) throws Exception{

		PreparedStatement pst = null;

		String query = "insert into Audio_Feature"
				+ "(id, angry, delight, fear, happy, bored, neutral, Measured_Values_id )"
				+ "values "
				+ "( ?, ?, ?, ?, ?, ?, ?, ? )";
		
		pst = conn.prepareStatement(query);

		pst.setInt(1, id); 
		pst.setDouble(2, angry);
		pst.setDouble(3, delight);
		pst.setDouble(4, fear);
		pst.setDouble(5, happy);
		pst.setDouble(6, bored);
		pst.setDouble(7, neutral);
		pst.setInt(8, Measured_Values_id); 
		
		pst.executeUpdate();
	}
}
