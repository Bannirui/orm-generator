package com.github.bannirui.ormgenerator.ui.component.dialog;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.TextField;
import javax.swing.Box;

public class DialogTest02 {
	public static void main(String[] args) {
		Frame frame = new Frame("test frame");
		Dialog d = new Dialog(frame, "模式对话框", true);
		d.setBounds(20, 30, 300, 200);
		Box box = Box.createVerticalBox();
		box.add(new TextField(20));
		box.add(new Button("ok"));
		d.add(box);
		// 两个按钮
		Button b = new Button("打开模式对话框");
		// 绑定按钮的点击事件
		b.addActionListener(e -> {
			d.setVisible(true);
		});
		// 按钮添加到frame中
		frame.add(b, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}
}
