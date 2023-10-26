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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.java.JavaSourceRootType;

public class EntryDialog extends DialogWrapper {

	// project's module
	private Deque<Module> modules;
	private Map<String, Module> moduleMap;

	private JPanel mainPanel;

	// module
	private ComboBox<Object> moduleComboBox;

	// model
	private JTextField modelPackageVal;
	private JTextField modelSrcDirVal;

	// dao
	private JTextField daoPackageVal;
	private JTextField daoSrcDirVal;

	// mapper
	private JTextField mapperDirVal;
	private JTextField mapperResourcesDir;

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
		this.initModules(p);
		// panel
		this.initPanel();
		this.initData();
		super.init();
		super.setTitle("GEN INVOKE->" + t.getName());
	}

	private void initModules(Project p) {
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
		this.mainPanel = new JPanel(new GridLayout(7, 1));
		// module
		this.mainPanel.add(this.initModulePanel(), 0);
		// placeholder
		this.mainPanel.add(this.initPlaceholderPanel(), 1);
		// model
		this.mainPanel.add(this.initModelPanel(), 2);
		this.mainPanel.add(this.initPlaceholderPanel(), 3);
		// dao
		this.mainPanel.add(this.initDaoPanel(), 4);
		this.mainPanel.add(this.initPlaceholderPanel(), 5);
		// mapper
		this.mainPanel.add(this.initMapperPanel(), 6);
	}

	private JPanel initModulePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel();
		label.setText("Module:");
		panel.add(label, BorderLayout.WEST);
		moduleComboBox = new ComboBox<>();
		moduleComboBox.setSwingPopup(false);
		moduleComboBox.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				String moduleName = (String) moduleComboBox.getSelectedItem();
				if (StringUtils.isBlank(moduleName)) {
					return;
				}
				EntryDialog.this.selectModule(moduleName);
			}
		});
		panel.add(moduleComboBox, BorderLayout.EAST);
		return panel;
	}

	private JPanel initModelPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JLabel modelPackageAttr = new JLabel();
		modelPackageAttr.setText("Model package:");
		panel.add(modelPackageAttr, 0);
		this.modelPackageVal = new JTextField();
		panel.add(this.modelPackageVal, 1);
		JLabel modelSrcDirAttr = new JLabel();
		modelSrcDirAttr.setText("Model source dir:");
		panel.add(modelSrcDirAttr, 2);
		this.modelSrcDirVal = new JTextField();
		panel.add(this.modelSrcDirVal, 3);
		return panel;
	}

	private JPanel initDaoPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JLabel daoPackageAttr = new JLabel();
		daoPackageAttr.setText("Dao package:");
		panel.add(daoPackageAttr, 0);
		this.daoPackageVal = new JTextField();
		panel.add(this.daoPackageVal, 1);
		JLabel daoSrcDirAttr = new JLabel();
		daoSrcDirAttr.setText("Dao source dir:");
		panel.add(daoSrcDirAttr, 2);
		this.daoSrcDirVal = new JTextField();
		panel.add(this.daoSrcDirVal, 3);
		return panel;
	}

	private JPanel initMapperPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JLabel mapperDirAttr = new JLabel();
		mapperDirAttr.setText("Mapper dir:");
		panel.add(mapperDirAttr, 0);
		this.mapperDirVal = new JTextField();
		panel.add(this.mapperDirVal, 1);
		JLabel mapperResourcesDirAttr = new JLabel();
		mapperResourcesDirAttr.setText("Mapper resources dir:");
		panel.add(mapperResourcesDirAttr, 2);
		this.mapperResourcesDir = new JTextField();
		panel.add(this.mapperResourcesDir, 3);
		return panel;
	}

	private JPanel initPlaceholderPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		return panel;
	}

	private void initData() {
		this.profile = new Profile();
		for (Module module : this.modules) {
			this.moduleComboBox.addItem(module.getName());
		}
		// TODO: 10/25/23 Assign default value for text field
	}

	private void selectModule(String moduleName) {
		if (StringUtils.isBlank(moduleName)) {
			return;
		}
		Module m = this.moduleMap.get(moduleName);
		VirtualFile vf = ProjectUtil.guessModuleDir(m);
		if (Objects.isNull(vf)) {
			return;
		}
		String path = vf.getPath();
		this.modelPackageVal.setText("com.model");
		this.modelSrcDirVal.setText(path);
		this.daoPackageVal.setText("com.dao");
		this.daoSrcDirVal.setText(path);
		this.mapperDirVal.setText("mapper");
		this.mapperResourcesDir.setText(path);
	}

	@Override
	protected @Nullable JComponent createCenterPanel() {
		return this.mainPanel;
	}

	@Override
	protected void doOKAction() {
		this.doOK();
		super.doOKAction();
	}

	private void doOK() {
		this.collectProfile();
		// TODO: 10/25/23 freemarker
		new ModelGenerator(this.project, this.table, this.profile).gen();
		new DaoGenerator(this.project, this.table, this.profile).gen();
		new MapperGenerator(this.project, this.table, this.profile).gen();
	}

	private void collectProfile() {
		// Collect user profile
		// Just suggest assign module
		String moduleName = (String) this.moduleComboBox.getSelectedItem();
		this.profile.setModuleName(moduleName);
		String modelPackage = this.modelPackageVal.getText();
		if (StringUtils.isBlank(modelPackage)) {
			Messages.showWarningDialog("Model package could not be none.", "Notice");
			return;
		}
		this.profile.setModelPackage(modelPackage.replaceAll(" ", ""));
		String modelSrcDir = this.modelSrcDirVal.getText();
		if (StringUtils.isBlank(modelSrcDir)) {
			Messages.showWarningDialog("Model source dir could not be none.", "Notice");
			return;
		}
		this.profile.setModelSrcDir(modelSrcDir.replaceAll(" ", ""));
		String daoPackage = this.daoPackageVal.getText();
		if (StringUtils.isBlank(daoPackage)) {
			Messages.showWarningDialog("Dao package could not be none.", "Notice");
			return;
		}
		this.profile.setDaoPackage(daoPackage.replaceAll(" ", ""));
		String daoSrcDir = this.daoSrcDirVal.getText();
		if (StringUtils.isBlank(daoSrcDir)) {
			Messages.showWarningDialog("Dao source dir could not be none.", "Notice");
			return;
		}
		this.profile.setDaoSrcDir(daoSrcDir.replaceAll("f", ""));
		String mapperDir = this.mapperDirVal.getText();
		if (StringUtils.isBlank(mapperDir)) {
			Messages.showWarningDialog("Mapper directory could not be none.", "Notice");
			return;
		}
		this.profile.setMapperDir(mapperDir.replaceAll(" ", ""));
		String mapperResourcesDir = this.mapperResourcesDir.getText();
		if (StringUtils.isBlank(mapperResourcesDir)) {
			Messages.showWarningDialog("Mapper resource directory could not be none.", "Notice");
			return;
		}
		this.profile.setMapperResourcesDir(mapperResourcesDir.replaceAll(" ", ""));
	}
}
