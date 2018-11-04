package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Player;
import model.PlayerUpdateMessages;
import patterns.IObserver;

public class PlayerLifeBarPanel extends JPanel implements IObserver<PlayerUpdateMessages> {

	private Player _player;
	private JLabel _life_label;
	private JLabel _flux_label;
	
	public PlayerLifeBarPanel(Player player) {
		super(new GridLayout(1,2));
		setBackground(new Color(200,200,255));
		
		_player = player;
	
		_life_label = new JLabel("PV : " + _player.getPV() + "/" + _player.getMaxPV());
		_life_label.setForeground(Color.WHITE);
		_life_label.setHorizontalAlignment(SwingConstants.CENTER);
		add(_life_label);
		
		_flux_label = new JLabel("PF : " + _player.getPF() + "/" + _player.getMaxPF());
		_flux_label.setForeground(Color.WHITE);
		_flux_label.setHorizontalAlignment(SwingConstants.CENTER);
		add(_flux_label);
	}
	
	@Override
	public void update(PlayerUpdateMessages msg) {
		switch(msg) {
			case PV_CHANGED :
			case PF_CHANGED :
			case ATK_CHANGED :
				break;
			case DEF_CHANGED :
				_life_label.setText("PV : " + _player.getPV() + "/" + _player.getMaxPV());
				repaint();
				break;
			case SPD_CHANGED :
			case FLUX_CHANGED :
				_flux_label.setText("PF : " + _player.getPF() + "/" + _player.getMaxPF());
				repaint();
				break;
			case GRADE_CHANGED :
			case POINTS_CHANGED :
				break;
			case EQUIPMENT_CHANGED:
			case LEADER_CHANGED:
			case ORB_CHANGED:
				_life_label.setText("PV : " + _player.getPV() + "/" + _player.getMaxPV());
				_flux_label.setText("PF : " + _player.getPF() + "/" + _player.getMaxPF());
				repaint();
				break;
			default:
				break;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		//PV
		GradientPaint gp = new GradientPaint(0,0, Color.GREEN, 0, this.getHeight(), Color.RED);
		g2d.setPaint(gp);
		float height = ((float)_player.getPV() / (float)_player.getMaxPV()) * this.getHeight();
		g2d.fillRoundRect(0, 0, this.getWidth() / 2, (int) height, 5, 5);
		
		//PF
		gp = new GradientPaint(0,0, Color.BLUE, 0, this.getHeight(), Color.CYAN);
		g2d.setPaint(gp);
		height = ((float)_player.getPF() / (float)_player.getMaxPF()) * this.getHeight();
		g2d.fillRoundRect(this.getWidth() / 2, 0, this.getWidth(), (int) height, 5, 5);
	}

}
