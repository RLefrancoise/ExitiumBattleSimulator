package model.items;

public class Set {
	
	private int _id;
	private String _name;
	private String _desc;
	
	private int _clothId;
	private int _leggingsId;
	private int _glovesId;
	private int _shoesId;
	
	private int _pv;
	private int _pf;
	private int _atk;
	private int _def;
	private int _res;
	private int _spd;
	private int _flux;
	
	public Set(int id, String name, String desc, int clothId, int leggingsId, int glovesId, int shoesId,
			int pv, int pf, int atk, int def, int res, int spd, int flux) {
		_id = id;
		_name = name;
		_desc = new String(desc);
		
		_clothId = clothId;
		_leggingsId = leggingsId;
		_glovesId = glovesId;
		_shoesId = shoesId;
		
		_pv = pv;
		_pf = pf;
		_atk = atk;
		_def = def;
		_res = res;
		_spd = spd;
		_flux = flux;
	}
	
	public int getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getDesc() {
		return _desc;
	}
	
	public int getClothId() {
		return _clothId;
	}
	
	public int getLeggingsId() {
		return _leggingsId;
	}
	
	public int getGlovesId() {
		return _glovesId;
	}
	
	public int getShoesId() {
		return _shoesId;
	}
	
	public int getPV() {
		return _pv;
	}
	
	public int getPF() {
		return _pf;
	}
	
	public int getAttack() {
		return _atk;
	}
	
	public int getDefense() {
		return _def;
	}
	
	public int getResistance() {
		return _res;
	}
	
	public int getSpeed() {
		return _spd;
	}
	
	public int getFlux() {
		return _flux;
	}
	
}
