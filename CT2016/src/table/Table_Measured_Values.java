package table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import core.Configure;
import util.DBFunction;

public class Table_Measured_Values {

	public Table_Measured_Values(){
		
	}
	
	public void generate(Connection conn) throws Exception{

		PreparedStatement pst = null;
		Map<String, Integer> emoIndexMap = new HashMap<String, Integer>();

		String query = "insert into Measured_Values"
				+ "(id, user_id, sensor_id, Time_id, emotion, angry, delight, fear, happy, surprise, disgust, bored, neutral)"
				+ "values "
				+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		
		int totalTimeCount = DBFunction.getTableCount(conn, "Time");
		int totalUserCount = DBFunction.getTableCount(conn, "User");
		int totalSensorCount = DBFunction.getTableCount(conn, "Sensor_List");
		int totalEmotionCount = DBFunction.getTableCount(conn, "Emotion_List");
		
		for(int emotion_id = 0; emotion_id < totalEmotionCount; emotion_id++){
			String emotion_name = Configure.emotionList[emotion_id];
			emoIndexMap.put(emotion_name, emotion_id+1);
		}
		
		int id = 1;
		
		int sid_video = 1;
		int sid_audio = 1;
		int sid_bio = 1;
		
		Table_Audio_Feature table_Audio_Feature = new Table_Audio_Feature();
		Table_Video_Feature table_Video_Feature = new Table_Video_Feature();
		Table_Bio_Feature table_Bio_Feature = new Table_Bio_Feature();
		
		Table_Delta_delta_MFCCs table_Delta_delta_MFCCs = new Table_Delta_delta_MFCCs();
		Table_Delta_MFCCs table_Delta_MFCCs = new Table_Delta_MFCCs();
		Table_MFCCs table_MFCCs = new Table_MFCCs();
		
		Table_ECG table_ECG = new Table_ECG();
		Table_EEG_left table_EEG_left = new Table_EEG_left();
		Table_EEG_right table_EEG_right = new Table_EEG_right();
		Table_EMG table_EMG = new Table_EMG();
		Table_EOG table_EOG = new Table_EOG();
		
		for(int time_id = 1; time_id <= totalTimeCount; time_id++){
			for(int user_id = 1; user_id <= totalUserCount; user_id++){
				for(int sensor_id = 1; sensor_id <= totalSensorCount; sensor_id++){
					
					pst = conn.prepareStatement(query);
					Map<String, Double> emoMap = new HashMap<String, Double>();

					pst.setInt(1, id ); 
					pst.setInt(2, user_id ); 
					pst.setInt(3, sensor_id ); 
					pst.setInt(4, time_id );
					
					Random rand = new Random();

					emoMap.put("angry 6", rand.nextDouble());
					emoMap.put("delight 7", rand.nextDouble());
					emoMap.put("fear 8", rand.nextDouble());
					emoMap.put("happy 9", rand.nextDouble());
					
					if( sensor_id == 1 ){ // video (angry,delight,fear,happy,surprise,disgust)
						
						emoMap.put("surprise 10", rand.nextDouble());
						emoMap.put("disgust 11", rand.nextDouble());
						emoMap.put("bored 12", 0.0 );
						emoMap.put("neutral 13", 0.0 );
						
					}else if( sensor_id == 2 ){ //audio (angry,delight,fear,happy,bored,neutral)
						
						emoMap.put("surprise 10", 0.0 );
						emoMap.put("disgust 11", 0.0 );
						emoMap.put("bored 12", rand.nextDouble());
						emoMap.put("neutral 13", rand.nextDouble());
						
					}else{ // bio (angry,delight,fear,happy)
						emoMap.put("surprise 10", 0.0 );
						emoMap.put("disgust 11", 0.0 );
						emoMap.put("bored 12", 0.0 );
						emoMap.put("neutral 13", 0.0 );
					}
					
					Map<String, Double> emoMapNorm = DBFunction.getNorm(emoMap);
					Map<String, Double> emoMapNormSort = DBFunction.SortMap(emoMapNorm);
					
					boolean isFst = true;
					
					Map<String, Double> emoMap2 = new HashMap<String, Double>();
					for(Map.Entry<String, Double> entry : emoMapNormSort.entrySet()){
						String key = entry.getKey();
						double value = entry.getValue();
						String emoStr = key.split(" ")[0];
						String keyIndexStr = key.split(" ")[1];
						emoMap2.put(emoStr, value);
						
						if(isFst){
							pst.setInt(5, emoIndexMap.get(emoStr));
							isFst = false;		
						}
						pst.setDouble(Integer.parseInt(keyIndexStr), value);
					}
					
					pst.executeUpdate();					
					
					
					if( sensor_id == 1 ){ // video
						
						table_Video_Feature.generate(conn, sid_video, 
								emoMap2.get("angry"), 
								emoMap2.get("delight"), 
								emoMap2.get("fear"), 
								emoMap2.get("happy"), 
								emoMap2.get("surprise"), 
								emoMap2.get("disgust"), 
								 id);
						sid_video++;
						
					}else if( sensor_id == 2 ){ // audio
						
						table_Audio_Feature.generate(conn, sid_audio, 
								emoMap2.get("angry"), 
								emoMap2.get("delight"), 
								emoMap2.get("fear"), 
								emoMap2.get("happy"), 
								emoMap2.get("bored"), 
								emoMap2.get("neutral"), 
								 id);
						
						table_MFCCs.generate(conn, sid_audio, sid_audio);
						table_Delta_MFCCs.generate(conn, sid_audio, sid_audio);
						table_Delta_delta_MFCCs.generate(conn, sid_audio, sid_audio);
						
						sid_audio++;
						
					}else{ // bio
						
						double arousal = rand.nextDouble();
						double sentiment = rand.nextDouble();
						
						table_Bio_Feature.generate(conn, sid_bio, 
								arousal, 
								sentiment, 
								emoMap2.get("angry"), 
								emoMap2.get("delight"), 
								emoMap2.get("fear"), 
								emoMap2.get("happy"), 
								 id);
						
						table_ECG.generate(conn, sid_bio, sid_bio);
						table_EEG_left.generate(conn, sid_bio, sid_bio);
						table_EEG_right.generate(conn, sid_bio, sid_bio);
						table_EMG.generate(conn, sid_bio, sid_bio);
						table_EOG.generate(conn, sid_bio, sid_bio);

						sid_bio++;
					}
					id++;
				}
			}
		}
	}
	
}
