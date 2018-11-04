package view;

import info.clearthought.layout.TableLayout;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Config;
import model.Player;
import model.PlayerUpdateMessages;
import model.PlayerWeapon;
import patterns.IObserver;
import controller.panel.PlayerPanelController;

public class PlayerPanel extends JPanel implements IObserver<PlayerUpdateMessages>, ItemListener {

	private Player _player;
	
	private JPanel _atk_buttons;
	private JPanel _def_buttons;
	private JPanel _res_buttons;
	private JPanel _spd_buttons;
	private JPanel _flux_buttons;
	
	private JPanel panelSort1;
	
	private JButton plusUnA;
	private JButton moinsUnA;
	private JButton plusDixA;
	private JButton moinsDixA;
	private JButton plusUnB;
	private JButton moinsUnB;
	private JButton plusDixB;
	private JButton moinsDixB;
	private JButton plusUnC;
	private JButton moinsUnC;
	private JButton plusDixC;
	private JButton moinsDixC;
	private JButton plusUnD;
	private JButton moinsUnD;
	private JButton plusDixD;
	private JButton moinsDixD;
	private JButton plusUnE;
	private JButton moinsUnE;
	private JButton plusDixE;
	private JButton moinsDixE;
	
	private JButton sortRouge1;
	private JButton sortBleu1;
	private JButton sortBlanc1;
	private JButton sortNoir1;
	
	private JTextField txtAttaque1;
	private JTextField txtDefense1;
	private JTextField txtResistance1;
	private JTextField txtVitesse1;
	private JTextField txtFlux1;
	private JTextField txtPoints;
	
	private JComboBox<String> listeBuild1;
	
	private JLabel nomJ1;
	
	private JCheckBox _leaderCheckBox;
	private JTextField _levelField;
	private JButton _levelButton;
	private JComboBox<PlayerWeapon.WeaponGrade> _weaponGradeList;
	private JButton _equipment_button;
	private JButton _orbs_button;
	
	private JLabel lbAttaque1;
	private JLabel lbDefense1;
	private JLabel lbResistance1;
	private JLabel lbVitesse1;
	private JLabel lbFlux1;
	
	private EquipmentDialog _dialog;
	private OrbDialog _orb_dialog;
	
	public PlayerPanel(String name, Player player) {
		super();
		setBackground(new Color(200,200,255));
		
		_player = player;
		PlayerPanelController ppc = new PlayerPanelController(this);
		
		
		double[][] dim = { {0.05, 0.9, 0.05}, {0.05, 0.15, 0.1, 0.1, 0.5, 0.1} };
		this.setLayout(new TableLayout(dim));
		
		//this.panelSort1 = new JPanel();
		
		//name
		this.nomJ1 = new JLabel(name);
		JPanel namePanel = new JPanel();
		namePanel.setAlignmentX(CENTER_ALIGNMENT);
		namePanel.add(nomJ1);
		this.add(namePanel, "1, 1");
		
		//leader check box
		_leaderCheckBox = new JCheckBox("Leader");
		_leaderCheckBox.setSelected(false);
		_leaderCheckBox.addItemListener(this);
		//this.add(_leaderCheckBox, "1, 2");
		
		//level textfield
		_levelField = new JTextField(3);
		_levelField.setText("1");
		
		//level button
		_levelButton = new JButton("Set Level");
		_levelButton.addActionListener(ppc);
		
		//weapon grade list
		JLabel gradeLabel = new JLabel("Grade");
		gradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		PlayerWeapon.WeaponGrade[] grades = {PlayerWeapon.WeaponGrade.D
											, PlayerWeapon.WeaponGrade.C
											, PlayerWeapon.WeaponGrade.B
											, PlayerWeapon.WeaponGrade.A
											, PlayerWeapon.WeaponGrade.S
											, PlayerWeapon.WeaponGrade.SS};
		_weaponGradeList = new JComboBox<PlayerWeapon.WeaponGrade>(grades);
		_weaponGradeList.addActionListener(ppc);
		
		//equipment button
		_equipment_button = new JButton("Set Equipment");
		_equipment_button.addActionListener(ppc);
		
		// orbs button
		_orbs_button = new JButton("Set Orbs");
		_orbs_button.addActionListener(ppc);
		
		JPanel p = new JPanel(new GridLayout(1,7));
		p.add(_leaderCheckBox);
		p.add(_levelField);
		p.add(_levelButton);
		p.add(gradeLabel);
		p.add(_weaponGradeList);
		p.add(_equipment_button);
		p.add(_orbs_button);
		
		this.add(p, "1,2");
		
		//atk buttons
		_atk_buttons = new JPanel(new GridLayout(1,4));
		this.plusUnA = new JButton("+1");
		this.moinsUnA = new JButton("-1");
		this.plusDixA = new JButton("+10");
		this.moinsDixA = new JButton ("-10");
		
		_atk_buttons.add(plusUnA);
		_atk_buttons.add(moinsUnA);
		_atk_buttons.add(plusDixA);
		_atk_buttons.add(moinsDixA);
		
		plusUnA.setActionCommand("Atk+1");
		moinsUnA.setActionCommand("Atk-1");
		plusDixA.setActionCommand("Atk+10");
		moinsDixA.setActionCommand("Atk-10");
		
		plusUnA.addActionListener(ppc);
		moinsUnA.addActionListener(ppc);
		plusDixA.addActionListener(ppc);
		moinsDixA.addActionListener(ppc);
		
		//def buttons
		_def_buttons = new JPanel(new GridLayout(1,4));
		this.plusUnB = new JButton("+1");
		this.moinsUnB = new JButton("-1");
		this.plusDixB = new JButton("+10");
		this.moinsDixB = new JButton ("-10");
		
		_def_buttons.add(plusUnB);
		_def_buttons.add(moinsUnB);
		_def_buttons.add(plusDixB);
		_def_buttons.add(moinsDixB);
		
		plusUnB.setActionCommand("Def+1");
		moinsUnB.setActionCommand("Def-1");
		plusDixB.setActionCommand("Def+10");
		moinsDixB.setActionCommand("Def-10");
		
		plusUnB.addActionListener(ppc);
		moinsUnB.addActionListener(ppc);
		plusDixB.addActionListener(ppc);
		moinsDixB.addActionListener(ppc);
		
		//res buttons
		_res_buttons = new JPanel(new GridLayout(1,4));
		this.plusUnE = new JButton("+1");
		this.moinsUnE = new JButton("-1");
		this.plusDixE = new JButton("+10");
		this.moinsDixE = new JButton ("-10");
		
		_res_buttons.add(plusUnE);
		_res_buttons.add(moinsUnE);
		_res_buttons.add(plusDixE);
		_res_buttons.add(moinsDixE);
		
		plusUnE.setActionCommand("Res+1");
		moinsUnE.setActionCommand("Res-1");
		plusDixE.setActionCommand("Res+10");
		moinsDixE.setActionCommand("Res-10");
		
		plusUnE.addActionListener(ppc);
		moinsUnE.addActionListener(ppc);
		plusDixE.addActionListener(ppc);
		moinsDixE.addActionListener(ppc);
		
		//spd buttons
		_spd_buttons = new JPanel(new GridLayout(1,4));
		this.plusUnC = new JButton("+1");
		this.moinsUnC = new JButton("-1");
		this.plusDixC = new JButton("+10");
		this.moinsDixC = new JButton ("-10");
		
		_spd_buttons.add(plusUnC);
		_spd_buttons.add(moinsUnC);
		_spd_buttons.add(plusDixC);
		_spd_buttons.add(moinsDixC);
		
		plusUnC.setActionCommand("Spd+1");
		moinsUnC.setActionCommand("Spd-1");
		plusDixC.setActionCommand("Spd+10");
		moinsDixC.setActionCommand("Spd-10");
		
		plusUnC.addActionListener(ppc);
		moinsUnC.addActionListener(ppc);
		plusDixC.addActionListener(ppc);
		moinsDixC.addActionListener(ppc);
		
		//flux buttons
		_flux_buttons = new JPanel(new GridLayout(1,4));
		this.plusUnD = new JButton("+1");
		this.moinsUnD = new JButton("-1");
		this.plusDixD = new JButton("+10");
		this.moinsDixD = new JButton ("-10");
		
		_flux_buttons.add(plusUnD);
		_flux_buttons.add(moinsUnD);
		_flux_buttons.add(plusDixD);
		_flux_buttons.add(moinsDixD);
		
		plusUnD.setActionCommand("Flux+1");
		moinsUnD.setActionCommand("Flux-1");
		plusDixD.setActionCommand("Flux+10");
		moinsDixD.setActionCommand("Flux-10");
		
		plusUnD.addActionListener(ppc);
		moinsUnD.addActionListener(ppc);
		plusDixD.addActionListener(ppc);
		moinsDixD.addActionListener(ppc);
		
		//stats
		this.lbAttaque1 = new JLabel("Attaque : ");
		this.lbDefense1 = new JLabel("Défense : ");
		this.lbResistance1 = new JLabel("Résistance : ");
		this.lbVitesse1 = new JLabel("Vitesse : ");
		this.lbFlux1 = new JLabel("Flux : ");
		
		txtAttaque1 = new JTextField(10);
		txtDefense1 = new JTextField(10);
		txtResistance1 = new JTextField(10);
		txtVitesse1 = new JTextField(10);
		txtFlux1 = new JTextField(10);
		txtPoints = new JTextField(10);
		
		txtAttaque1.setEditable(false);
		txtDefense1.setEditable(false);
		txtResistance1.setEditable(false);
		txtVitesse1.setEditable(false);
		txtFlux1.setEditable(false);
		txtPoints.setEditable(false);
		
		txtAttaque1.setText("" + _player.getAtk());
		txtDefense1.setText("" + _player.getDef());
		txtResistance1.setText("" + _player.getRes());
		txtVitesse1.setText("" + _player.getSpd());
		txtFlux1.setText("" + _player.getFlux());
		txtPoints.setText("" + _player.getPoints());
		
		double[][] dim2 = { {0.2, 0.2, 0.1, 0.5}, {TableLayout.FILL} };
		
		//---attack
		JPanel atk = new JPanel(new GridLayout(1,3));
		atk.setLayout(new TableLayout(dim2));
		atk.add(lbAttaque1, "0,0");
		atk.add(txtAttaque1, "1,0");
		atk.add(_atk_buttons, "3,0");
		
		//---defense
		JPanel def = new JPanel(new GridLayout(1,3));
		def.setLayout(new TableLayout(dim2));
		def.add(lbDefense1, "0,0");
		def.add(txtDefense1, "1,0");
		def.add(_def_buttons, "3,0");
		
		//---resistance
		JPanel res = new JPanel(new GridLayout(1,3));
		res.setLayout(new TableLayout(dim2));
		res.add(lbResistance1, "0,0");
		res.add(txtResistance1, "1,0");
		res.add(_res_buttons, "3,0");
		
		//---speed
		JPanel spd = new JPanel(new GridLayout(1,3));
		spd.setLayout(new TableLayout(dim2));
		spd.add(lbVitesse1, "0,0");
		spd.add(txtVitesse1, "1,0");
		spd.add(_spd_buttons, "3,0");
		
		//---flux
		JPanel flux = new JPanel(new GridLayout(1,3));
		flux.setLayout(new TableLayout(dim2));
		flux.add(lbFlux1, "0,0");
		flux.add(txtFlux1, "1,0");
		flux.add(_flux_buttons, "3,0");
		
		//---points
		JPanel points = new JPanel();
		points.setLayout(new TableLayout(dim2));
		points.add(new JLabel("Points : "), "0,0");
		points.add(txtPoints, "1,0");
		
		JPanel statsPanel = new JPanel(new GridLayout(6,1));
		statsPanel.add(atk);
		statsPanel.add(def);
		statsPanel.add(res);
		statsPanel.add(spd);
		statsPanel.add(flux);
		statsPanel.add(points);
		
		this.add(statsPanel, "1,4");
		
		/*this.sortRouge1 = new JButton ("Rouge");
		this.sortBleu1 = new JButton ("Bleu");
		this.sortBlanc1 = new JButton ("Blanc");
		this.sortNoir1 = new JButton ("Noir");
		
		String[] build = {"Tank", "DPS", "HEAL", "SUPPORT"};
		this.listeBuild1 = new JComboBox<String>(build);
		listeBuild1.setPreferredSize(new Dimension(150, 50));*/
	}
	
	@Override
	public void update(PlayerUpdateMessages msg) {
		switch(msg) {
			case PV_CHANGED :
				break;
			case PF_CHANGED :
				break;
			case ATK_CHANGED :
			{
				txtAttaque1.setText("" + _player.getAtk());
			}
				break;
			case DEF_CHANGED :
			{
				txtDefense1.setText("" + _player.getDef());
			}
				break;
			case RES_CHANGED :
			{
				txtResistance1.setText("" + _player.getRes());
			}
				break;
			case SPD_CHANGED :
			{
				txtVitesse1.setText("" + _player.getSpd());
			}
				break;
			case FLUX_CHANGED :
			{
				txtFlux1.setText("" + _player.getFlux());
			}
				break;
			case POINTS_CHANGED :
			{
				txtPoints.setText("" + _player.getPoints());
			}
				break;
			case GRADE_CHANGED:
				break;
			case EQUIPMENT_CHANGED:
			case LEADER_CHANGED:
			case ORB_CHANGED:
				txtAttaque1.setText("" + _player.getAtk());
				txtDefense1.setText("" + _player.getDef());
				txtResistance1.setText("" + _player.getRes());
				txtVitesse1.setText("" + _player.getSpd());
				txtFlux1.setText("" + _player.getFlux());
				break;
			default:
				break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getItemSelectable();
		
		if(source == _leaderCheckBox) {
			if(e.getStateChange() == ItemEvent.SELECTED)
				_player.setLeader(true);
			else
				_player.setLeader(false);
		}
	}
	
	public void showEquipmentWindow() {
		if(_dialog == null) _dialog = new EquipmentDialog(MainFrame.getInstance(), this);
		_dialog.setVisible(true);
	}
	
	public void showOrbsWindow() {
		if(_orb_dialog == null) _orb_dialog = new OrbDialog(MainFrame.getInstance(), this);
		_orb_dialog.setVisible(true);
	}
	
	public Player getPlayer() {
		return _player;
	}
	
	public int getLevel() {
		int level = 1;
		
		try {
			level = Integer.parseInt(_levelField.getText());
		} catch(NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Le level doit être une valeur numérique entre 1 et " + Config.MAX_LEVEL + ".");
		} finally {
			if(level < 1 || level > Config.MAX_LEVEL) {
				JOptionPane.showMessageDialog(this, "Le level doit être une valeur numérique entre 1 et " + Config.MAX_LEVEL + ".");
			}
		}
		
		return level;
	}
	
	public JComboBox<PlayerWeapon.WeaponGrade> getGradesList() {
		return _weaponGradeList;
	}
}
