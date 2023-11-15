package com.github.bannirui.ormgenerator.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameTest {

	private static void buildConstraints(GridBagConstraints con,
										 int x, int y,
										 int wx, int wy,
										 int fill, int anchor) {
		buildConstraints(con, x, y, 1, 1, wx, wy, fill, anchor);
	}

	private static void buildConstraints(GridBagConstraints con,
										 int x, int y,
										 int w, int h,
										 int wx, int wy,
										 int fill, int anchor) {
		// 起始坐标[x, y]
		con.gridx = x;
		con.gridy = y;

		// 横跨几个单元格
		con.gridwidth = w;
		// 竖跨几个单元格
		con.gridheight = h;

		// 窗口变大时缩放比例
		con.weightx = wx;
		con.weighty = wy;

		/**
		 * 组件在单元格中对齐方式
		 * <ul>
		 *     <li>NONE       不调整组件大小</li>
		 *     <li>HORIZONTAL 加宽组件 使它在水平方向上填满显示区域 但是不改变高度</li>
		 *     <li>VERTICAL   加搞组件 是它在垂直方向上填满显示区域 但是不改变宽度</li>
		 *     <li>BOTH       组件完全填满显示区域</li>
		 * </ul>
		 */
		con.fill = fill;
		/**
		 * 当组件不完全填充单元格时 组件应该放在单元格的什么位置
		 * <ul>
		 *     <li>CENTER</li>
		 *     <li>NORTH</li>
		 *     <li>NORTHEAST</li>
		 *     <li>EAST</li>
		 *     <li>SOUTHEAST</li>
		 *     <li>SOUTH</li>
		 *     <li>SOUTHWEST</li>
		 *     <li>WEST</li>
		 *     <li>NORTHWEST</li>
		 * </ul>
		 */
		con.anchor = anchor;
	}

	public static void test00() {
		JFrame mainFrame = new JFrame("test frame");
		mainFrame.setSize(600, 480);

		JPanel panel = new JPanel();
		// 网格袋布局-GridBagLayout类
		// 布局管理器
		GridBagLayout layout = new GridBagLayout();
		// 网格袋布局-助手类
		GridBagConstraints constraints = new GridBagConstraints();
		// 容器的布局管理器
		panel.setLayout(layout);

		// 组件
		JLabel nameLb = new JLabel("Name", JLabel.LEFT);
		// 设置组件的约束条件
		buildConstraints(constraints, 0, 0, 10, 30, GridBagConstraints.NONE, GridBagConstraints.EAST);
		// 将组件及其约束条件告诉布局管理器
		layout.setConstraints(nameLb, constraints);
		// 将组件加入到容器中
		panel.add(nameLb);

		// 组件
		JLabel pwdLb = new JLabel("Password", JLabel.LEFT);
		// 组件约束条件
		buildConstraints(constraints, 0, 1, 1, 1, 0, 40, GridBagConstraints.NONE, GridBagConstraints.EAST);
		// 将组件及其约束条件告诉布局管理器
		layout.setConstraints(pwdLb, constraints);
		// 组件加入到容器
		panel.add(pwdLb);

		// 组件
		JTextField nameTf = new JTextField();
		// 组件约束条件
		buildConstraints(constraints, 1, 0, 1, 1, 90, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		// 将组件及其约束条件告诉布局管理器
		layout.setConstraints(nameTf, constraints);
		// 组件加入到容器
		panel.add(nameTf);

		// 组件
		JTextField pwdTf = new JTextField();
		// 组件约束条件
		buildConstraints(constraints, 1, 1, 1, 1, 0, 20, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		// 将组件及其约束条件告诉布局管理器
		layout.setConstraints(pwdTf, constraints);
		// 组件加入到容器
		panel.add(pwdTf);

		// 组件
		JButton OkBtn = new JButton("OK");
		// 组件约束条件
		buildConstraints(constraints, 0, 2, 2, 1, 0, 20, GridBagConstraints.NONE, GridBagConstraints.CENTER);
		// 将组件及其约束条件告诉布局管理器
		layout.setConstraints(OkBtn, constraints);
		// 组件加入到容器
		panel.add(OkBtn);

		// 容器
		mainFrame.setContentPane(panel);
		mainFrame.setVisible(true);
	}

	public static void test01() {
		JFrame mainFrame = new JFrame("test frame");
		mainFrame.setSize(600, 480);

		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		panel.setLayout(layout);

		JLabel nameLb = new JLabel("Name", JLabel.LEFT);
		buildConstraints(constraints, 0, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.EAST);
		layout.setConstraints(nameLb, constraints);
		panel.add(nameLb);

		JLabel pwdLb = new JLabel("Password", JLabel.LEFT);
		buildConstraints(constraints, 0, 1, 0, 0, GridBagConstraints.NONE, GridBagConstraints.EAST);
		layout.setConstraints(pwdLb, constraints);
		panel.add(pwdLb);

		JTextField nameTf = new JTextField();
		buildConstraints(constraints, 1, 0, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		layout.setConstraints(nameTf, constraints);
		panel.add(nameTf);

		JTextField pwdTf = new JTextField();
		buildConstraints(constraints, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		layout.setConstraints(pwdTf, constraints);
		panel.add(pwdTf);

		JButton OkBtn = new JButton("OK");
		buildConstraints(constraints, 0, 2, 2, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER);
		layout.setConstraints(OkBtn, constraints);
		panel.add(OkBtn);

		mainFrame.setContentPane(panel);
		mainFrame.setVisible(true);
	}

	public static void test02() {
		JFrame mainFrame = new JFrame("test frame");
		mainFrame.setSize(600, 480);

		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		panel.setLayout(layout);

		JLabel nameLb = new JLabel("Name", JLabel.LEFT);
		buildConstraints(constraints, 0, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.EAST);
		layout.setConstraints(nameLb, constraints);
		panel.add(nameLb);

		JLabel pwdLb = new JLabel("Password", JLabel.LEFT);
		buildConstraints(constraints, 0, 1, 0, 0, GridBagConstraints.NONE, GridBagConstraints.EAST);
		layout.setConstraints(pwdLb, constraints);
		panel.add(pwdLb);

		JTextField nameTf = new JTextField();
		buildConstraints(constraints, 1, 0, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		layout.setConstraints(nameTf, constraints);
		panel.add(nameTf);

		JTextField pwdTf = new JTextField();
		buildConstraints(constraints, 1, 1, 1, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
		layout.setConstraints(pwdTf, constraints);
		panel.add(pwdTf);

		JButton OkBtn = new JButton("OK");
		buildConstraints(constraints, 0, 2, 2, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER);
		layout.setConstraints(OkBtn, constraints);
		panel.add(OkBtn);

		mainFrame.setContentPane(panel);
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		// test00();
		// test01();
		test02();
	}
}
