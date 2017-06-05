package loadSave;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import island.Island;
import main.Preferences;
import map.WAMap;

public class LoadJson {
	private JSONObject jsonObj;
	
	public LoadJson(String file) {
		try {
			FileInputStream input = new FileInputStream(Preferences.folder + file);
			JSONTokener token = new JSONTokener(input);
			jsonObj = new JSONObject(token);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void load(WAMap map){
		JSONArray islands = jsonObj.getJSONArray("islands");
		JSONObject obj;
		Island isl,ref;
		for(int i = 0; i<islands.length(); i++){
			isl = null;
			obj = islands.getJSONObject(i);
			if(obj.getBoolean("is_origin")){
				isl = new Island(obj.getString("island_name"), obj.getDouble("x"), obj.getDouble("y"));
			} else {
				ref = map.getIslandByName(obj.getString("ref_island"));
				if(ref != null){
					isl = new Island(obj.getString("island_name"), ref, obj.getDouble("angle"), obj.getDouble("distance"));
				} else {
					System.err.println("WARN : cannot find island \"" + obj.getString("ref_island") + "\" needed for \""
							+ obj.getString("island_name") + "\" !");
				}
			}
			if(isl != null){
				isl.setIslandSize(obj.getInt("size"));
				isl.setColor(obj.getString("color"));
				map.addIslandToMap(isl);
			}
		}
	}
	
	

}
