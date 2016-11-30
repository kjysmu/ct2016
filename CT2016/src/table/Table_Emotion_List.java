package table;

import java.sql.Connection;
import java.sql.PreparedStatement;

import core.Configure;

public class Table_Emotion_List {
	public Table_Emotion_List(){
		
	}
	
	public void generate(Connection conn) throws Exception{

		PreparedStatement pst = null;

		String query = "insert into Emotion_List"
				+ "(id, name)"
				+ "values "
				+ "( ?, ? )";
		
		for(int i = 0; i < Configure.emotionList.length; i++){
			pst = conn.prepareStatement(query);

			int id = i+1;
			String name = Configure.emotionList[i];
			
			pst.setInt(1, id); 
			pst.setString(2, name);
			
			pst.executeUpdate();			
		}

	}
	
	
}
