package com.github.bannirui.ormgenerator.ui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class BackGroundPanel extends JPanel {

	private Image bgIcon;

	public BackGroundPanel(Image bgIcon) {
		this.bgIcon = bgIcon;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 背景图片
		g.drawImage(this.bgIcon, 0, 0, super.getWidth(), super.getHeight(), null);
	}
}
