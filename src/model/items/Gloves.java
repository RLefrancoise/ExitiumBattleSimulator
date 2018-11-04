package model.items;

public class Gloves extends SetPart {

	public Gloves(int id, String name, String desc, String iconName, int atk, int def, int res, int spd, int flux) {
		super(id, name, desc, iconName, atk, def, res , spd, flux);
	}

	@Override
	public ItemType getType() {
		return ItemType.GLOVES;
	}

}
