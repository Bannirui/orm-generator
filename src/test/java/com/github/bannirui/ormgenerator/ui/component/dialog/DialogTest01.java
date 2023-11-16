package com.github.bannirui.ormgenerator.ui.component.dialog;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;

public class DialogTest01 {
	public static void main(String[] args) {
		Frame frame = new Frame("test frame");
		// 两个对话框 模式的和非模式的
		Dialog d1 = new Dialog(frame, "模式对话框", true);
		Dialog d2 = new Dialog(frame, "非模式对话框", false);
		// 设置对话框的位置和大小
		d1.setBounds(20, 30, 300, 200);
		d2.setBounds(20, 30, 300, 200);
		// 两个按钮
		Button b1 = new Button("打开模式对话框");
		Button b2 = new Button("打开非模式对话框");
		// 绑定按钮的点击事件
		b1.addActionListener(e -> {
			d1.setVisible(true);
		});
		b2.addActionListener(e -> {
			d2.setVisible(true);
		});
		// 按钮添加到frame中
		frame.add(b1, BorderLayout.NORTH);
		frame.add(b2);
		frame.pack();
		frame.setVisible(true);
	}
}
