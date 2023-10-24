package com.github.bannirui.ormgenerator.config;

public class MapperProfile {

	private String moduleName;

	/**
	 * split by '.'.
	 */
	private String modelPackageName;

	/**
	 * split by '.'.
	 */
	private String daoPackageName;


	/**
	 * split by '/'.
	 */
	private String mapperDirectoryName;

	private String srcDir;

	public MapperProfile(String moduleName, String modelPackageName, String daoPackageName, String mapperDirectoryName, String srcDir) {
		this.moduleName = moduleName;
		this.modelPackageName = modelPackageName;
		this.daoPackageName = daoPackageName;
		this.mapperDirectoryName=mapperDirectoryName;
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

	public String getMapperDirectoryName() {
		return mapperDirectoryName;
	}

	public String getSrcDir() {
		return srcDir;
	}
}
