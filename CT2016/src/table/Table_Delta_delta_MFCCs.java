package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

public class Table_Delta_delta_MFCCs {

	public Table_Delta_delta_MFCCs(){
		
	}
	
	public void generate(Connection conn, int id, int Audio_Feature_id) throws Exception{

		PreparedStatement pst = null;
		Random rand = new Random();
		String query = "insert into Delta_delta_MFCCs"
				+ "(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, log_energy, Audio_Feature_id )"
				+ "values "
				+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		
		pst = conn.prepareStatement(query);
		
		pst.setInt(1, id); 
		
		for(int index = 2; index <= 14; index++){
			pst.setDouble(index, rand.nextDouble()); 
		}
		
		pst.setInt(15, Audio_Feature_id);
		
		pst.executeUpdate();
		

	}
	
	
	
}
