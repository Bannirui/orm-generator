package com.github.bannirui.ormgenerator.freemarker;

import com.github.bannirui.ormgenerator.constant.FreemakerTemplateMgr;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import java.io.IOException;

public class FreemarkerConfiguration extends Configuration {

	protected static final String EN_CODING = "UTF-8";

	public FreemarkerConfiguration() {
		this(FreemakerTemplateMgr.TEMPLATE_DIR);
	}

	private FreemarkerConfiguration(String resourcesDir) {
		super(Configuration.VERSION_2_3_22);
		super.setDefaultEncoding(EN_CODING);
		super.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		super.setCacheStorage(NullCacheStorage.INSTANCE);
		// resource path, src/main/resources/templates/
		super.setTemplateLoader(new ClassTemplateLoader(FreemarkerConfiguration.class, resourcesDir));
	}

	public Template getTemplate(String templateName) throws IOException {
		return super.getTemplate(templateName);
	}
}
