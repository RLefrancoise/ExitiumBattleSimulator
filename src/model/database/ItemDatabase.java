package model.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;

import org.json.JSONObject;
import org.json.JSONTokener;

import view.MainFrame;

import model.Config;
import model.items.AbstractItem;
import model.items.Cloth;
import model.items.Gloves;
import model.items.ItemType;
import model.items.Leggings;
import model.items.Orb;
import model.items.Orb.OrbLevel;
import model.items.Orb.OrbType;
import model.items.Shoes;

public class ItemDatabase {
	
	private HashMap<Integer,AbstractItem> _items;
	
	private static ItemDatabase INSTANCE;
	
	private ItemDatabase() {
		_items = new HashMap<Integer, AbstractItem>();
		
		JSONTokener tokener = null;
		try {
			tokener = new JSONTokener(new FileReader(Config.JSON_PATH + "items.json"));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "Le fichier " + Config.JSON_PATH + "items.json" + " n'a pas été trouvé.", "Erreur de chargement", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		JSONObject root = new JSONObject(tokener);
		@SuppressWarnings("unchecked")
		Iterator<String> keys = root.keys();
		while(keys.hasNext()) {
			String id = keys.next();
			int _id = Integer.parseInt(id);
			
			JSONObject item = (JSONObject) root.get(id);
			String itemType = (String) item.get("type");
			addItem(_id, getItemFromType(item, _id, itemType));
			//_items.put(_id, getItemFromType(item, _id, itemType));
		}
		
		System.out.println(_items);
	}
	
	private void addItem(int id, AbstractItem item) {
		if(_items.containsKey(id)) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "ID number " + id + " is already used. Please check items.json", "Loading Error", JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException("ID number " + id + " is already used. Please check items.json");
		}
		_items.put(id, item);
	}
	
	private AbstractItem getItemFromType(JSONObject item, int id, String itemType) {
		String name = item.has("name") ? (String) item.get("name") : "";
		String desc = item.has("desc") ? (String) item.get("desc") : "";
		String img = item.has("img") ? (String) item.get("img") : "";
		int atk = item.has("atk") ? (Integer) item.get("atk") : 0;
		int def = item.has("def") ? (Integer) item.get("def") : 0;
		int res = item.has("res") ? (Integer) item.get("res") : 0;
		int spd = item.has("spd") ? (Integer) item.get("spd") : 0;
		int flux = item.has("flux") ? (Integer) item.get("flux") : 0;
		
		// Cloth
		if(itemType.equals("cloth")) {
			Cloth cloth = new Cloth(id, name, desc, img, atk, def, res, spd, flux);
			return cloth;
		// Leggings
		} else if(itemType.equals("leggings")) {
			Leggings leggings = new Leggings(id, name, desc, img, atk, def, res, spd, flux);
			return leggings;
		// Gloves
		} else if(itemType.equals("gloves")) {
			Gloves gloves = new Gloves(id, name, desc, img, atk, def, res, spd, flux);
			return gloves;
		// Shoes
		} else if(itemType.equals("shoes")) {
			Shoes shoes = new Shoes(id, name, desc, img, atk, def, res, spd, flux);
			return shoes;
		// Orb
		} else if(itemType.equals("orb")) {
			String orbType = item.has("orbType") ? (String) item.get("orbType") : "";
			int orbLevel = item.has("orbLevel") ? (Integer) item.get("orbLevel") : 0;
			
			OrbType type = null;
			OrbLevel level = null;
			
			if(orbType.equals("atk")) type = OrbType.ATTACK;
			else if(orbType.equals("def")) type = OrbType.DEFENSE;
			else if(orbType.equals("spd")) type = OrbType.SPEED;
			else if(orbType.equals("flux")) type = OrbType.FLUX;
			else if(orbType.equals("pv")) type = OrbType.PV;
			else if(orbType.equals("pf")) type = OrbType.PF;
			
			if(orbLevel == 1) level = OrbLevel.LEVEL1;
			else if(orbLevel == 2) level = OrbLevel.LEVEL2;
			else if(orbLevel == 3) level = OrbLevel.LEVEL3;
			
			Orb orb = new Orb(id, name, desc, img, type, level);
			return orb;
		}
		
		return null;
	}
	
	public static synchronized ItemDatabase getInstance() {
		if(INSTANCE == null) 
			INSTANCE = new ItemDatabase();
		
		return INSTANCE;
	}
	
	public AbstractItem getItem(int id) {
		return _items.get(id);
	}
	
	public ArrayList<AbstractItem> getItemsByType(ItemType type) {
		ArrayList<AbstractItem> list = new ArrayList<AbstractItem>();
		
		Set<Integer> ids = _items.keySet();
		
		for(int id : ids) {
			AbstractItem item = _items.get(id);
			if(item.getType() == type) list.add(item);
		}
		
		Collections.sort(list);
		return list;
	}
}
