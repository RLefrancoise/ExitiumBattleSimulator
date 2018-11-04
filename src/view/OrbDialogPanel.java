package view;

import info.clearthought.layout.TableLayout;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Player;
import model.database.ItemDatabase;
import model.items.AbstractItem;
import model.items.Cloth;
import model.items.Gloves;
import model.items.ItemType;
import model.items.Leggings;
import model.items.Orb;
import model.items.Shoes;

public class OrbDialogPanel extends JPanel implements ActionListener {

	private class OrbImagePanel extends JPanel {
		private BufferedImage _image;
		
		public OrbImagePanel(AbstractItem item) {
			if(item != null)
				_image = item.getImage();
			else
				_image = null;
		}
		
		public void setItem(AbstractItem item) {
			if(item != null)
				_image = item.getImage();
			else
				_image = null;
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			if(_image != null)
				g.drawImage(_image, 0, 0, _image.getWidth(this), _image.getHeight(this), this);
				//g.drawImage(_image, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	
	private OrbDialog _dialog;
	private PlayerPanel _playerPanel;
	
	private JComboBox<Orb> _orbs1;
	private JComboBox<Orb> _orbs2;
	private JComboBox<Orb> _orbs3;
	private JComboBox<Orb> _orbs4;
	
	private OrbImagePanel _orb1_img;
	private OrbImagePanel _orb2_img;
	private OrbImagePanel _orb3_img;
	private OrbImagePanel _orb4_img;
	
	private JButton _ok;
	private JButton _cancel;
	private JButton _reset;
	
	public OrbDialogPanel(OrbDialog dialog, PlayerPanel playerPanel) {
		super();
		
		_dialog = dialog;
		_playerPanel = playerPanel;
		
		ArrayList<AbstractItem> orbs = ItemDatabase.getInstance().getItemsByType(ItemType.ORB);
		orbs.add(0, null);
		
		Orb orbsTab[] = new Orb[orbs.size()];
		for(int i = 0 ; i < orbs.size() ; i++) {
			orbsTab[i] = (Orb) orbs.get(i);
		}
		
		//Orbs 1
		_orbs1 = new JComboBox<Orb>(orbsTab);
		_orbs1.addActionListener(this);
		
		//Orbs 2
		_orbs2 = new JComboBox<Orb>(orbsTab);
		_orbs2.addActionListener(this);
		
		//Orbs 3
		_orbs3 = new JComboBox<Orb>(orbsTab);
		_orbs3.addActionListener(this);
		
		//Orbs 4
		_orbs4 = new JComboBox<Orb>(orbsTab);
		_orbs4.addActionListener(this);
		
		// Orb 1 Image
		_orb1_img = new OrbImagePanel((Orb) _orbs1.getSelectedItem());
		
		// Orb 2 Image
		_orb2_img = new OrbImagePanel((Orb) _orbs2.getSelectedItem());
		
		// Orb 3 Image
		_orb3_img = new OrbImagePanel((Orb) _orbs3.getSelectedItem());
		
		// Orb 4 Image
		_orb4_img = new OrbImagePanel((Orb) _orbs4.getSelectedItem());
		
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
		
		p.add(new JLabel("Orbe 1 : "), "1,1");
		p.add(_orbs1, "3,1");
		p.add(_orb1_img, "5,1");
		p.add(new JLabel("Orbe 2 : "), "1,3");
		p.add(_orbs2, "3,3");
		p.add(_orb2_img, "5,3");
		p.add(new JLabel("Orbe 3 : "), "1,5");
		p.add(_orbs3, "3,5");
		p.add(_orb3_img, "5,5");
		p.add(new JLabel("Orbe 4 : "), "1,7");
		p.add(_orbs4, "3,7");
		p.add(_orb4_img, "5,7");
		
		JPanel buttons = new JPanel(new GridLayout(1,5));
		buttons.add(_ok);
		buttons.add(new JPanel());
		buttons.add(_cancel);
		buttons.add(new JPanel());
		buttons.add(_reset);
		
		p.add(buttons, "1,9, 5,9");
		
		add(p, "1,1");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if(source == _orbs1) {
			Orb orb = (Orb) _orbs1.getSelectedItem();
			_orb1_img.setItem(orb);
			return;
		}
		if(source == _orbs2) {
			Orb orb = (Orb) _orbs2.getSelectedItem();
			_orb2_img.setItem(orb);
			return;
		}
		if(source == _orbs3) {
			Orb orb = (Orb) _orbs3.getSelectedItem();
			_orb3_img.setItem(orb);
			return;
		}
		if(source == _orbs4) {
			Orb orb = (Orb) _orbs4.getSelectedItem();
			_orb4_img.setItem(orb);
			return;
		}
		
		
		
		if(e.getActionCommand().equals("OK")) {
			
			Orb orb1 = (Orb) _orbs1.getSelectedItem();
			Orb orb2 = (Orb) _orbs2.getSelectedItem();
			Orb orb3 = (Orb) _orbs3.getSelectedItem();
			Orb orb4 = (Orb) _orbs4.getSelectedItem();
			
			Player player = _playerPanel.getPlayer();
			player.setOrb(orb1, 1);
			player.setOrb(orb2, 2);
			player.setOrb(orb3, 3);
			player.setOrb(orb4, 4);
			
			_dialog.setVisible(false);
			
		} else if(e.getActionCommand().equals("Cancel")) {
			
			_dialog.setVisible(false);
			
		} else if(e.getActionCommand().equals("Reset")) {
			
			Player player = _playerPanel.getPlayer();
			player.setOrb(null, 1);
			player.setOrb(null, 2);
			player.setOrb(null, 3);
			player.setOrb(null, 4);
			
			_dialog.setVisible(false);
			
		}
		
	}
}
