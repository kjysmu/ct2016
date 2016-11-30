package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

public class Table_EOG {
	public Table_EOG(){
		
	}
	
	public void generate(Connection conn, int id, int Bio_Feature_id) throws Exception{

		PreparedStatement pst = null;
		Random rand = new Random();
		String query = "insert into EOG"
				+ "(id, value, Bio_Feature_id )"
				+ "values "
				+ "( ?, ?, ? )";
		
		pst = conn.prepareStatement(query);
		
		pst.setInt(1, id); 
		pst.setDouble(2, rand.nextDouble());
		pst.setInt(3, Bio_Feature_id);
		
		pst.executeUpdate();
		

	}
	
}
