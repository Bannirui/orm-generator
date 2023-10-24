package com.github.bannirui.ormgenerator.config;

public class DaoProfile {

	private String moduleName;
	private String modelPackageName;
	private String daoPackageName;
	private String srcDir;

	public DaoProfile(String moduleName, String modelPackageName, String daoPackageName, String srcDir) {
		this.moduleName = moduleName;
		this.modelPackageName = modelPackageName;
		this.daoPackageName = daoPackageName;
		this.srcDir = srcDir;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String getModelPackageName() {
		return modelPackageName;
	}

	public String getDaoPackageName() {
		return daoPackageName;
	}

	public String getSrcDir() {
		return srcDir;
	}
}
