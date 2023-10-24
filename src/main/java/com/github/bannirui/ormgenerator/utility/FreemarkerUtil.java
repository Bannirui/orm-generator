package com.github.bannirui.ormgenerator.utility;

import com.github.bannirui.ormgenerator.constant.FreemakerTemplateMgr;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import java.io.IOException;

public class FreemarkerUtil {

	private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);

	static {
		// resource path, src/main/resources/templates/
		CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(FreemarkerUtil.class, FreemakerTemplateMgr.TEMPLATE_DIR));
		CONFIGURATION.setDefaultEncoding("UTF-8");
		CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
	}

	private FreemarkerUtil() {
	}

	public static Template getTemplate(String templateName) throws IOException {
		return CONFIGURATION.getTemplate(templateName);
	}

	public static void clearCache() {
		CONFIGURATION.clearTemplateCache();
	}
}
