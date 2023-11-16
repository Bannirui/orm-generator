package com.github.bannirui.ormgenerator.ui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;

public class PanelTest {

	public static void main(String[] args) {
		// panel以及其他容器都不能独立存在 都依附于window
		Frame frame = new Frame("test frame");
		Panel panel = new Panel();
		panel.add(new TextField("Input"));
		panel.add(new Button("Btn"));
		frame.add(panel);
		frame.setBounds(100, 100, 500, 300);
		frame.setVisible(true);
	}
}
