package com.github.bannirui.ormgenerator.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.List;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.Box;

public class Test00 {

	Frame frame = new Frame("test frame");
	TextArea ta = new TextArea(5, 20);
	Choice choice = new Choice();
	CheckboxGroup cbg = new CheckboxGroup();
	Checkbox male = new Checkbox("男", cbg, true);
	Checkbox female = new Checkbox("女", cbg, false);

	Checkbox marry = new Checkbox("是否已婚?");

	TextField tf = new TextField(50);
	Button okBtn = new Button("确认?");

	List colorList = new List(6, true);

	public void init() {
		Box b1 = Box.createHorizontalBox();
		b1.add(tf);
		b1.add(okBtn);
		frame.add(b1, BorderLayout.SOUTH);

		Box b2 = Box.createHorizontalBox();
		choice.add("red");
		choice.add("blue");
		choice.add("green");
		b2.add(choice);
		b2.add(male);
		b2.add(female);
		b2.add(marry);

		Box b3 = Box.createVerticalBox();
		b3.add(ta);
		b3.add(b2);

		Box b4 = Box.createHorizontalBox();
		b4.add(b3);
		colorList.add("red");
		colorList.add("green");
		colorList.add("blue");
		b4.add(colorList);

		frame.add(b4);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Test00().init();
	}
}
