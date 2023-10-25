package com.github.bannirui.ormgenerator.config;

public class Profile {

	// module
	private String moduleName;

	// model
	private String modelPackage;
	private String modelSrcDir;

	// dao
	private String daoPackage;
	private String daoSrcDir;

	// mapper
	private String mapperDir;
	private String mapperResourcesDir;

	public Profile() {
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModelPackage() {
		return modelPackage;
	}

	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	public String getModelSrcDir() {
		return modelSrcDir;
	}

	public void setModelSrcDir(String modelSrcDir) {
		this.modelSrcDir = modelSrcDir;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getDaoSrcDir() {
		return daoSrcDir;
	}

	public void setDaoSrcDir(String daoSrcDir) {
		this.daoSrcDir = daoSrcDir;
	}

	public String getMapperDir() {
		return mapperDir;
	}

	public void setMapperDir(String mapperDir) {
		this.mapperDir = mapperDir;
	}

	public String getMapperResourcesDir() {
		return mapperResourcesDir;
	}

	public void setMapperResourcesDir(String mapperResourcesDir) {
		this.mapperResourcesDir = mapperResourcesDir;
	}
}