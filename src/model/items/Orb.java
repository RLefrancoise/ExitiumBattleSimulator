package model.items;

import model.Config;

public class Orb extends AbstractItem {

	public enum OrbType {
		ATTACK, DEFENSE, RESISTANCE, SPEED, FLUX, PV, PF
	}
	
	public enum OrbLevel {
		LEVEL1(Config.ORB_LVL1_MULTIPLIER), LEVEL2(Config.ORB_LVL2_MULTIPLIER), LEVEL3(Config.ORB_LVL3_MULTIPLIER);
		
		float _multiplier;
		
		OrbLevel(float multiplier) {
			_multiplier = multiplier;
		}
		
		public float getMultiplier() {
			return _multiplier;
		}
	}
	
	private OrbType _type;
	private OrbLevel _level;
	
	public Orb(int id, String name, String desc, String iconName, OrbType type, OrbLevel level) {
		super(id, name, desc, iconName);
		_type = type;
		_level = level;
	}

	public OrbType getOrbType() {
		return _type;
	}
	
	public OrbLevel getLevel() {
		return _level;
	}
	
	@Override
	public ItemType getType() {
		return ItemType.ORB;
	}

}
