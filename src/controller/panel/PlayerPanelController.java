package controller.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import view.PlayerPanel;
import model.Creature.CreatureStats;
import model.PlayerWeapon;
import controller.PlayerController;

public class PlayerPanelController implements ActionListener {

	private PlayerPanel _panel;
	private PlayerController _controller;
	
	public PlayerPanelController(PlayerPanel panel) {
		_panel = panel;
		_controller = _panel.getPlayer().getController();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		//grade has changed
		if(source == _panel.getGradesList()) {
			@SuppressWarnings("unchecked")
			JComboBox<PlayerWeapon.WeaponGrade> list = (JComboBox<PlayerWeapon.WeaponGrade>) source;
			PlayerWeapon.WeaponGrade grade = (PlayerWeapon.WeaponGrade) list.getSelectedItem();
			_controller.setWeaponGrade(grade);
			return;
		}
		
		String c = e.getActionCommand();
		
		//set level button
		if(c.equals("Set Level")) {
			_panel.getPlayer().setLevel(_panel.getLevel());
			return;
		}
		
		//set equipment
		if(c.equals("Set Equipment")) {
			_panel.showEquipmentWindow();
			return;
		}
		
		//set orbs
		if(c.equals("Set Orbs")) {
			_panel.showOrbsWindow();
			return;
		}
		
		//atk button
		if(c.equals("Atk+1")) {
			_controller.givePoints(CreatureStats.ATTACK, 1);
		} else if(c.equals("Atk-1")) {
			_controller.givePoints(CreatureStats.ATTACK, -1);
		} else if(c.equals("Atk+10")) {
			_controller.givePoints(CreatureStats.ATTACK, 10);
		} else if(c.equals("Atk-10")) {
			_controller.givePoints(CreatureStats.ATTACK, -10);
		}
		//def button
		else if(c.equals("Def+1")) {
			_controller.givePoints(CreatureStats.DEFENSE, 1);
		} else if(c.equals("Def-1")) {
			_controller.givePoints(CreatureStats.DEFENSE, -1);
		} else if(c.equals("Def+10")) {
			_controller.givePoints(CreatureStats.DEFENSE, 10);
		} else if(c.equals("Def-10")) {
			_controller.givePoints(CreatureStats.DEFENSE, -10);
		}
		//res button
		else if(c.equals("Res+1")) {
			_controller.givePoints(CreatureStats.RESISTANCE, 1);
		} else if(c.equals("Res-1")) {
			_controller.givePoints(CreatureStats.RESISTANCE, -1);
		} else if(c.equals("Res+10")) {
			_controller.givePoints(CreatureStats.RESISTANCE, 10);
		} else if(c.equals("Res-10")) {
			_controller.givePoints(CreatureStats.RESISTANCE, -10);
		}
		//spd button
		else if(c.equals("Spd+1")) {
			_controller.givePoints(CreatureStats.SPEED, 1);
		} else if(c.equals("Spd-1")) {
			_controller.givePoints(CreatureStats.SPEED, -1);
		} else if(c.equals("Spd+10")) {
			_controller.givePoints(CreatureStats.SPEED, 10);
		} else if(c.equals("Spd-10")) {
			_controller.givePoints(CreatureStats.SPEED, -10);
		}
		//flux button
		else if(c.equals("Flux+1")) {
			_controller.givePoints(CreatureStats.FLUX, 1);
		} else if(c.equals("Flux-1")) {
			_controller.givePoints(CreatureStats.FLUX, -1);
		} else if(c.equals("Flux+10")) {
			_controller.givePoints(CreatureStats.FLUX, 10);
		} else if(c.equals("Flux-10")) {
			_controller.givePoints(CreatureStats.FLUX, -10);
		}
	}

}
