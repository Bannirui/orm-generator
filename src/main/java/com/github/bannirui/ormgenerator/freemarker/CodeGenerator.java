package com.github.bannirui.ormgenerator.freemarker;

import com.github.bannirui.ormgenerator.config.Profile;

public interface CodeGenerator {

	/**
	 * <p>Generate source or resource.</p>
	 * <ul>
	 *     <li>Dal model file</li>
	 *     <li>Dal dao file</li>
	 *     <li>Dal mapper xml file</li>
	 *     <li>...</li>
	 * </ul>
	 */
	void gen();
}
