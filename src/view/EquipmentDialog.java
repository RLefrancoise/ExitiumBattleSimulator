package view;

import java.awt.Frame;
import javax.swing.JDialog;

public class EquipmentDialog extends JDialog {

	private EquipmentDialogPanel _panel;
	
	public EquipmentDialog(Frame parent, PlayerPanel playerPanel) {
		super(parent, "Set Equipment", true);
		setSize(600,400);
		setLocationRelativeTo(parent);
		
		_panel = new EquipmentDialogPanel(this, playerPanel);
		setContentPane(_panel);
	}
}
