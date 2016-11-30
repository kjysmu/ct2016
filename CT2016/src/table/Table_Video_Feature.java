package table;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Table_Video_Feature {

	public Table_Video_Feature(){
		
		
	}
	
	public void generate(Connection conn, int id, double angry, double delight, double fear, double happy, double surprise, double disgust, int Measured_Values_id) throws Exception{

		PreparedStatement pst = null;

		String query = "insert into Video_Feature"
				+ "(id, angry, delight, fear, happy, surprise, disgust, Measured_Values_id )"
				+ "values "
				+ "( ?, ?, ?, ?, ?, ?, ?, ? )";
		
		pst = conn.prepareStatement(query);

		pst.setInt(1, id); 
		pst.setDouble(2, angry);
		pst.setDouble(3, delight);
		pst.setDouble(4, fear);
		pst.setDouble(5, happy);
		pst.setDouble(6, surprise);
		pst.setDouble(7, disgust);
		pst.setInt(8, Measured_Values_id); 
		
		pst.executeUpdate();
	}

	
}
