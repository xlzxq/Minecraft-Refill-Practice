package xlz.mrp.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import xlz.mrp.Main;

public class FinishGUI {
	
	public static boolean closed = true;

	public void createAndShowGUI() {
		JFrame frame = new JFrame("MRP v1 - Results");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setLayout(new FlowLayout());
		
		frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closed = true;
            }
        });
		
		List<Long> timestamps = subtractByElements(Main.refillGUI.timestamps);

		JLabel time = new JLabel("Duration (ms): " + getTime(Main.refillGUI.timestamps));
		JLabel min = new JLabel("Lowest Time (ms): " + getLowestTime(timestamps));
		JLabel max = new JLabel("Highest Time (ms): " + getHighestTime(timestamps));
		JLabel avg = new JLabel("Average Time (ms): " + getAverageTime(timestamps));
		JLabel soups = new JLabel((Main.potsOrSoup ? "Pots" : "Soups") + " refilled: " + Main.refillGUI.movedSlots.size());
		JLabel refill = new JLabel("Refill method: " + findMethod(Main.refillGUI.methods));

		JButton start = new JButton("Restart");

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Main.openRefillGUI()) {
					start.setText("Error!");
				} else {
					frame.dispose();
					closed = true;
				}
			}
		});

		frame.add(time);
		frame.add(min);
		frame.add(max);
		frame.add(avg);
		frame.add(soups);
		frame.add(refill);
		frame.add(start);

        frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private List<Long> subtractByElements(List<Long> inputList) {
        List<Long> result = new ArrayList<>();
        
        if (inputList.size() > 0) {
            for (int i = 1; i < inputList.size(); i++) {
                long diff = inputList.get(i) - inputList.get(i - 1);
                result.add(diff);
            }
        }
        
        return result;
    }
	
	private long getTime(List<Long> inputList) {
        if (inputList.size() > 0) {
            return inputList.get(inputList.size() - 1) - inputList.get(0);
        }
        return 0;
    }
	
	private long getLowestTime(List<Long> timeList) {
        if (timeList.isEmpty()) {
            return 0L;
        }

        long lowest = Long.MAX_VALUE;
        for (Long time : timeList) {
            if (time < lowest) {
                lowest = time;
            }
        }

        return lowest;
    }

	private long getHighestTime(List<Long> timeList) {
        if (timeList.isEmpty()) {
            return 0L;
        }

        long highest = Long.MIN_VALUE;
        for (Long time : timeList) {
            if (time > highest) {
                highest = time;
            }
        }

        return highest;
    }

	private long getAverageTime(List<Long> timeList) {
        if (timeList.isEmpty()) {
            return 0L;
        }

        long sum = 0;
        for (Long time : timeList) {
            sum += time;
        }

        return sum / timeList.size();
    }
	
	private String findMethod(List<String> stringList) {
        if (stringList.isEmpty()) {
            return null;
        }

        Map<String, Integer> stringCountMap = new HashMap<>();

        for (String str : stringList) {
            stringCountMap.put(str, stringCountMap.getOrDefault(str, 0) + 1);
        }

        String mostFrequentString = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : stringCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentString = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostFrequentString;
    }

}
