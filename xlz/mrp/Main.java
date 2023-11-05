package xlz.mrp;

import xlz.mrp.gui.FinishGUI;
import xlz.mrp.gui.OptionsGUI;
import xlz.mrp.gui.RefillGUI;

public class Main {
	
	public static OptionsGUI mainGUI;
	public static RefillGUI refillGUI;
	public static FinishGUI finishGUI;
	
	public static boolean potsOrSoup = false;
	public static int amountInHotbar = 8;

	public static void main(String[] args) {
		(mainGUI = new OptionsGUI()).createAndShowGUI();
	}
	
	public static boolean openRefillGUI() {
		return (refillGUI = new RefillGUI()).createAndShowGUI();
	}
	
	public static void openFinishGUI() {
		(finishGUI = new FinishGUI()).createAndShowGUI();
		FinishGUI.closed = false;
	}

}
