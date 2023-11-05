package xlz.mrp.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import xlz.mrp.Main;
import xlz.mrp.items.InventoryGUI;
import xlz.mrp.items.Item;

public class RefillGUI {
	
	public boolean shiftPressed, dropPressed;
	public int currentSlot;
	
	public ArrayList<Integer> nonMovedSlots, movedSlots;
	public ArrayList<Long> timestamps;
	public ArrayList<String> methods;

	public boolean createAndShowGUI() {
		nonMovedSlots = new ArrayList<>();
		movedSlots = new ArrayList<>();
		timestamps = new ArrayList<>();
		methods = new ArrayList<>();
		
		JLabel label = InventoryGUI.createLabel();
		
        if (label != null) {
    		JFrame frame = new JFrame("MRP v1 - Refill");
    		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		frame.setLayout(new FlowLayout());
    		
            frame.add(label);
            frame.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
					dropPressed = e.getKeyCode() == KeyEvent.VK_Q && !e.isShiftDown();
					shiftPressed = e.isShiftDown() && e.getKeyCode() != KeyEvent.VK_Q;
					
					if (dropPressed) {
						onClickEvent();
					}
                }

                @Override
				public void keyReleased(KeyEvent e) {
					dropPressed = false;
					shiftPressed = e.isShiftDown() && e.getKeyCode() != KeyEvent.VK_Q;
                }
            });
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setBackground(Color.black);
            frame.setVisible(true);
            return true;
        } else {
            return false;
        }
	}
	
	public void onClickEvent() {
		if (shiftPressed || dropPressed) {
			if (!movedSlots.contains(currentSlot) && nonMovedSlots.contains(currentSlot) && !(movedSlots.size() >= Main.amountInHotbar)) {
				nonMovedSlots.remove((Object) currentSlot);
				movedSlots.add(currentSlot);
				timestamps.add(System.currentTimeMillis());
				methods.add(shiftPressed ? "Shift" : dropPressed ? "Drop" : "Other");
				Item.getLabelByID(currentSlot).setVisible(false);
				dropPressed = false;
			}
			
			if (movedSlots.size() >= Main.amountInHotbar && FinishGUI.closed) {
				Main.openFinishGUI();
			}
		}
	}

}