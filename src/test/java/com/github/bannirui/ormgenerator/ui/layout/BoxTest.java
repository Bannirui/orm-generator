package com.github.bannirui.ormgenerator.ui.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import javax.swing.Box;

public class BoxTest {

	private static void test00() {
		Frame frame = new Frame("box");
		// 水平排列的box
		Box hBox = Box.createHorizontalBox();
		hBox.add(new Button("btn1"));
		hBox.add(new Button("btn2"));
		// 垂直排列的box
		Box vBox = Box.createVerticalBox();
		vBox.add(new Button("btn3"));
		vBox.add(new Button("btn4"));
		// 两个box添加到frame中 frame默认的布局是border
		frame.add(hBox, BorderLayout.NORTH);
		// 默认添加到Center区域
		frame.add(vBox);
		frame.pack();
		frame.setVisible(true);
	}

	private static void test01() {
		Frame frame = new Frame("box");
		// 水平排列的box 组件之间添加间隔
		Box hBox = Box.createHorizontalBox();
		hBox.add(new Button("btn1"));
		// 分割组件在两个方向都可以拉伸
		hBox.add("水平分割", Box.createHorizontalGlue());
		hBox.add(new Button("btn2"));
		hBox.add("指定宽度的分割", Box.createHorizontalStrut(3));
		hBox.add(new Button("btn3"));
		// 垂直排列的box 组件之间添加间隔
		Box vBox = Box.createVerticalBox();
		vBox.add(new Button("btn4"));
		// 间隔可以两个方向拉伸
		vBox.add("垂直组件间隔", Box.createVerticalGlue());
		vBox.add(new Button("btn5"));
		// 固定高度的间隔
		vBox.add(Box.createVerticalStrut(30));
		vBox.add(new Button("btn6"));
		// 两个box添加到frame中 frame默认的布局是border
		frame.add(hBox, BorderLayout.NORTH);
		// 默认添加到Center区域
		frame.add(vBox);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// test00();
		test01();
	}
}
