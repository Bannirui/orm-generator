package com.github.bannirui.ormgenerator.utility;

import com.github.bannirui.ormgenerator.bean.Column;
import com.github.bannirui.ormgenerator.bean.Table;
import com.github.bannirui.ormgenerator.config.DaoProfile;
import com.github.bannirui.ormgenerator.config.MapperProfile;
import com.github.bannirui.ormgenerator.config.ModelProfile;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

public class CodeGenUtilTest extends TestCase {

	private static final Table t;

	static {
		List<Column> cols = new ArrayList<>();
		cols.add(new Column("id", "id", "BIGINT", "Long", "java.lang.Long", "primary key", true));
		cols.add(new Column("user_name", "userName", "VARCHAR", "String", "java.lang.String", "user name"));
		cols.add(new Column("user_age", "userAge", "INT", "Integer", "java.lang.Integer", "user age"));
		t = new Table("test_table", "test table's comment", cols);
	}

	public void testGenDAO() {
		DaoProfile profile = new DaoProfile("test-module",
				"com.github.bannirui.ormgenerator.model",
				"com.github.bannirui.ormgenerator.dao",
				"/home/dingrui/MyDev/code/java/orm-generator/src/main/java/com/github/bannirui/ormgenerator/dao"
		);
		CodeGenUtil.genDao(t, profile);
	}

	public void testGenMapper() {
		MapperProfile profile = new MapperProfile("test-module",
				"com.github.bannirui.ormgenerator.model",
				"com.github.bannirui.ormgenerator.dao",
				"mapper",
				"/home/dingrui/MyDev/code/java/orm-generator/src/main/resources"
		);
		CodeGenUtil.genMapper(t, profile);
	}

	public void testGenModel() {
		ModelProfile profile = new ModelProfile("test-module",
				"com.github.bannirui.ormgenerator.model",
				"/home/dingrui/MyDev/code/java/orm-generator/src/main/java/com/github/bannirui/ormgenerator/model"
		);
		CodeGenUtil.genModel(t, profile);
	}
}