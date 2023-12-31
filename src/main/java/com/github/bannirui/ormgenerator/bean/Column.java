package com.github.bannirui.ormgenerator.bean;

public class Column {

	// like tb_user
	private String lowerUnderscoreName;

	// like tbUser
	private String lowerCamelName;

	// like TbUser
	private String upperCamelName;

	/**
	 * <p>Type, like</p>
	 * <ul>
	 *     <li>BIGINT</li>
	 *     <li>INT</li>
	 * </ul>
	 */
	private String dbType;

	/**
	 * <p>Type, like</p>
	 * <ul>
	 *     <li>Long</li>
	 *     <li>Integer</li>
	 * </ul>
	 */
	private String jdkType;

	/**
	 * <p>Type, like</p>
	 * <ul>
	 *     <li>java.lang.Long</li>
	 *     <li>java.lang.Integer</li>
	 * </ul>
	 */
	private String jdkLangType;

	private String comment;

	/**
	 * <p>Determine column attribute</p>
	 * <ul>
	 *     <li>True-column is primaryKey</li>
	 *     <li>False-common column</li>
	 * </ul>
	 */
	private boolean primaryKey;

	public Column(String lowerUnderscoreName, String lowerCamelName, String upperCamelName, String dbType, String jdkType, String jdkLangType,
				  String comment) {
		this.lowerUnderscoreName = lowerUnderscoreName;
		this.lowerCamelName = lowerCamelName;
		this.upperCamelName = upperCamelName;
		this.dbType = dbType;
		this.jdkType = jdkType;
		this.jdkLangType = jdkLangType;
		this.comment = comment;
		this.primaryKey = false;
	}

	public Column(String lowerUnderscoreName, String lowerCamelName, String upperCamelName, String dbType, String jdkType, String jdkLangType,
				  String comment, boolean primaryKey) {
		this(lowerUnderscoreName, lowerCamelName, upperCamelName, dbType, jdkType, jdkLangType, comment);
		this.primaryKey = primaryKey;
	}

	public String getLowerUnderscoreName() {
		return lowerUnderscoreName;
	}

	public String getLowerCamelName() {
		return lowerCamelName;
	}

	public String getUpperCamelName() {
		return upperCamelName;
	}

	public String getDbType() {
		return dbType;
	}

	public String getJdkType() {
		return jdkType;
	}

	public String getJdkLangType() {
		return jdkLangType;
	}

	public String getComment() {
		return comment;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	@Override
	public String toString() {
		return this.lowerUnderscoreName;
	}
}
