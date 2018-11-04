package model.items;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import model.Config;

public abstract class AbstractItem implements Comparable<AbstractItem> {
	
	protected int _id;
	protected String _name;
	protected String _desc;
	protected BufferedImage _image;
	
	protected AbstractItem(int id, String name, String desc, String iconName) {
		_id = id;
		_name = name;
		_desc = desc;
		
		if(!iconName.equals("")) {
			try {
				_image = ImageIO.read(new File(Config.ICONS_PATH + iconName));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Le fichier " + Config.ICONS_PATH + iconName + " n'a pas été trouvé.", "Erreur de chargement", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	
	public int getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getDesc() {
		return _desc;
	}
	
	public BufferedImage getImage() {
		return _image;
	}
	
	public abstract ItemType getType();
	
	@Override
	public String toString() {
		return _name + "[" + _desc + "]";
	}
	
	public int compareTo(AbstractItem i) {
		if(getId() < i.getId())
			return -1;
		else if(getId() == i.getId())
			return 0;
		else
			return 1;
	}
}
