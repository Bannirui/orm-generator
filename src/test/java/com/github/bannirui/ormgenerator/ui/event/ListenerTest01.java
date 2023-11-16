package com.github.bannirui.ormgenerator.ui.event;

import java.awt.Choice;
import java.awt.Component;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.Box;

public class ListenerTest01 {

	public static void main(String[] args) {
		Frame frame = new Frame("listener test");
		TextField tf = new TextField(30);
		Choice names = new Choice();
		names.add("a");
		names.add("b");
		names.add("c");

		// 监听文本框 文本框文本变化时 事件
		tf.addTextListener(e -> {
			String s = tf.getText();
			System.out.println("当前文本框内容为: " + s);
		});

		// 监听下拉框 下拉框选项选择时事件
		names.addItemListener(e -> {
			String s = (String) e.getItem();
			System.out.println("下拉框选择到了: " + s);
		});

		// 监听容器 组件添加事件
		frame.addContainerListener(new ContainerListener() {
			@Override
			public void componentAdded(ContainerEvent e) {
				Component child = e.getChild();
				System.out.println("frame中添加了: " + child);
			}

			@Override
			public void componentRemoved(ContainerEvent e) {

			}
		});
		Box box = Box.createHorizontalBox();
		box.add(names);
		box.add(tf);
		frame.add(box);
		frame.pack();
		frame.setVisible(true);
	}
}
