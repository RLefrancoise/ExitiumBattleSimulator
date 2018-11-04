package view;

import info.clearthought.layout.TableLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;

import model.Player;
import model.PlayerUpdateMessages;

import patterns.IObserver;

/**
 * Interface graphique principal de l'application
 * 
 * @author Erwan FROC
 * @version 1.0 
 * @date 18/04/2013
 */

public class MainPanel extends JPanel {
	
	private class StatsPanel extends JPanel implements IObserver<PlayerUpdateMessages> {

		private BattleStatsPanel _p1;
		private BattleStatsPanel _p2;
		
		public StatsPanel(BattleStatsPanel p1, BattleStatsPanel p2) {
			super();
			_p1 = p1;
			_p2 = p2;
			
			setLayout(new GridLayout(2,1));
			add(_p1);
			add(_p2);
		}
		
		@Override
		public void update(PlayerUpdateMessages msg) {
			_p1.updateStats();
			_p2.updateStats();
		}
		
	}
	
	private Player _player1;
	private Player _player2;
	
	private PlayerPanel _playerPanel1;
	private PlayerPanel _playerPanel2;
	
	private BattleStatsPanel _statsPanel1;
	private BattleStatsPanel _statsPanel2;
	
	/**
	 * Constructeur qui créer la fenêtre de l'application
	 * @param titre - Le titre de la fenêtre
	 */
	public MainPanel(){
		super();
		setBackground(new Color(200,200,255));
		
		this.init();
		this.creerInterface();
	}
	
	/**
	 * Initialise les éléments graphique
	 */
	public void init(){
		_player1 = new Player();
		_player2 = new Player();
		
		_playerPanel1 = new PlayerPanel("Joueur 1", _player1);
		_playerPanel2 = new PlayerPanel("Joueur 2", _player2);
		_player1.addObserver(_playerPanel1);
		_player2.addObserver(_playerPanel2);
		
		_statsPanel1 = new BattleStatsPanel(_player1, _player2);
		_statsPanel2 = new BattleStatsPanel(_player2, _player1);
		_player1.addObserver(_statsPanel1);
		_player2.addObserver(_statsPanel2);
		
	}
	
	/**
	 * Cree l'interface de l'application
	 */
	private void creerInterface(){
		
		double[][] dim = { {0.05, 0.2, 0.5, 0.2, 0.05}, {0.4, 0.2, 0.4} };
		this.setLayout(new TableLayout(dim));
		
		StatsPanel p = new StatsPanel(_statsPanel1, _statsPanel2);
		_player1.addObserver(p);
		_player2.addObserver(p);
		
		PlayerLifeBarPanel plbp1 = new PlayerLifeBarPanel(_player1);
		PlayerLifeBarPanel plbp2 = new PlayerLifeBarPanel(_player2);
		_player1.addObserver(plbp1);
		_player2.addObserver(plbp2);
		
		this.add(_playerPanel1, "0,0, 4,0");
		this.add(plbp1, "1,1");
		this.add(p, "2,1");
		this.add(plbp2, "3,1");
		this.add(_playerPanel2, "0,2, 4,2");
	}
}
