package com.github.bannirui.ormgenerator.ui.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;

public class GridLayoutTest {

	private static void test00() {
		// frame是window 默认的布局管理器是border layout
		Frame frame = new Frame("grid layout");
		// panel里面放文本框
		Panel p1 = new Panel();
		p1.add(new TextField(30));
		// panel放到frame北边区域
		frame.add(p1, BorderLayout.NORTH);
		// 默认用Center区域放一个panel 这个panel用网格布局管理器
		Panel p2 = new Panel();
		// 3行5列 水平间距4 垂直间距4 组件从左往右 从上往下放
		p2.setLayout(new GridLayout(3, 5, 4, 4));
		for (int i = 0; i < 10; i++) {
			p2.add(new Button("" + i));
		}
		p2.add(new Button("+"));
		p2.add(new Button("-"));
		p2.add(new Button("*"));
		p2.add(new Button("/"));
		p2.add(new Button("."));
		// 默认往Center区域放
		frame.add(p2);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		test00();
	}
}
