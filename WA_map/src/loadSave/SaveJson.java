package loadSave;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import org.json.JSONWriter;

import island.Island;
import main.Preferences;
/**
 * Create a file to save the map.
 * Must be closed using close();
 * @author Hugo
 *
 */
public class SaveJson {
	private Writer writer;
	private JSONWriter jsonW;
	
	public SaveJson(String file) {
		try {
			writer = new OutputStreamWriter(new FileOutputStream(Preferences.folder + file), "utf-8");
			jsonW = new JSONWriter(this.writer);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save(List<Island> islands){
		jsonW.object().key("islands").array();
		for(Island i : islands){
			jsonW.object().key("island_name").value(i.getName());
			if(i.getRefIsland() == null){
				jsonW.key("is_origin").value(true);
				Point coord = i.getCoord();
				jsonW.key("x").value(coord.getX()).key("y").value(coord.getY());
			} else {
				jsonW.key("ref_island").value(i.getRefIsland().getName());
				jsonW.key("angle").value(i.getAngle()).key("distance").value(i.getDistance());
				jsonW.key("is_origin").value(false);
			}
			jsonW.key("size").value((long)i.getIslandSize());
			jsonW.key("color").value(i.getIslandColor());
			jsonW.endObject();
			//System.out.println("Island "+ i +"saved");
		}
		jsonW.endArray().endObject();
		
	}
	
	public void close(){
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
