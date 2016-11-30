package table;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import core.Configure;

public class Table_Time {
	
	public Table_Time(){
		
	}
	public void generate(Connection conn) throws Exception{
		
		PreparedStatement pst = null;
		
		String strDate= Configure.strDate;
		
		String strTime= Configure.strTime;
		String endTime= Configure.endTime;
		
		int interval = Configure.interval;
		
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		
		java.util.Date date_start = df.parse(strTime); 
		java.util.Date date_end = df.parse(endTime); 
		
		int id = 1;
		
		String query = "insert into Time"
				+ "(id, date, timestamp, timeinterval)"
				+ "values "
				+ "( ?, ?, ?, ? )";
		
		String timeintervalStr = "00:00:";
		if(Configure.interval <= 0){
			System.out.println("Interval should be in between 1~59 seconds!");
			System.exit(0); 
		}else if(Configure.interval >= 1 && Configure.interval <= 9){
			timeintervalStr += "0"+Configure.interval;
		}else if(Configure.interval >= 10 && Configure.interval <= 59){
			timeintervalStr += Configure.interval;
		}else{
			System.out.println("Interval should be in between 1~59 seconds!");
			System.exit(0); 
		}
		Time timeinterval = Time.valueOf(timeintervalStr);
	
		while(true){
			
			pst = conn.prepareStatement(query);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date_start);
			cal.add(Calendar.SECOND, interval);
			
			strTime = df.format(cal.getTime());
			date_start = df.parse(strTime);
			
			if(date_end.before(date_start)){
				break;
			}
			
			Date date = Date.valueOf(strDate);
	
			Timestamp ts = Timestamp.valueOf(strDate + " " + strTime);

			pst.setInt(1, id); 
			pst.setDate(2, date); 
			pst.setTimestamp(3, ts); 
			pst.setTime(4, timeinterval); 

			pst.executeUpdate();			
			id++;
		}

	}

}
