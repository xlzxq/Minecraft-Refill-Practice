package xlz.mrp.items;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import xlz.mrp.Main;

public class Item {
	
	public static HashMap<Integer, JLabel> idWithLabel = new HashMap<>();

	public static JLabel createLabel(int x, int y, int id) {
		BufferedImage soup = loadImageFromClasspath("xlz/mrp/assets/soup.png"),
				pot = loadImageFromClasspath("xlz/mrp/assets/pot.png");

		if (soup != null && pot != null) {
			ImageIcon icon;
			if (!Main.potsOrSoup) {
				icon = new ImageIcon(soup);
			} else {
				icon = new ImageIcon(pot);
			}
			JLabel label = new JLabel(icon);
			label.setBounds(15 + (x * 35), 165 + (y * 36), icon.getIconWidth(), icon.getIconHeight());
			label.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {}

				@Override
				public void mousePressed(MouseEvent e) {
					Main.refillGUI.onClickEvent();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					Main.refillGUI.onClickEvent();
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					Main.refillGUI.currentSlot = id;
				}

				@Override
				public void mouseExited(MouseEvent e) {
					Main.refillGUI.currentSlot = -1;
				}
			});
			idWithLabel.put(id, label);
			return label;
		} else {
			return null;
		}
	}
	
	public static JLabel getLabelByID(int id) {
		if (idWithLabel.containsKey(id)) {
			return idWithLabel.get(id);
		}
		return null;
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
