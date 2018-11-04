package model;

public class PlayerWeapon {
	public enum WeaponGrade {
		D(10, 0, 0), C(20, 5, 0), B(30, 5, 5), A(40, 10, 10), S(50, 10, 15), SS(60, 15, 20);
		
		int _attack;
		int _accuracy;
		int _critical;
		
		WeaponGrade(int attack, int accuracy, int critical) {
			_attack = attack;
			_accuracy = accuracy;
			_critical = critical;
		}
		
		public int getAttack() {
			return _attack;
		}
		
		public int getAccuracy() {
			return _accuracy;
		}
		
		public int getCritical() {
			return _critical;
		}
	}
	
	protected WeaponGrade _grade;
	
	public PlayerWeapon() {
		_grade = WeaponGrade.D;
	}
	
	public PlayerWeapon(WeaponGrade grade) {
		_grade = grade;
	}
	
	public WeaponGrade getGrade() {
		return _grade;
	}
	
	public int getAttack() {
		return _grade.getAttack();
	}
	
	public int getAccuracy() {
		return _grade.getAccuracy();
	}
	
	public int getCritical() {
		return _grade.getCritical();
	}
	
	/*public void setGrade(WeaponGrade grade) {
		_grade = grade;
	}*/

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlayerWeapon [_grade=" + _grade + "]";
	}
}
