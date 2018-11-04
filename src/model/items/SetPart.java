package model.items;

public abstract class SetPart extends AbstractItem {

	protected int _atk;
	protected int _def;
	protected int _res;
	protected int _spd;
	protected int _flux;
	
	protected SetPart(int id, String name, String desc, String iconName, int atk, int def, int res, int spd, int flux) {
		super(id, name, desc, iconName);
		_atk = atk;
		_def = def;
		_res = res;
		_spd = spd;
		_flux = flux;
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
	
	public abstract ItemType getType();
}
