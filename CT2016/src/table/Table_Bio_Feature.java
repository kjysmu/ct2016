package table;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Table_Bio_Feature {
	
	public Table_Bio_Feature(){
		
		
	}
	
	public void generate(Connection conn, int id, double arousal, double sentiment, double angry, double delight, double fear, double happy, int Measured_Values_id) throws Exception{

		PreparedStatement pst = null;

		String query = "insert into Bio_Feature"
				+ "(id, arousal, sentiment, angry, delight, fear, happy, Measured_Values_id )"
				+ "values "
				+ "( ?, ?, ?, ?, ?, ?, ?, ? )";
		
		pst = conn.prepareStatement(query);

		pst.setInt(1, id);
		pst.setDouble(2, arousal);
		pst.setDouble(3, sentiment);
		pst.setDouble(4, angry);
		pst.setDouble(5, delight);
		pst.setDouble(6, fear);
		pst.setDouble(7, happy);
		pst.setInt(8, Measured_Values_id); 
		
		pst.executeUpdate();
	}
	

	
}
