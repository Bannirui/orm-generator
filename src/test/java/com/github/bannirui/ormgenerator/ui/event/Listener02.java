package com.github.bannirui.ormgenerator.ui.event;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Listener02 {

	public static void main(String[] args) {
		Frame frame = new Frame("test listener");
		frame.setBounds(200, 300, 500, 300);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.pack();
		frame.setVisible(true);
	}
}
