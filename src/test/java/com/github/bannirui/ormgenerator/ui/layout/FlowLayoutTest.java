package com.github.bannirui.ormgenerator.ui.layout;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

public class FlowLayoutTest {

	private static void test00() {
		Frame frame = new Frame("flowlayout test");
		// 容器设置布局管理器
		// 左对齐 每个组件水平间距20px 垂直间距20px
		frame.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		for (int i = 0; i < 30; i++) {
			frame.add(new Button("btn:" + i));
		}
		// 设置最佳大小
		frame.pack();
		frame.setVisible(true);
	}

	private static void test01() {
		Frame frame = new Frame("flowlayout test");
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		for (int i = 0; i < 30; i++) {
			frame.add(new Button("btn:" + i));
		}
		frame.pack();
		frame.setVisible(true);
	}

	private static void test02() {
		Frame frame = new Frame("flowlayout test");
		frame.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
		for (int i = 0; i < 30; i++) {
			frame.add(new Button("btn:" + i));
		}
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// test00();
		// test01();
		test02();
	}
}
