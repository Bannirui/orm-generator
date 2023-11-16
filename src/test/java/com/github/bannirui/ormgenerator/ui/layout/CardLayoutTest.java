package com.github.bannirui.ormgenerator.ui.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardLayoutTest {

	public static void main(String[] args) {
		Frame frame = new Frame("card layout test");
		// panel存储多个卡片
		Panel p1 = new Panel();
		// 卡片布局对象 设置给panel
		CardLayout cardLayout = new CardLayout();
		p1.setLayout(cardLayout);
		// 往panel添加组件
		String[] names = new String[] {"1", "2", "3", "4", "5"};
		for (int i = 0; i < 5; i++) {
			p1.add(names[i], new Button(names[i]));
		}
		// panel加到frame中间区域
		frame.add(p1);
		// 创建另外一个panel放到frame南部区域
		Panel p2 = new Panel();
		// 创建按钮
		Button b1 = new Button("上一张");
		Button b2 = new Button("下一张");
		Button b3 = new Button("第一张");
		Button b4 = new Button("最后一张");
		Button b5 = new Button("第三张");
		// 给按钮添加事件监听器
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = e.getActionCommand(); // 按钮的名字
				switch (name) {
					case "上一张":
						cardLayout.previous(p1);
						break;
					case "下一张":
						cardLayout.next(p1);
						break;
					case "第一张":
						cardLayout.first(p1);
						break;
					case "最后一张":
						cardLayout.last(p1);
						break;
					case "第三张":
						cardLayout.show(p1, names[2]);
						break;
				}
			}
		};
		b1.addActionListener(listener);
		b2.addActionListener(listener);
		b3.addActionListener(listener);
		b4.addActionListener(listener);
		b5.addActionListener(listener);
		// 按钮添加到panel中
		p2.add(b1);
		p2.add(b2);
		p2.add(b3);
		p2.add(b4);
		p2.add(b5);
		frame.add(p2, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}
}
