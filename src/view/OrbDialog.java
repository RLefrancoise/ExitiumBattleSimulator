package view;

import java.awt.Frame;

import javax.swing.JDialog;

public class OrbDialog extends JDialog {

	private OrbDialogPanel _panel;
	
	public OrbDialog(Frame parent, PlayerPanel playerPanel) {
		super(parent, "Set Orbs", true);
		setSize(600,400);
		setLocationRelativeTo(parent);
		
		_panel = new OrbDialogPanel(this, playerPanel);
		setContentPane(_panel);
	}
}
