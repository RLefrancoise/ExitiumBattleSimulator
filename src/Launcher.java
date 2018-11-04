import model.database.ItemDatabase;
import model.database.SetDatabase;
import view.MainFrame;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting...");
		
		ItemDatabase.getInstance();
		SetDatabase.getInstance();
		
		MainFrame.getInstance().repaint();
	}

}
