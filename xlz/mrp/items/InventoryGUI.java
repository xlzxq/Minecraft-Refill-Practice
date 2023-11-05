package xlz.mrp.items;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import xlz.mrp.Main;

public class InventoryGUI {

	public static JLabel createLabel() {
		BufferedImage image = loadImageFromClasspath("xlz/mrp/assets/inventory.jpg");

		if (image != null) {
			ImageIcon icon = new ImageIcon(image);
			JLabel label = new JLabel(icon);
			for (int y = 0; y < 3; y++) {
				for (int x = 0; x < 9; x++) {
					label.add(Item.createLabel(x, y, (y * 10) + x));
					Main.refillGUI.nonMovedSlots.add((y * 10) + x);
				}
			}
			return label;
		} else {
			return null;
		}
	}

	private static BufferedImage loadImageFromClasspath(String resourcePath) {
		try {
			return ImageIO.read(InventoryGUI.class.getClassLoader().getResourceAsStream(resourcePath));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}