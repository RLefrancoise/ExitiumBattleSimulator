package model.items;

public class Cloth extends SetPart {

	public Cloth(int id, String name, String desc, String iconName, int atk, int def, int res, int spd, int flux) {
		super(id, name, desc, iconName, atk, def, res, spd, flux);
	}

	@Override
	public ItemType getType() {
		return ItemType.CLOTH;
	}
}
