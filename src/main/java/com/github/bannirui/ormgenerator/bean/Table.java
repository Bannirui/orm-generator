package com.github.bannirui.ormgenerator.bean;

import com.github.bannirui.ormgenerator.utility.StrUtil;
import com.intellij.database.model.DasColumn;
import com.intellij.database.psi.DbTable;
import com.intellij.database.util.DasUtil;
import com.intellij.util.containers.JBIterable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {

	/**
	 * <p>mapping between mysql field type and java lang type.</p>
	 * <ul>
	 *     <li>key-mysql field, and upper</li>
	 *     <li>value-java lang</li>
	 * </ul>
	 */
	private static final Map<String, String[]> TYPE_MAP = new HashMap<>();

	static {
		TYPE_MAP.put("BIGINT", new String[] {"Long", "java.lang.Long"});
		TYPE_MAP.put("BIGINT UNSIGNED", new String[] {"Long", "java.lang.Long"});
		TYPE_MAP.put("BIT", new String[] {"Boolean", "java.lang.Boolean"});
		TYPE_MAP.put("BLOB", new String[] {"byte[]", "java.lang.byte[]"});
		TYPE_MAP.put("CHAR", new String[] {"String", "java.lang.String"});
		TYPE_MAP.put("DATE", new String[] {"LocalDateTime", "java.time.LocalDateTime"});
		TYPE_MAP.put("DATETIME", new String[] {"LocalDateTime", "java.time.LocalDateTime"});
		TYPE_MAP.put("DECIMAL", new String[] {"BigDecimal", "java.math.BigDecimal"});
		TYPE_MAP.put("DOUBLE", new String[] {"Double", "java.lang.Double"});
		TYPE_MAP.put("FLOAT", new String[] {"Float", "java.lang.Float"});
		TYPE_MAP.put("INT", new String[] {"Integer", "java.lang.Integer"});
		TYPE_MAP.put("INT UNSIGNED", new String[] {"Integer", "java.lang.Integer"});
		TYPE_MAP.put("INTEGER", new String[] {"Integer", "java.lang.Integer"});
		TYPE_MAP.put("INTEGER UNSIGNED", new String[] {"Long", "java.lang.Integer"});
		TYPE_MAP.put("MEDIUMINT", new String[] {"Integer", "java.lang.Integer"});
		TYPE_MAP.put("MEDIUMINT UNSIGNED", new String[] {"Integer", "java.lang.Integer"});
		TYPE_MAP.put("SMALLINT", new String[] {"Integer", "java.lang.Integer"});
		TYPE_MAP.put("SMALLINT UNSIGNED", new String[] {"Integer", "java.lang.Integer"});
		TYPE_MAP.put("TINYINT", new String[] {"Integer", "java.lang.Integer"});
		TYPE_MAP.put("TINYINT UNSIGNED", new String[] {"Integer", "java.lang.Integer"});
		TYPE_MAP.put("TIMESTAMP", new String[] {"LocalDateTime", "java.time.LocalDateTime"});
		TYPE_MAP.put("VARCHAR", new String[] {"String", "java.lang.String"});
	}

	private String name;

	private String comment;

	private List<Column> columns;

	private Column primaryKey;

	public Table(String name, String comment, List<Column> columns) {
		this.name = name;
		this.comment = comment;
		this.columns = columns;
		this.primaryKey = columns.stream().filter(Column::isPrimaryKey).findFirst().orElseGet(() -> null);
	}

	public Table(DbTable t) {
		this.name = t.getName();
		this.comment = t.getComment();
		JBIterable<? extends DasColumn> cols = DasUtil.getColumns(t);
		if (cols.isEmpty()) {
			return;
		}
		this.columns = new ArrayList<>();
		for (DasColumn col : cols) {
			String lowerUnderscoreName = col.getName();
			String lowerCamelName = StrUtil.lowerScore2LowerCamel(lowerUnderscoreName);
			String upperCamelName = StrUtil.lowerScore2UpperCamel(lowerUnderscoreName);
			String dbType = col.getDataType().typeName.toUpperCase();
			String comment = col.getComment();
			boolean primary = DasUtil.isPrimary(col);
			String[] jdkType = TYPE_MAP.get(dbType);
			if (null == jdkType) {
				throw new IllegalArgumentException("JdbcType[" + dbType + "] and JdkType mapping not support.");
			}
			this.columns.add(new Column(lowerUnderscoreName, lowerCamelName, upperCamelName, dbType, jdkType[0], jdkType[1], comment, primary));
		}
		this.primaryKey = this.columns.stream().filter(Column::isPrimaryKey).findFirst().orElseGet(() -> null);
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public Column getPrimaryKey() {
		return primaryKey;
	}
}
