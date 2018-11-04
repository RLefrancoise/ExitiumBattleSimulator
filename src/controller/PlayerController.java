package controller;

import controller.CreatureController;
import model.Player;
import model.Creature.CreatureStats;
import model.PlayerWeapon;

public class PlayerController extends CreatureController {
	
	public PlayerController(Player player) {
		super(player);
	}
	
	public Player getPlayer() {
		return (Player) getCreature();
	}
	
	public void damagePlayer(int damage) {
		super.damageCreature(damage);
	}
	
	public void increaseLevel() {
		super.increaseLevel();
		getPlayer().resetPoints();
	}
	
	public void decreaseLevel() {
		super.decreaseLevel();
		getPlayer().resetPoints();
	}
	
	public void givePoints(CreatureStats stat, int points) {
		getPlayer().updatePoints();
		
		if(getPlayer().getPoints() < points) {
			points = getPlayer().getPoints();
		}
		
		int current_points = getPlayer().getPointsOfStat(stat);
		int total_points = current_points + points;
		
		if(total_points >= 0)
			getPlayer().setPointsOfStat(stat, total_points);
		else
			getPlayer().setPointsOfStat(stat, 0);
	}
	
	public void setWeaponGrade(PlayerWeapon.WeaponGrade grade) {
		getPlayer().setWeapon(new PlayerWeapon(grade));
	}
}
