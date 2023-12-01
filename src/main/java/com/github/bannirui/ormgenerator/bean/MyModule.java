package com.github.bannirui.ormgenerator.bean;

import com.intellij.openapi.module.Module;

public class MyModule {

	private String name;
	private Module module;

	public MyModule(String name, Module module) {
		this.name = name;
		this.module = module;
	}

	public String getName() {
		return name;
	}

	public Module getModule() {
		return module;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
