package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import core.Configure;

public class Table_User {
	
	public Table_User(){
		
	}
	
	public void generate(Connection conn) throws Exception{

		PreparedStatement pst = null;

		String query = "insert into User"
				+ "(id, full_name, email)"
				+ "values "
				+ "( ?, ?, ? )";
		
		
		for(int i = 1; i <= Configure.numUser; i++){
			pst = conn.prepareStatement(query);

			int id = i;
			String full_name = "User"+i;
			String email = "user"+i +"@gist.ac.kr";
			
			pst.setInt(1, id); 
			pst.setString(2, full_name);
			pst.setString(3, email);
			
			pst.executeUpdate();			
		}

	}
	
}
