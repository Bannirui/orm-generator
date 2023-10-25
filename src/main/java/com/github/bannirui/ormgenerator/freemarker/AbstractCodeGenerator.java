package com.github.bannirui.ormgenerator.freemarker;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import freemarker.template.Template;
import java.io.File;
import java.io.StringWriter;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import org.apache.commons.lang3.StringUtils;

public abstract class AbstractCodeGenerator implements CodeGenerator {

	protected static final String EN_CODING = "UTF-8";

	protected static final String DATE = "date";
	protected static final String AUTHOR = "author";
	protected static final String SYS_USER = "USER";
	protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	protected static final String CLASS_NAME = "class_name";

	protected static final String MODEL_PACKAGE_NAME = "model_package_name";
	protected static final String DAO_PACKAGE_NAME = "dao_package_name";

	protected static final String DAO_CLASS_NAME_SUFFIX = "dao_class_name_suffix";

	protected static final String TABLE_NAME = "table_name";
	protected static final String TABLE_COMMENT = "table_comment";
	protected static final String PRIMARY_KEY = "primary_key";
	protected static final String COLUMNS = "columns";

	private static final FreemarkerConfiguration FREEMARKER_CONFIGURATION;

	static {
		FREEMARKER_CONFIGURATION = new FreemarkerConfiguration();
	}

	@Override
	public void gen() {
		this.doGen();
	}

	protected abstract void doGen();

	/**
	 * @param project      current project
	 * @param templateName freemarker template name
	 * @param fileDir      dest file dir, absolute path
	 * @param fileName     absolute file path is fileDir/fileName
	 * @param viewModel    viewModel bound to freemarker template
	 * @param err          err msg
	 */
	public boolean genFile(Project project, String templateName, String fileDir, String fileName, Map<String, Object> viewModel,
						   Consumer<String> err) {
		String filePath = fileDir + File.separator + fileName;
		if (StringUtils.isBlank(fileDir) || StringUtils.isBlank(filePath)) {
			err.accept("Pls specify file path, directory and name.");
			return false;
		}
		File dir = new File(fileDir);
		if (!dir.exists() || !dir.isDirectory()) {
			if (!dir.mkdirs()) {
				err.accept("Create dir[" + fileDir + "] failed.");
				return false;
			}
		}
		VirtualFile vf = Objects.requireNonNull(LocalFileSystem.getInstance().refreshAndFindFileByPath(fileDir));
		WriteCommandAction.runWriteCommandAction(project, () -> {
			try {
				VirtualFile data = vf.createChildData(this, fileName);
				StringWriter sw = new StringWriter();
				Template template = FREEMARKER_CONFIGURATION.getTemplate(templateName);
				template.process(viewModel, sw);
				data.setBinaryContent(sw.toString().getBytes(EN_CODING));
			} catch (Exception e) {
				// TODO: 10/25/23 cur thread is async, determine if signal the err msg to main/user
				err.accept("File relevant operation exception, err={" + e.getMessage() + "}");
			}
		});
		return true;
	}
}
