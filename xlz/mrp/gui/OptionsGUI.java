package xlz.mrp.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import xlz.mrp.Main;

public class OptionsGUI {

	public void createAndShowGUI() {
		JFrame frame = new JFrame("MRP v1 - Options");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setLayout(new FlowLayout());

		JLabel label = new JLabel("Amount in hotbar: " + Main.amountInHotbar);

		JButton mode = new JButton("Mode: " + (Main.potsOrSoup ? "Pots" : "Soups"));
		JButton start = new JButton("Start");

		JSlider hotbar = new JSlider(1, 9, 8);
		hotbar.setMajorTickSpacing(1);
		hotbar.setMinorTickSpacing(1);
		hotbar.setPaintTicks(true);
		hotbar.setPaintLabels(true);

		mode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.potsOrSoup = !Main.potsOrSoup;
				mode.setText("Mode: " + (Main.potsOrSoup ? "Pots" : "Soups"));
			}
		});

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Main.openRefillGUI()) {
					label.setText("An error has occurred while loading GUI.");
				}
			}
		});

		hotbar.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Main.amountInHotbar = hotbar.getValue();
				label.setText("Amount in hotbar: " + Main.amountInHotbar);
			}
		});

		frame.add(mode);
		frame.add(start);
		frame.add(hotbar);
		frame.add(label);

        frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
