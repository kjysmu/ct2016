package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

public class Table_EEG_left {

	public Table_EEG_left(){
		
	}
	
	public void generate(Connection conn, int id, int Bio_Feature_id) throws Exception{

		PreparedStatement pst = null;
		Random rand = new Random();
		String query = "insert into EEG_left"
				+ "(id, gamma, beta, alpha, theta, delta, Bio_Feature_id )"
				+ "values "
				+ "( ?, ?, ?, ?, ?, ?, ? )";
		
		pst = conn.prepareStatement(query);
		
		pst.setInt(1, id); 
		
		for(int index = 2; index <= 6; index++){
			pst.setDouble(index, rand.nextDouble()); 
		}
		
		pst.setInt(7, Bio_Feature_id);
		
		pst.executeUpdate();
		

	}
	
	
	
}
