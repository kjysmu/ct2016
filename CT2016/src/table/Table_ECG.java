package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

public class Table_ECG {

	public Table_ECG(){
		
	}
	
	public void generate(Connection conn, int id, int Bio_Feature_id) throws Exception{

		PreparedStatement pst = null;
		Random rand = new Random();
		String query = "insert into ECG"
				+ "(id, HRV, LF, HF, BPM, SDNN, RMSSD, Bio_Feature_id )"
				+ "values "
				+ "( ?, ?, ?, ?, ?, ?, ?, ? )";
		
		pst = conn.prepareStatement(query);
		
		pst.setInt(1, id); 
		
		for(int index = 2; index <= 7; index++){
			pst.setDouble(index, rand.nextDouble()); 
		}
		
		pst.setInt(8, Bio_Feature_id);
		
		pst.executeUpdate();
		

	}
	
	
}
