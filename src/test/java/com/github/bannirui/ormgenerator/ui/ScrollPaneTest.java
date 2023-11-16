package com.github.bannirui.ormgenerator.ui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.ScrollPane;
import java.awt.TextField;

public class ScrollPaneTest {

	public static void main(String[] args) {
		Frame frame = new Frame("scroll pane");

		ScrollPane sp = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		sp.add(new TextField("test text"));
		sp.add(new Button("test btn"));
		frame.add(sp);
		frame.setBounds(100, 100, 500, 300);
		frame.setVisible(true);
	}
}
