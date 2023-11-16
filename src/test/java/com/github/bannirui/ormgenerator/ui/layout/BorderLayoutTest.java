package com.github.bannirui.ormgenerator.ui.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;

public class BorderLayoutTest {

	private static void test00() {
		Frame frame = new Frame("border layout test");
		frame.setLayout(new BorderLayout(30, 10));
		frame.add(new Button("btn1"), BorderLayout.NORTH);
		frame.add(new Button("btn2"), BorderLayout.SOUTH);
		frame.add(new Button("btn3"), BorderLayout.WEST);
		frame.add(new Button("btn4"), BorderLayout.EAST);
		frame.add(new Button("btn5"), BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * 如果有区域没有放上组件 默认会被Center区域侵占
	 * 往同一个区域放多个组件 只会显示最后添加的那个组件
	 */
	private static void test01() {
		Frame frame = new Frame("border layout test");
		frame.setLayout(new BorderLayout(30, 10));
		frame.add(new Button("btn1"), BorderLayout.NORTH);
		frame.add(new Button("btn2"), BorderLayout.SOUTH);
		frame.add(new Button("btn5"), BorderLayout.CENTER);
		frame.add(new TextField("input"), BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	private static void test02() {
		Frame frame = new Frame("border layout test");
		frame.setLayout(new BorderLayout(30, 10));
		frame.add(new Button("btn1"), BorderLayout.NORTH);
		frame.add(new Button("btn2"), BorderLayout.SOUTH);
		Panel panel = new Panel();
		panel.add(new Button("中间按钮"));
		panel.add(new TextField("input"));
		// 不指定区域 默认是Center区域
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// test00();
		// test01();
		test02();
	}
}
