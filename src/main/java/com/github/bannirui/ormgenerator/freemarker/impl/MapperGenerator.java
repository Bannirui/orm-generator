package com.github.bannirui.ormgenerator.freemarker.impl;

import com.github.bannirui.ormgenerator.bean.Column;
import com.github.bannirui.ormgenerator.bean.Table;
import com.github.bannirui.ormgenerator.config.Profile;
import com.github.bannirui.ormgenerator.constant.FreemakerTemplateMgr;
import com.github.bannirui.ormgenerator.freemarker.AbstractCodeGenerator;
import com.github.bannirui.ormgenerator.utility.StrUtil;
import com.intellij.openapi.project.Project;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapperGenerator extends AbstractCodeGenerator {

	private Project project;
	private Table table;
	private Profile profile;

	public MapperGenerator(Project project, Table table, Profile profile) {
		this.project = project;
		this.profile = profile;
		this.table = table;
	}

	@Override
	protected void doGen() {
		Map<String, Object> data = new HashMap<>();
		data.put(DAO_PACKAGE_NAME, this.profile.getDaoPackage());
		data.put(MODEL_PACKAGE_NAME, this.profile.getModelPackage());
		data.put(DAO_CLASS_NAME_SUFFIX, FreemakerTemplateMgr.DAO_CLASS_SUFFIX);
		String className = StrUtil.lowerScore2UpperCamel(this.table.getName());
		data.put(CLASS_NAME, className);
		data.put(TABLE_NAME, this.table.getName());
		Column primaryKey = null;
		if (Objects.nonNull(primaryKey = this.table.getPrimaryKey())) {
			data.put(PRIMARY_KEY, primaryKey);
		}
		data.put(COLUMNS, this.table.getColumns());
		String dir = this.profile.getMapperResourcesDir();
		String path = dir + File.separator + this.profile.getMapperDir().replaceAll("\\.", File.separator);
		String[] err = new String[1];
		boolean b = super.genFile(
				project,
				FreemakerTemplateMgr.MAPPER_TEMPLATE_NAME,
				path,
				className + FreemakerTemplateMgr.MAPPER_TEMPLATE_SUFFIX,
				data,
				s -> err[0] = s);
		if (!b) {
			// TODO: 10/25/23 msg
			System.out.println("Dao gen failed, err={" + err[0] + "}");
		}
	}
}
