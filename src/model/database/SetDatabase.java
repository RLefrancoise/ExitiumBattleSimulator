package model.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;

import model.Config;

import org.json.JSONObject;
import org.json.JSONTokener;

import view.MainFrame;

public class SetDatabase {
	
	private HashMap<Integer,model.items.Set> _sets;
	
	private static SetDatabase INSTANCE;
	
	private SetDatabase() {
		_sets = new HashMap<Integer, model.items.Set>();
		
		JSONTokener tokener = null;
		try {
			tokener = new JSONTokener(new FileReader(Config.JSON_PATH + "sets.json"));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "Le fichier " + Config.JSON_PATH + "sets.json" + " n'a pas été trouvé.", "Erreur de chargement", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		JSONObject root = new JSONObject(tokener);
		@SuppressWarnings("unchecked")
		Iterator<String> keys = root.keys();
		while(keys.hasNext()) {
			String id = keys.next();
			int _id = Integer.parseInt(id);
			
			JSONObject set = (JSONObject) root.get(id);
			
			//get set info
			String name = set.has("name") ? (String) set.get("name") : "";
			String desc = set.has("desc") ? (String) set.get("desc") : "";
			int cloth_id = set.has("cloth_id") ? (Integer) set.get("cloth_id") : 0;
			int leggings_id = set.has("leggings_id") ? (Integer) set.get("leggings_id") : 0;
			int gloves_id = set.has("gloves_id") ? (Integer) set.get("gloves_id") : 0;
			int shoes_id = set.has("shoes_id") ? (Integer) set.get("shoes_id") : 0;
			int pv = set.has("pv") ? (Integer) set.get("pv") : 0;
			int pf = set.has("pf") ? (Integer) set.get("pf") : 0;
			int atk = set.has("atk") ? (Integer) set.get("atk") : 0;
			int def = set.has("def") ? (Integer) set.get("def") : 0;
			int res = set.has("res") ? (Integer) set.get("res") : 0;
			int spd = set.has("spd") ? (Integer) set.get("spd") : 0;
			int flux = set.has("flux") ? (Integer) set.get("flux") : 0;
			
			model.items.Set s = new model.items.Set(_id, name, desc, cloth_id, leggings_id, gloves_id, shoes_id, pv, pf, atk, def, res, spd, flux);
			addSet(_id, s);
		}
		
		System.out.println(_sets);
	}
	
	private void addSet(int id, model.items.Set set) {
		if(_sets.containsKey(id)) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "ID number " + id + " is already used. Please check sets.json", "Loading Error", JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException("ID number " + id + " is already used. Please check sets.json");
		}
		_sets.put(id, set);
	}
	
	public model.items.Set getSet(int id) {
		return _sets.get(id);
	}
	
	public model.items.Set getSet(int clothId, int leggingsId, int glovesId, int shoesId) {
		Set<Integer> ids = _sets.keySet();
		
		for(Integer id : ids) {
			model.items.Set s = _sets.get(id);
			if(s.getClothId() == clothId && s.getLeggingsId() == leggingsId
				&& s.getGlovesId() == glovesId && s.getShoesId() == shoesId)
					return s;
		}
		
		return null;
	}
	
	public static synchronized SetDatabase getInstance() {
		if(INSTANCE == null) 
			INSTANCE = new SetDatabase();
		
		return INSTANCE;
	}
}
