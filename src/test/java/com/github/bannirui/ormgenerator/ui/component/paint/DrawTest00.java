package com.github.bannirui.ormgenerator.ui.component.paint;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;

public class DrawTest00 {

	private enum Shape {
		RECT, OVAL;
	}

	private class MyCanvas extends Canvas {

		public MyCanvas() {
		}

		// repaint()->update()->paint()
		@Override
		public void paint(Graphics g) {
			switch (DrawTest00.this.shape) {
				case RECT -> this.paintRect(g);
				case OVAL -> this.paintOval(g);
			}
		}

		private void paintRect(Graphics g) {
			g.setColor(Color.BLACK);
			g.drawRect(100, 100, 150, 100);
		}

		private void paintOval(Graphics g) {
			g.setColor(Color.RED);
			g.drawOval(100, 100, 150, 100);
		}
	}

	private Shape shape;

	public void draw() {
		Frame frame = new Frame("draw test");
		Button btn1 = new Button("绘制矩形");
		Button btn2 = new Button("绘制椭圆");
		// 画布
		MyCanvas canvas = new MyCanvas();
		canvas.setPreferredSize(new Dimension(300, 300));
		frame.add(canvas);
		btn1.addActionListener(e -> {
			DrawTest00.this.shape = Shape.RECT;
			canvas.repaint();
		});
		btn2.addActionListener(e -> {
			DrawTest00.this.shape = Shape.OVAL;
			canvas.repaint();
		});
		// 画布设置

		// 创建panel用来放按钮
		Panel p = new Panel();
		p.add(btn1);
		p.add(btn2);
		frame.add(p, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new DrawTest00().draw();
	}
}
