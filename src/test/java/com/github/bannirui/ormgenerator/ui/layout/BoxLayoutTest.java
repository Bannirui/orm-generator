package com.github.bannirui.ormgenerator.ui.layout;

import java.awt.Button;
import java.awt.Frame;
import javax.swing.BoxLayout;

public class BoxLayoutTest {

	public static void main(String[] args) {
		Frame frame = new Frame("box layout test");
		BoxLayout layout = new BoxLayout(frame, BoxLayout.Y_AXIS);
		frame.setLayout(layout);
		frame.add(new Button("btn1"));
		frame.add(new Button("btn2"));
		frame.pack();
		frame.setVisible(true);
	}
}
