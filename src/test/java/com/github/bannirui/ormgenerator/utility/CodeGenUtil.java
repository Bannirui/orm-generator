package com.github.bannirui.ormgenerator.utility;

import com.github.bannirui.ormgenerator.bean.Column;
import com.github.bannirui.ormgenerator.bean.Table;
import com.github.bannirui.ormgenerator.config.DaoProfile;
import com.github.bannirui.ormgenerator.config.MapperProfile;
import com.github.bannirui.ormgenerator.config.ModelProfile;
import com.github.bannirui.ormgenerator.constant.FreemakerTemplateMgr;
import freemarker.template.Template;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import org.apache.commons.lang3.StringUtils;

public class CodeGenUtil {

	private static final String MODEL_PACKAGE_NAME = "model_package_name";
	private static final String DAO_PACKAGE_NAME = "dao_package_name";
	private static final String TABLE_NAME = "table_name";
	private static final String TABLE_COMMENT = "table_comment";
	private static final String DATE = "date";
	private static final String AUTHOR = "author";
	private static final String CLASS_NAME = "class_name";
	private static final String DAO_CLASS_NAME_SUFFIX = "dao_class_name_suffix";
	private static final String PRIMARY_KEY = "primary_key";
	private static final String COLUMNS = "columns";

	private static final String SYS_USER = "USER";

	public static void genDao(Table t, DaoProfile profile) {
		Map<String, Object> data = new HashMap<>();
		data.put(DAO_PACKAGE_NAME, profile.getDaoPackageName());
		data.put(MODEL_PACKAGE_NAME, profile.getModelPackageName());
		data.put(TABLE_COMMENT, t.getComment());
		data.put(DATE, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		data.put(AUTHOR, System.getenv().get(SYS_USER));
		String className = StrUtil.lowerScore2UpperCamel(t.getName());
		data.put(CLASS_NAME, className);
		data.put(DAO_CLASS_NAME_SUFFIX, FreemakerTemplateMgr.DAO_CLASS_SUFFIX);
		Column primaryKey = null;
		if (Objects.nonNull(primaryKey = t.getPrimaryKey())) {
			data.put(PRIMARY_KEY, primaryKey);
		}
		data.put(COLUMNS, t.getColumns());
		String[] err = new String[1];
		boolean b = genFile(FreemakerTemplateMgr.DAO_TEMPLATE_NAME,
				profile.getSrcDir(),
				className + FreemakerTemplateMgr.DAO_TEMPLATE_SUFFIX,
				data,
				s -> err[0] = s);
		if (!b) {
			System.out.println("Dao gen failed, err={" + err[0] + "}");
		}
	}

	public static void genMapper(Table t, MapperProfile profile) {
		Map<String, Object> data = new HashMap<>();
		data.put(DAO_PACKAGE_NAME, profile.getDaoPackageName());
		data.put(MODEL_PACKAGE_NAME, profile.getModelPackageName());
		data.put(DAO_CLASS_NAME_SUFFIX, FreemakerTemplateMgr.DAO_CLASS_SUFFIX);
		String className = StrUtil.lowerScore2UpperCamel(t.getName());
		data.put(CLASS_NAME, className);
		data.put(TABLE_NAME, t.getName());
		Column primaryKey = null;
		if (Objects.nonNull(primaryKey = t.getPrimaryKey())) {
			data.put(PRIMARY_KEY, primaryKey);
		}
		data.put(COLUMNS, t.getColumns());
		String[] err = new String[1];
		boolean b = genFile(FreemakerTemplateMgr.MAPPER_TEMPLATE_NAME,
				profile.getSrcDir() + File.separator + profile.getMapperDirectoryName(),
				className + FreemakerTemplateMgr.MAPPER_TEMPLATE_SUFFIX,
				data,
				s -> err[0] = s);
		if (!b) {
			System.out.println("Dao gen failed, err={" + err[0] + "}");
		}
	}

	public static void genModel(Table t, ModelProfile modelProfile) {
		Map<String, Object> data = new HashMap<>();
		data.put(MODEL_PACKAGE_NAME, modelProfile.getPackageName());
		data.put(TABLE_COMMENT, t.getComment());
		data.put(DATE, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		data.put(AUTHOR, System.getenv().get(SYS_USER));
		String className = StrUtil.lowerScore2UpperCamel(t.getName());
		data.put(CLASS_NAME, className);
		data.put(COLUMNS, t.getColumns());
		String[] err = new String[1];
		boolean b = genFile(FreemakerTemplateMgr.MODEL_TEMPLATE_NAME,
				modelProfile.getSrcDir(),
				className + FreemakerTemplateMgr.MODEL_TEMPLATE_SUFFIX,
				data,
				s -> err[0] = s);
		if (!b) {
			System.out.println("Model gen failed, err={" + err[0] + "}");
		}
	}

	/**
	 * @param templateName freemarker template name
	 * @param fileDir      dest file dir, absolute path
	 * @param fileName     absolute file path is fileDir/fileName
	 * @param data         data bound to freemarker template
	 */
	private static boolean genFile(String templateName, String fileDir, String fileName, Map<String, Object> data, Consumer<String> err) {
		Template template = null;
		try {
			template = FreemarkerUtil.getTemplate(templateName);
		} catch (Exception e) {
			err.accept(e.getMessage());
		}
		if (Objects.isNull(template)) {
			return false;
		}
		String filePath = fileDir + File.separator + fileName;
		if (StringUtils.isBlank(fileDir) || StringUtils.isBlank(filePath)) {
			err.accept("Pls specify file path, dir and name.");
			return false;
		}
		File dir = new File(fileDir);
		if (!dir.exists() || !dir.isDirectory()) {
			if (!dir.mkdirs()) {
				err.accept("Create dir[" + fileDir + "] failed.");
				return false;
			}
		}
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				if (!file.createNewFile()) {
					err.accept("Create file[" + filePath + "] failed.");
					return false;
				}
			} catch (IOException e) {
				err.accept("Create file[" + filePath + "] err, err={" + e.getMessage() + "}");
				return false;
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(file);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
			template.process(data, out);
		} catch (Exception e) {
			err.accept("File op exception, err={" + e.getMessage() + "}");
			return false;
		}
		return true;
	}
}
