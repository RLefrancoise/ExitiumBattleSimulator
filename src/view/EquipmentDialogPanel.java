package view;

import info.clearthought.layout.TableLayout;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Player;
import model.database.ItemDatabase;
import model.database.SetDatabase;
import model.items.AbstractItem;
import model.items.Cloth;
import model.items.Gloves;
import model.items.ItemType;
import model.items.Leggings;
import model.items.Set;
import model.items.Shoes;

public class EquipmentDialogPanel extends JPanel implements ActionListener {

	private class EquipmentImagePanel extends JPanel {
		private BufferedImage _image;
		
		public EquipmentImagePanel(AbstractItem item) {
			_image = item.getImage();
		}
		
		public void setItem(AbstractItem item) {
			_image = item.getImage();
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			if(_image != null)
				g.drawImage(_image, 0, 0, _image.getWidth(this), _image.getHeight(this), this);
				//g.drawImage(_image, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	
	private EquipmentDialog _dialog;
	private PlayerPanel _playerPanel;
	
	private JLabel _set_label;
	
	private JComboBox<Cloth> _clothes;
	private JComboBox<Leggings> _leggings;
	private JComboBox<Gloves> _gloves;
	private JComboBox<Shoes> _shoes;
	
	private EquipmentImagePanel _cloth_img;
	private EquipmentImagePanel _leggings_img;
	private EquipmentImagePanel _gloves_img;
	private EquipmentImagePanel _shoes_img;
	
	private JButton _ok;
	private JButton _cancel;
	private JButton _reset;
	
	public EquipmentDialogPanel(EquipmentDialog dialog, PlayerPanel playerPanel) {
		super();
		
		_dialog = dialog;
		_playerPanel = playerPanel;
		
		_set_label = new JLabel();
		_set_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Clothes
		ArrayList<AbstractItem> clothes = ItemDatabase.getInstance().getItemsByType(ItemType.CLOTH);
		Cloth clothesTab[] = new Cloth[clothes.size()];
		for(int i = 0 ; i < clothes.size() ; i++) {
			clothesTab[i] = (Cloth) clothes.get(i);
		}
		_clothes = new JComboBox<Cloth>(clothesTab);
		_clothes.addActionListener(this);
		
		//Leggings
		ArrayList<AbstractItem> leggings = ItemDatabase.getInstance().getItemsByType(ItemType.LEGGINGS);
		Leggings leggingsTab[] = new Leggings[leggings.size()];
		for(int i = 0 ; i < leggings.size() ; i++) {
			leggingsTab[i] = (Leggings) leggings.get(i);
		}
		_leggings = new JComboBox<Leggings>(leggingsTab);
		_leggings.addActionListener(this);
		
		//Gloves
		ArrayList<AbstractItem> gloves = ItemDatabase.getInstance().getItemsByType(ItemType.GLOVES);
		Gloves glovesTab[] = new Gloves[gloves.size()];
		for(int i = 0 ; i < gloves.size() ; i++) {
			glovesTab[i] = (Gloves) gloves.get(i);
		}
		_gloves = new JComboBox<Gloves>(glovesTab);
		_gloves.addActionListener(this);
		
		//Shoes
		ArrayList<AbstractItem> shoes = ItemDatabase.getInstance().getItemsByType(ItemType.SHOES);
		Shoes shoesTab[] = new Shoes[shoes.size()];
		for(int i = 0 ; i < shoes.size() ; i++) {
			shoesTab[i] = (Shoes) shoes.get(i);
		}
		_shoes = new JComboBox<Shoes>(shoesTab);
		_shoes.addActionListener(this);
		
		// Cloth Image
		_cloth_img = new EquipmentImagePanel((Cloth) _clothes.getSelectedItem());
		
		// Leggings Image
		_leggings_img = new EquipmentImagePanel((Leggings) _leggings.getSelectedItem());
		
		// Gloves Image
		_gloves_img = new EquipmentImagePanel((Gloves) _gloves.getSelectedItem());
		
		// Shoes Image
		_shoes_img = new EquipmentImagePanel((Shoes) _shoes.getSelectedItem());
		
		// OK Button
		_ok = new JButton("OK");
		_ok.addActionListener(this);
		
		// Cancel Button
		_cancel = new JButton("Cancel");
		_cancel.addActionListener(this);
		
		// Reset Button
		_reset = new JButton("Reset");
		_reset.addActionListener(this);
		
		//layout
		double dim[][] = { {0.05, 0.9, 0.05}, {0.05, 0.9, 0.05} };
		setLayout(new TableLayout(dim));
		
		JPanel p = new JPanel();
		double dim2[][] = { {0.025, 0.15, 0.1, 0.55, 0.05, 0.1, 0.025}, {0.05, 0.15, 0.05, 0.15, 0.05, 0.15, 0.05, 0.15, 0.1, 0.1} };
		p.setLayout(new TableLayout(dim2));
		
		p.add(_set_label, "1,0, 5,0");
		p.add(new JLabel("Haut : "), "1,1");
		p.add(_clothes, "3,1");
		p.add(_cloth_img, "5,1");
		p.add(new JLabel("Bas : "), "1,3");
		p.add(_leggings, "3,3");
		p.add(_leggings_img, "5,3");
		p.add(new JLabel("Gants : "), "1,5");
		p.add(_gloves, "3,5");
		p.add(_gloves_img, "5,5");
		p.add(new JLabel("Bottes : "), "1,7");
		p.add(_shoes, "3,7");
		p.add(_shoes_img, "5,7");
		
		JPanel buttons = new JPanel(new GridLayout(1,5));
		buttons.add(_ok);
		buttons.add(new JPanel());
		buttons.add(_cancel);
		buttons.add(new JPanel());
		buttons.add(_reset);
		
		p.add(buttons, "1,9, 5,9");
		
		add(p, "1,1");
		
		checkSet();
	}

	private void checkSet() {
		Cloth cloth = (Cloth) _clothes.getSelectedItem();
		Leggings leggings = (Leggings) _leggings.getSelectedItem();
		Gloves gloves = (Gloves) _gloves.getSelectedItem();
		Shoes shoes = (Shoes) _shoes.getSelectedItem();
		
		Set set = SetDatabase.getInstance().getSet(cloth.getId(), leggings.getId(), gloves.getId(), shoes.getId());
		if(set != null) {
			_set_label.setText(set.getName() + " : " + set.getDesc());
		}
		else {
			_set_label.setText("");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if(source == _clothes) {
			Cloth cloth = (Cloth) _clothes.getSelectedItem();
			_cloth_img.setItem(cloth);
			checkSet();
			return;
		}
		if(source == _leggings) {
			Leggings leggings = (Leggings) _leggings.getSelectedItem();
			_leggings_img.setItem(leggings);
			checkSet();
			return;
		}
		if(source == _gloves) {
			Gloves gloves = (Gloves) _gloves.getSelectedItem();
			_gloves_img.setItem(gloves);
			checkSet();
			return;
		}
		if(source == _shoes) {
			Shoes shoes = (Shoes) _shoes.getSelectedItem();
			_shoes_img.setItem(shoes);
			checkSet();
			return;
		}
		
		
		
		if(e.getActionCommand().equals("OK")) {
			
			Cloth cloth = (Cloth) _clothes.getSelectedItem();
			Leggings leggings = (Leggings) _leggings.getSelectedItem();
			Gloves gloves = (Gloves) _gloves.getSelectedItem();
			Shoes shoes = (Shoes) _shoes.getSelectedItem();
			
			Player player = _playerPanel.getPlayer();
			player.setCloth(cloth);
			player.setLeggings(leggings);
			player.setGloves(gloves);
			player.setShoes(shoes);
			
			_dialog.setVisible(false);
			
		} else if(e.getActionCommand().equals("Cancel")) {
			
			_dialog.setVisible(false);
			
		} else if(e.getActionCommand().equals("Reset")) {
			
			Player player = _playerPanel.getPlayer();
			player.setCloth(null);
			player.setLeggings(null);
			player.setGloves(null);
			player.setShoes(null);
			
			_dialog.setVisible(false);
			
		}
	}
}
