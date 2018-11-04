package model;

import model.Config;

public abstract class Creature {
	
	public enum CreatureStats {
		ATTACK, DEFENSE, RESISTANCE, SPEED, FLUX
	}
	
	protected int _level;
	protected int _pv;
	protected int _attack;
	protected int _defense;
	protected int _resistance;
	protected int _speed;
	protected int _flux;
	
	protected Creature() {
		_level		= 1;
		_pv 		= Config.DEFAULT_PV;
		_attack 	= Config.DEFAULT_ATK;
		_defense 	= Config.DEFAULT_DEF;
		_resistance = Config.DEFAULT_RES;
		_speed 		= Config.DEFAULT_SPD;
		_flux 		= Config.DEFAULT_FLUX;
	}
	
	public int getLevel() {
		return _level;
	}
	
	public int getPV() {
		return _pv;
	}
	
	public int getMaxPV() {
		return Config.DEFAULT_PV + (getDef() - Config.DEFAULT_DEF) * Config.PV_PER_DEF_POINT;
	}
	
	public int getAtk() {
		return _attack;
	}
	
	public int getDef() {
		return _defense;
	}
	
	public int getRes() {
		return _resistance;
	}
	
	public int getSpd() {
		return _speed;
	}
	
	public int getFlux() {
		return _flux;
	}
	
	public void setLevel(int level) {
		_level = level;
		if(_level < 1) _level = 1;
		if(_level > Config.MAX_LEVEL) _level = Config.MAX_LEVEL;
	}
	
	public void setPV(int pv) {
		_pv = pv;
		if(_pv <= 0) _pv = 1;
		if(_pv > getMaxPV()) _pv = getMaxPV();
	}
	
	public void setAtk(int atk) {
		_attack = atk;
		if(_attack <= 0) _attack = 1;
	}
	
	public void setDef(int def) {
		_defense = def;
		if(_defense <= 0) _defense = 1;
	}
	
	public void setRes(int res) {
		_resistance = res;
		if(_resistance <= 0) _resistance = 1;
	}
	
	public void setSpd(int spd) {
		_speed = spd;
		if(_speed <= 0) _speed = 1;
	}
	
	public void setFlux(int flux) {
		_flux = flux;
		if(_flux >= 0) _flux = 1;
	}
}
