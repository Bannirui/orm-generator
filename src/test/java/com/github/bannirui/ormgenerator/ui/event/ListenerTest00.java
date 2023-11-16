package com.github.bannirui.ormgenerator.ui.event;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;

public class ListenerTest00 {

	public static void main(String[] args) {
		Frame frame = new Frame("test listener");
		TextField tf = new TextField(30);
		Button btn = new Button("OK");
		btn.addActionListener(e -> {
			tf.setText("hello world");
		});
		frame.add(tf, BorderLayout.NORTH);
		frame.add(btn);
		frame.pack();
		frame.setVisible(true);
	}
}
