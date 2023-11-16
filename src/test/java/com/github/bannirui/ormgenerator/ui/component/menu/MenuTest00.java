package com.github.bannirui.ormgenerator.ui.component.menu;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.TextArea;
import java.awt.event.KeyEvent;

public class MenuTest00 {

	public static void main(String[] args) {
		Frame frame = new Frame("menu test");

		// 菜单条
		MenuBar menuBar = new MenuBar();
		// 菜单条中的菜单
		Menu fileMenu = new Menu("文件");
		Menu editMenu = new Menu("编辑");
		Menu formatMenu = new Menu("格式");

		// 菜单项
		MenuItem mi1 = new MenuItem("自动换行");
		MenuItem mi2 = new MenuItem("复制");
		MenuItem mi3 = new MenuItem("粘贴");
		// ctrl+shift+q
		MenuItem mi4 = new MenuItem("注释", new MenuShortcut(KeyEvent.VK_Q, true));
		MenuItem mi5 = new MenuItem("取消注释");

		TextArea ta = new TextArea(6, 40);

		mi4.addActionListener(e -> {
			// 监听事件 注释菜单项被点击
			ta.append("点击了菜单项: " + e.getActionCommand() + "\n");
		});

		formatMenu.add(mi4);
		formatMenu.add(mi5);

		editMenu.add(mi1);
		editMenu.add(mi2);
		editMenu.add(mi3);
		// 比较实用的分割符
		editMenu.add("-");
		editMenu.add(formatMenu);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);

		// 菜单条放到frame中
		frame.setMenuBar(menuBar);
		frame.add(ta);

		frame.pack();
		frame.setVisible(true);
	}
}
