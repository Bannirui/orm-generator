package com.github.bannirui.ormgenerator.ui.component.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuTest01 {

	public static void main(String[] args) {
		Frame frame = new Frame("menu test");
		TextArea ta = new TextArea("hello world", 6, 30);
		Panel p = new Panel();
		PopupMenu popupMenu = new PopupMenu();
		MenuItem mi1 = new MenuItem("注释");
		MenuItem mi2 = new MenuItem("取消注释");
		MenuItem mi3 = new MenuItem("复制");
		MenuItem mi4 = new MenuItem("保存");
		ActionListener e = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.append("点击了菜单项目: " + e.getActionCommand());
			}
		};
		mi1.addActionListener(e);
		mi2.addActionListener(e);
		mi3.addActionListener(e);
		mi4.addActionListener(e);
		popupMenu.add(mi1);
		popupMenu.add(mi2);
		popupMenu.add(mi3);
		popupMenu.add(mi4);

		p.add(popupMenu);
		p.setPreferredSize(new Dimension(400, 300));
		p.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// 右键释放
				boolean b = e.isPopupTrigger();
				if (b) {
					popupMenu.show(p, e.getX(), e.getY());
				}
			}
		});

		frame.add(ta);
		frame.add(p, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}
}
