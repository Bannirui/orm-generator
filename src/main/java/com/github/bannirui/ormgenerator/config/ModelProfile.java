package com.github.bannirui.ormgenerator.config;

public class ModelProfile {

	private String moduleName;
	private String packageName;
	private String srcDir;

	public ModelProfile(String moduleName, String packageName, String srcDir) {
		this.moduleName = moduleName;
		this.packageName = packageName;
		this.srcDir = srcDir;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getSrcDir() {
		return srcDir;
	}
}
