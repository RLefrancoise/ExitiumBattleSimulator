package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Config;
import model.Player;
import model.PlayerUpdateMessages;
import patterns.IObserver;

public class BattleStatsPanel extends JPanel implements IObserver<PlayerUpdateMessages> {

	private Player _player;
	private Player _opponent;
	
	private JLabel _atk_label;
	private JLabel _acc_label;
	private JLabel _crit_label;
	
	public BattleStatsPanel(Player player, Player opponent) {
		super();
		
		_player = player;
		_opponent = opponent;
		
		_atk_label = new JLabel("Atk : ");
		_atk_label.setHorizontalAlignment(SwingConstants.CENTER);
		_acc_label = new JLabel("Acc : ");
		_acc_label.setHorizontalAlignment(SwingConstants.CENTER);
		_crit_label = new JLabel("Crit : ");
		_crit_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		setLayout(new GridLayout(1,3));
		add(_atk_label);
		add(_acc_label);
		add(_crit_label);
		
		updateStats();
	}
	
	public void updateStats() {
		int player_dex = ((_player.getSpd() + _player.getAtk()) * 2) / 2;
		int atk = _player.getAtk() + _player.getWeapon().getAttack() - (_opponent.getDef() / 2);
		int acc = Config.BASE_ACCURACY_VALUE + _player.getWeapon().getAccuracy() + player_dex - (int) (_opponent.getSpd() * 1.2);
		int crit = Config.BASE_CRITICAL_VALUE + _player.getWeapon().getCritical() + (( _player.getAtk() + _player.getFlux() ) / 2) - ( (_opponent.getRes() + _opponent.getDef()) / 2 );
		crit = crit / 2;
		
		if(atk < 0) atk = 0;
		if(acc < 0) acc = 0;
		if(crit < 0) crit = 0;
		if(acc > 100) acc = 100;
		if(crit > 100) crit = 100;
		
		_atk_label.setText("Atk : " + atk);
		_acc_label.setText("Acc : " + acc + "%");
		_crit_label.setText("Crit : " + crit + "%");
	}
	
	@Override
	public void update(PlayerUpdateMessages msg) {
		switch(msg) {
			case PV_CHANGED :
				break;
			case PF_CHANGED :
				break;
			case ATK_CHANGED :
			case DEF_CHANGED :
			case RES_CHANGED :
			case SPD_CHANGED :
			case FLUX_CHANGED :
			case GRADE_CHANGED :
				updateStats();
				break;
			case POINTS_CHANGED :
				break;
			default:
				break;
		}
	}

}
