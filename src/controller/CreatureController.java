package controller;

import model.Creature;

public abstract class CreatureController {

	protected Creature _creature;
	
	protected CreatureController(Creature creature) {
		_creature = creature;
	}
	
	public Creature getCreature() {
		return _creature;
	}
	
	public void damageCreature(int damage) {
		_creature.setPV(_creature.getPV() - damage);
	}
	
	public void increaseLevel() {
		_creature.setLevel(_creature.getLevel() + 1);
	}
	
	public void decreaseLevel() {
		_creature.setLevel(_creature.getLevel() - 1);
	}
}
