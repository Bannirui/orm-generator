package com.github.bannirui.ormgenerator.dialog;

import com.github.bannirui.ormgenerator.bean.Table;
import com.github.bannirui.ormgenerator.config.Profile;
import com.github.bannirui.ormgenerator.freemarker.impl.DaoGenerator;
import com.github.bannirui.ormgenerator.freemarker.impl.MapperGenerator;
import com.github.bannirui.ormgenerator.freemarker.impl.ModelGenerator;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.java.JavaSourceRootType;

public class EntryDialog extends DialogWrapper {

	private static final int COMPONENT_GAP_5PX = 5;
	private static final int COMPONENT_GAP_40PX = 40;
	private static final int COMPONENT_GAP_400PX = 400;

	private static final int LABEL_WIDTH = 150;
	private static final int LABEL_HEIGHT = 60;
	private static final int TF_COLS = 50;
	private static final int COMBO_BOX_WIDTH = 60;

	// project's module
	private Deque<Module> modules;
	private Map<String, Module> moduleMap;

	private JPanel panel;

	private JLabel moduleLabel;

	private ComboBox<String> moduleComboBox;

	private String[] packages = {"Model package", "Dao package", "Mapper package"};
	private String[] dirs = {"Model source dir", "Dao source dir", "Mapper resource dir"};

	private JLabel[] labels;

	private JTextField[] tfs;

	// Global profile
	private Profile profile;

	private Project project;
	private Table table;

	public EntryDialog(Project p, Table t) {
		super(p);
		this.project = p;
		this.table = t;
		// list all modules of cur project
		this.modules = new ArrayDeque<>();
		this.moduleMap = new HashMap<>();
		this.panel = new JPanel(new BorderLayout());
		this.moduleLabel = new JLabel("Module name");
		this.moduleComboBox = new ComboBox<>(COMBO_BOX_WIDTH);
		this.labels = new JLabel[6];
		for (int i = 0; i < 3; i++) {
			JLabel l1 = new JLabel(this.packages[i]);
			l1.setPreferredSize(new Dimension(this.LABEL_WIDTH, this.LABEL_HEIGHT));
			this.labels[2 * i] = l1;
			JLabel l2 = new JLabel(this.dirs[i]);
			l2.setPreferredSize(new Dimension(this.LABEL_WIDTH, this.LABEL_HEIGHT));
			this.labels[2 * i + 1] = l2;
		}
		this.tfs = new JTextField[6];
		for (int i = 0; i < 6; i++) {
			this.tfs[i] = new JTextField(this.TF_COLS);
		}
		this.initProjModules(p);
		// panel layout
		this.initPanel();
		this.initData();
		super.init();
		super.setTitle(t.getName());
	}

	private void initProjModules(Project p) {
		Module[] modules = ModuleManager.getInstance(p).getModules();
		for (Module m : modules) {
			// source directory: src/main/java, order the modules
			if (CollectionUtils.isEmpty(ModuleRootManager.getInstance(m).getSourceRoots(JavaSourceRootType.SOURCE))) {
				this.modules.addFirst(m);
			} else {
				this.modules.addLast(m);
			}
			this.moduleMap.put(m.getName(), m);
		}
	}

	private void initPanel() {
		this.panel.add(this.moduleBox(), BorderLayout.NORTH);
		this.panel.add(this.dataBox());
	}

	private Box moduleBox() {
		Box box = Box.createHorizontalBox();
		this.moduleLabel.setPreferredSize(new Dimension(this.LABEL_WIDTH, this.LABEL_HEIGHT));
		box.add(this.moduleLabel);
		// 间隔
		box.add(Box.createHorizontalStrut(COMPONENT_GAP_400PX));
		this.moduleComboBox.setSwingPopup(false);
		this.moduleComboBox.addActionListener(e -> {
			String moduleName = (String) EntryDialog.this.moduleComboBox.getSelectedItem();
			if (StringUtils.isBlank(moduleName)) {
				return;
			}
			EntryDialog.this.moduleSelected(moduleName);
		});
		box.add(this.moduleComboBox);
		return box;
	}

	private Box dataBox() {
		Box box = Box.createVerticalBox();
		for (int i = 0; i < 3; i++) {
			// 组件间隔
			box.add(Box.createVerticalStrut(COMPONENT_GAP_40PX));
			box.add(this.packageAndDir(i));
		}
		return box;
	}

	private Box packageAndDir(int i) {
		Box box = Box.createVerticalBox();
		// package
		box.add(this.labelAndText(2 * i));
		// 组件间隔
		box.add(Box.createVerticalStrut(COMPONENT_GAP_5PX));
		// dir
		box.add(this.labelAndText(2 * i + 1));
		return box;
	}

	private Box labelAndText(int i) {
		Box box = Box.createHorizontalBox();
		// label
		box.add(this.labels[i]);
		box.add(Box.createHorizontalStrut(COMPONENT_GAP_40PX));
		// text field
		box.add(this.tfs[i]);
		return box;
	}

	private void initData() {
		this.profile = new Profile();
		for (Module module : this.modules) {
			this.moduleComboBox.addItem(module.getName());
		}
	}

	private void moduleSelected(String moduleName) {
		Module m = null;
		if (StringUtils.isBlank(moduleName) || Objects.isNull(m = this.moduleMap.get(moduleName))){
			return;
		}
		VirtualFile vf = ProjectUtil.guessModuleDir(m);
		if (Objects.isNull(vf)) {
			return;
		}
		// module path, like, /Users/dingrui/MyDev/code/java/plugin-test/ma
		String path = vf.getPath();
		String src = path + "/src/main/java";
		String resource = path + "/src/main/resources";
		// model
		this.tfs[0].setText("com.model");
		this.tfs[1].setText(src);
		// dao
		this.tfs[2].setText("com.dao");
		this.tfs[3].setText(src);
		// mapper
		this.tfs[4].setText("com.mapper");
		this.tfs[5].setText(resource);
	}

	@Override
	protected @Nullable JComponent createCenterPanel() {
		this.pack();
		this.panel.setVisible(true);
		return this.panel;
	}

	@Override
	protected void doOKAction() {
		this.doOK();
		super.doOKAction();
	}

	private void doOK() {
		this.collectProfile();
		new ModelGenerator(this.project, this.table, this.profile).gen();
		new DaoGenerator(this.project, this.table, this.profile).gen();
		new MapperGenerator(this.project, this.table, this.profile).gen();
	}

	private void collectProfile() {
		String moduleName = (String) this.moduleComboBox.getSelectedItem();
		this.profile.setModuleName(moduleName);
		// like, com.model
		String modelPackage = this.tfs[0].getText();
		if (StringUtils.isBlank(modelPackage)) {
			Messages.showWarningDialog("Model package could not be none.", "Notice");
			return;
		}
		this.profile.setModelPackage(modelPackage.replaceAll(" ", ""));
		// like, /Users/dingrui/MyDev/code/java/plugin-test/ma/src/main/java
		String modelSrcDir = this.tfs[1].getText();
		if (StringUtils.isBlank(modelSrcDir)) {
			Messages.showWarningDialog("Model source dir could not be none.", "Notice");
			return;
		}
		this.profile.setModelSrcDir(modelSrcDir.replaceAll(" ", ""));
		// like, com.dao
		String daoPackage = this.tfs[2].getText();
		if (StringUtils.isBlank(daoPackage)) {
			Messages.showWarningDialog("Dao package could not be none.", "Notice");
			return;
		}
		this.profile.setDaoPackage(daoPackage.replaceAll(" ", ""));
		// like, /Users/dingrui/MyDev/code/java/plugin-test/ma/src/main/java
		String daoSrcDir = this.tfs[3].getText();
		if (StringUtils.isBlank(daoSrcDir)) {
			Messages.showWarningDialog("Dao source dir could not be none.", "Notice");
			return;
		}
		this.profile.setDaoSrcDir(daoSrcDir.replaceAll("f", ""));
		// like, com.mapper
		String mapperDir = this.tfs[4].getText();
		if (StringUtils.isBlank(mapperDir)) {
			Messages.showWarningDialog("Mapper directory could not be none.", "Notice");
			return;
		}
		this.profile.setMapperDir(mapperDir.replaceAll(" ", ""));
		// like, /Users/dingrui/MyDev/code/java/plugin-test/ma/src/main/resources
		String mapperResourcesDir = this.tfs[5].getText();
		if (StringUtils.isBlank(mapperResourcesDir)) {
			Messages.showWarningDialog("Mapper resource directory could not be none.", "Notice");
			return;
		}
		this.profile.setMapperResourcesDir(mapperResourcesDir.replaceAll(" ", ""));
	}
}
