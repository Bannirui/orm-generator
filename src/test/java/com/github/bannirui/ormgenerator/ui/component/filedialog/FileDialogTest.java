package com.github.bannirui.ormgenerator.ui.component.filedialog;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Frame;

public class FileDialogTest {

	public static void main(String[] args) {
		Frame frame = new Frame("test file dialog");
		// file dialog
		FileDialog f1 = new FileDialog(frame, "点击要打开的文件", FileDialog.LOAD);
		FileDialog f2 = new FileDialog(frame, "选择要保存的路径", FileDialog.SAVE);
		// 按钮
		Button btn1 = new Button("打开文件");
		Button btn2 = new Button("保存文件");
		// 按钮绑定事件
		btn1.addActionListener(e -> {
			f1.setVisible(true);
			String dir = f1.getDirectory();
			String fileName = f1.getFile();
			System.out.println("打开的路径为: " + dir + ", 文件名为: " + fileName);
		});
		btn2.addActionListener(e -> {
			f2.setVisible(true);
			String dir = f2.getDirectory();
			String fileName = f2.getFile();
			System.out.println("保存的路径为: " + dir + ", 文件名为: " + fileName);
		});
		// 按钮添加到frame
		frame.add(btn1, BorderLayout.NORTH);
		frame.add(btn2);
		frame.pack();
		frame.setVisible(true);
	}
}
