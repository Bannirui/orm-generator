package com.github.bannirui.ormgenerator.dialog;

import com.github.bannirui.ormgenerator.bean.Table;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.ui.DialogWrapper;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.commons.collections.CollectionUtils;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.java.JavaSourceRootType;

public class EntryDialog extends DialogWrapper {

	// project's module
	private Deque<Module> modules;

	private JPanel mainPanel;

	private void initModules(Project p) {
		Module[] modules = ModuleManager.getInstance(p).getModules();
		for (Module m : modules) {
			// source directory: src/main/java, order the modules
			if (CollectionUtils.isEmpty(ModuleRootManager.getInstance(m).getSourceRoots(JavaSourceRootType.SOURCE))) {
				this.modules.addFirst(m);
			} else {
				this.modules.addLast(m);
			}
		}
	}

	private void initPanel() {

	}

	public EntryDialog(Project p, Table t) {
		super(p);
		super.init();
		super.setTitle("GEN INVOKE->" + t.getName());

		// list all modules of cur project
		this.modules = new ArrayDeque<>();
		this.initModules(p);
		// panel
		this.initPanel();
	}

	@Override
	protected @Nullable JComponent createCenterPanel() {
		JPanel dialogPanel = new JPanel(new BorderLayout());

		JLabel label = new JLabel("testing");
		label.setPreferredSize(new Dimension(100, 100));
		dialogPanel.add(label, BorderLayout.CENTER);

		return dialogPanel;
	}
}
