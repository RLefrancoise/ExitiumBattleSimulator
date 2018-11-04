package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import patterns.IObservable;
import patterns.IObserver;

import controller.PlayerController;
import model.PlayerWeapon;
import model.database.SetDatabase;
import model.items.Cloth;
import model.items.Gloves;
import model.items.Leggings;
import model.items.Orb;
import model.items.Orb.OrbType;
import model.items.Shoes;

public class Player extends Creature implements IObservable<PlayerUpdateMessages> {
	
	protected int _pf;
	protected int _points;
	protected HashMap<CreatureStats,Integer> _points_per_stats;
	protected boolean _is_leader;
	protected PlayerWeapon _weapon;
	protected PlayerController _controller;
	protected Cloth _cloth;
	protected Leggings _leggings;
	protected Gloves _gloves;
	protected Shoes _shoes;
	protected Orb _orbs[];
	
	protected ArrayList<IObserver<PlayerUpdateMessages>> _observers;
	
	public Player() {
		super();
		
		_observers = new ArrayList<IObserver<PlayerUpdateMessages>>();
		
		_pf = Config.DEFAULT_PF;
		
		_points_per_stats = new HashMap<CreatureStats,Integer>();
		_is_leader = false;
		
		resetPoints();
		
		_weapon = new PlayerWeapon();
		_controller = new PlayerController(this);
		
		_cloth = null;
		_leggings = null;
		_gloves = null;
		_shoes = null;
		
		_orbs = new Orb[4];
		for(int i = 0 ; i < _orbs.length ; ++i)
			_orbs[i] = null;
	}
	
	@Override
	public int getMaxPV() {
		
		float orbMultiplier = 0;
		for(Orb o : _orbs) {
			if(o != null && o.getOrbType() == OrbType.PV) {
				orbMultiplier += o.getLevel().getMultiplier();
			}
		}
		
		int base_pv = Config.DEFAULT_PV 
					+ (getSet() != null ? getSet().getPV() : 0) 
					+ ( (getDef() - Config.DEFAULT_DEF ) * Config.PV_PER_DEF_POINT * (isLeader() ? Config.LEADER_STAT_EFFECT_MULTIPLIER : 1) );
		
		return ( base_pv + (int)((float)base_pv * orbMultiplier) ) * (isLeader() ? Config.LEADER_PV_MULTIPLIER : 1);
	}
	
	public int getPF() {
		return _pf;
	}
	
	public int getMaxPF() {
		
		float orbMultiplier = 0;
		for(Orb o : _orbs) {
			if(o != null && o.getOrbType() == OrbType.PF) {
				orbMultiplier += o.getLevel().getMultiplier();
			}
		}
		
		int base_pf = Config.DEFAULT_PF
					+ (getSet() != null ? getSet().getPF() : 0)
					+ (getRes() - Config.DEFAULT_RES) * Config.PF_PER_RES_POINT * (isLeader() ? Config.LEADER_STAT_EFFECT_MULTIPLIER : 1);
		
		return ( base_pf + (int)((float)base_pf * orbMultiplier) ) * (isLeader() ? Config.LEADER_PF_MULTIPLIER : 1);
	}
	
	@Override
	public int getAtk() {
		
		float orbMultiplier = 0;
		for(Orb o : _orbs) {
			if(o != null && o.getOrbType() == OrbType.ATTACK) {
				orbMultiplier += o.getLevel().getMultiplier();
			}
		}
		
		int base_atk = getBaseAtk();
			
			
		return base_atk
		+ (int)((float)base_atk * orbMultiplier)
		+ (_cloth != null ? _cloth.getAttack() : 0)
		+ (_leggings != null ? _leggings.getAttack() : 0)
		+ (_gloves != null ? _gloves.getAttack() : 0)
		+ (_shoes != null ? _shoes.getAttack() : 0)
		+ (getSet() != null ? getSet().getAttack() : 0);
	}
	
	@Override
	public int getDef() {
		
		float orbMultiplier = 0;
		for(Orb o : _orbs) {
			if(o != null && o.getOrbType() == OrbType.DEFENSE) {
				orbMultiplier += o.getLevel().getMultiplier();
			}
		}
		
		int base_def = getBaseDef();
		
		return base_def
			+ (int)((float)base_def * orbMultiplier)
			+ (_cloth != null ? _cloth.getDefense() : 0)
			+ (_leggings != null ? _leggings.getDefense() : 0)
			+ (_gloves != null ? _gloves.getDefense() : 0)
			+ (_shoes != null ? _shoes.getDefense() : 0)
			+ (getSet() != null ? getSet().getDefense() : 0);
	}
	
	@Override
	public int getRes() {
		
		float orbMultiplier = 0;
		for(Orb o : _orbs) {
			if(o != null && o.getOrbType() == OrbType.RESISTANCE) {
				orbMultiplier += o.getLevel().getMultiplier();
			}
		}
		
		int base_res = getBaseRes();
		
		return base_res
			+ (int)((float)base_res * orbMultiplier)
			+ (_cloth != null ? _cloth.getResistance() : 0)
			+ (_leggings != null ? _leggings.getResistance() : 0)
			+ (_gloves != null ? _gloves.getResistance() : 0)
			+ (_shoes != null ? _shoes.getResistance() : 0)
			+ (getSet() != null ? getSet().getResistance() : 0);
	}
	
	@Override
	public int getSpd() {
		
		float orbMultiplier = 0;
		for(Orb o : _orbs) {
			if(o != null && o.getOrbType() == OrbType.SPEED) {
				orbMultiplier += o.getLevel().getMultiplier();
			}
		}
		
		int base_spd = getBaseSpd();
			
		return base_spd
			+ (int)((float)base_spd * orbMultiplier)
			+ (_cloth != null ? _cloth.getSpeed() : 0)
			+ (_leggings != null ? _leggings.getSpeed() : 0)
			+ (_gloves != null ? _gloves.getSpeed() : 0)
			+ (_shoes != null ? _shoes.getSpeed() : 0)
			+ (getSet() != null ? getSet().getSpeed() : 0);
	}
	
	@Override
	public int getFlux() {
		
		float orbMultiplier = 0;
		for(Orb o : _orbs) {
			if(o != null && o.getOrbType() == OrbType.FLUX) {
				orbMultiplier += o.getLevel().getMultiplier();
			}
		}
		
		int base_flux = getBaseFlux();
			
		return base_flux
			+ (int)((float)base_flux * orbMultiplier)
			+ (_cloth != null ? _cloth.getFlux() : 0)
			+ (_leggings != null ? _leggings.getFlux() : 0)
			+ (_gloves != null ? _gloves.getFlux() : 0)
			+ (_shoes != null ? _shoes.getFlux() : 0)
			+ (getSet() != null ? getSet().getFlux() : 0);
	}
	
	public int getBaseAtk() {
		return super.getAtk() 
		+ getPointsOfStat(CreatureStats.ATTACK) * Config.ATK_PER_POINT * (isLeader() ? Config.LEADER_STAT_EFFECT_MULTIPLIER : 1);
	}
	
	public int getBaseDef() {
		return super.getDef() 
		+ getPointsOfStat(CreatureStats.DEFENSE) * Config.DEF_PER_POINT * (isLeader() ? Config.LEADER_STAT_EFFECT_MULTIPLIER : 1);
	}
	
	public int getBaseRes() {
		return super.getRes() 
		+ getPointsOfStat(CreatureStats.RESISTANCE) * Config.RES_PER_POINT * (isLeader() ? Config.LEADER_STAT_EFFECT_MULTIPLIER : 1);
	}
	
	public int getBaseSpd() {
		return super.getSpd() 
		+ getPointsOfStat(CreatureStats.SPEED) * Config.SPD_PER_POINT * (isLeader() ? Config.LEADER_STAT_EFFECT_MULTIPLIER : 1);
	}
	
	public int getBaseFlux() {
		return super.getFlux() 
		+ getPointsOfStat(CreatureStats.FLUX) * Config.FLUX_PER_POINT * (isLeader() ? Config.LEADER_STAT_EFFECT_MULTIPLIER : 1);
	}
	
	public int getPoints() {
		//updatePoints();
		return _points;
	}
	
	public boolean isLeader() {
		return _is_leader;
	}
	
	public PlayerWeapon getWeapon() {
		return _weapon;
	}
	
	public PlayerController getController() {
		return _controller;
	}
	
	public Cloth getCloth() {
		return _cloth;
	}
	
	public Leggings getLeggings() {
		return _leggings;
	}
	
	public Gloves getGloves() {
		return _gloves;
	}
	
	public Shoes getShoes() {
		return _shoes;
	}
	
	public model.items.Set getSet() {
		if(_cloth == null || _leggings == null || _gloves == null || _shoes == null) return null;
		return SetDatabase.getInstance().getSet(_cloth.getId(), _leggings.getId(), _gloves.getId(), _shoes.getId());
	}
	
	public Orb getOrb(int slot) {
		if(slot < 1 || slot > 4) throw new IllegalArgumentException("Invalid slot " + slot);
		return _orbs[slot-1];
	}
	
	public void setLevel(int level) {
		super.setLevel(level);
		resetPoints();
	}
	
	public void setPF(int pf) {
		_pf = pf;
		if(_pf <= 0) _pf = 1;
		if(_pf > getMaxPF()) _pf = getMaxPF();
	}
	
	public void setLeader(boolean leader) {
		_is_leader = leader;
		resetPoints();
		notifyObservers(PlayerUpdateMessages.LEADER_CHANGED);
	}
	
	public void setWeapon(PlayerWeapon weapon) {
		_weapon = weapon;
		notifyObservers(PlayerUpdateMessages.GRADE_CHANGED);
	}
	
	public void setCloth(Cloth cloth) {
		_cloth = cloth;
		notifyObservers(PlayerUpdateMessages.EQUIPMENT_CHANGED);
	}
	
	public void setLeggings(Leggings leggings) {
		_leggings = leggings;
		notifyObservers(PlayerUpdateMessages.EQUIPMENT_CHANGED);
	}
	
	public void setGloves(Gloves gloves) {
		_gloves = gloves;
		notifyObservers(PlayerUpdateMessages.EQUIPMENT_CHANGED);
	}
	
	public void setShoes(Shoes shoes) {
		_shoes = shoes;
		notifyObservers(PlayerUpdateMessages.EQUIPMENT_CHANGED);
	}
	
	public void setOrb(Orb o, int slot) {
		if(slot < 1 || slot > 4) throw new IllegalArgumentException("Invalid slot " + slot);
		_orbs[slot-1] = o;
		
		notifyObservers(PlayerUpdateMessages.ORB_CHANGED);
	}
	
	public void updatePoints() {
		int level_points = _level * Config.POINTS_PER_LEVEL;
		//if(isLeader()) level_points *= Config.LEADER_POINTS_MULTIPLIER;
		int given_points = 0;
		
		Set<CreatureStats> k = _points_per_stats.keySet();
		for(CreatureStats cs : k) {
			given_points += _points_per_stats.get(cs);
		}
		
		level_points -= given_points;
		_points = level_points;
		
		notifyObservers(PlayerUpdateMessages.POINTS_CHANGED);
		notifyObservers(PlayerUpdateMessages.ATK_CHANGED);
		notifyObservers(PlayerUpdateMessages.DEF_CHANGED);
		notifyObservers(PlayerUpdateMessages.SPD_CHANGED);
		notifyObservers(PlayerUpdateMessages.FLUX_CHANGED);
		notifyObservers(PlayerUpdateMessages.RES_CHANGED);
	}
	
	public void resetPoints() {
		_points = _level * Config.POINTS_PER_LEVEL;
		//if(isLeader()) _points *= Config.LEADER_POINTS_MULTIPLIER;
		
		_points_per_stats.put(CreatureStats.ATTACK, 0);
		_points_per_stats.put(CreatureStats.DEFENSE, 0);
		_points_per_stats.put(CreatureStats.RESISTANCE, 0);
		_points_per_stats.put(CreatureStats.SPEED, 0);
		_points_per_stats.put(CreatureStats.FLUX, 0);
		
		updatePoints();
	}
	
	public int getPointsOfStat(CreatureStats stat) {
		return _points_per_stats.get(stat);
	}
	
	public void setPointsOfStat(CreatureStats stat, int points) {
		
		int old_points = getPointsOfStat(stat);
		_points_per_stats.put(stat, points);
		
		switch(stat) {
			case ATTACK:
				if(getBaseAtk() > Config.STAT_MAX_CAPACITY) {
					_points_per_stats.put(stat, old_points);
					setPointsOfStat(stat, points - 1);
				}
				break;
			case DEFENSE:
				if(getBaseDef() > Config.STAT_MAX_CAPACITY) {
					_points_per_stats.put(stat, old_points);
					setPointsOfStat(stat, points - 1);
				}
				break;
			case RESISTANCE:
				if(getBaseRes() > Config.STAT_MAX_CAPACITY) {
					_points_per_stats.put(stat, old_points);
					setPointsOfStat(stat, points - 1);
				}
				break;
			case SPEED:
				if(getBaseSpd() > Config.STAT_MAX_CAPACITY) {
					_points_per_stats.put(stat, old_points);
					setPointsOfStat(stat, points - 1);
				}
				break;
			case FLUX:
				if(getBaseFlux() > Config.STAT_MAX_CAPACITY) {
					_points_per_stats.put(stat, old_points);
					setPointsOfStat(stat, points - 1);
				}
				break;
		}
		
		updatePoints();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return 	"Niveau : " + getLevel() + '\n'
			+	"PV : " + getPV() + '/' + getMaxPV() + '\n'
			+	"PF : " + getPF() + '/' + getMaxPF() + '\n'
			+	"Attaque : " + getAtk() + '[' + Config.DEFAULT_ATK + '+' + getPointsOfStat(CreatureStats.ATTACK) + '*' + Config.ATK_PER_POINT + ']' + '\n'
			+	"Défense : " + getDef() + '[' + Config.DEFAULT_DEF + '+' + getPointsOfStat(CreatureStats.DEFENSE) + '*' + Config.DEF_PER_POINT + ']' + '\n'
			+	"Résistance : " + getRes() + '[' + Config.DEFAULT_RES + '+' + getPointsOfStat(CreatureStats.RESISTANCE) + '*' + Config.RES_PER_POINT + ']' + '\n'
			+	"Vitesse : " + getSpd() + '[' + Config.DEFAULT_SPD + '+' + getPointsOfStat(CreatureStats.SPEED) + '*' + Config.SPD_PER_POINT + ']' + '\n'
			+	"Flux : " 	 + getFlux() + '[' + Config.DEFAULT_FLUX + '+' + getPointsOfStat(CreatureStats.FLUX) + '*' + Config.FLUX_PER_POINT + ']' + '\n'
			+	"Points : "	 + getPoints() + '\n'
			+	"Grade arme : " + getWeapon().getGrade()
			;
		
		/*return "Player [_pf=" + _pf + ", _points=" + _points
				+ ", _points_per_stats=" + _points_per_stats + ", _is_leader="
				+ _is_leader + ", _weapon=" + _weapon + ", _level=" + _level
				+ ", _pv=" + _pv + ", _attack=" + _attack + ", _defense="
				+ _defense + ", _speed=" + _speed + ", _flux=" + _flux + "]";*/
	}

	@Override
	public void notifyObservers(PlayerUpdateMessages msg) {
		for(IObserver<PlayerUpdateMessages> o : _observers) {
			o.update(msg);
		}
	}

	@Override
	public void addObserver(IObserver<PlayerUpdateMessages> o) {
		if(_observers.contains(o)) return;
		_observers.add(o);
		
	}

	@Override
	public void removeObserver(IObserver<PlayerUpdateMessages> o) {
		if(!_observers.contains(o)) return;
		_observers.remove(o);
		
	}
	
}
