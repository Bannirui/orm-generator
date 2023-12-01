package com.github.bannirui.ormgenerator.ui;

import com.github.bannirui.ormgenerator.bean.Column;
import com.github.bannirui.ormgenerator.bean.MyModule;
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
import com.intellij.ui.JBColor;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.java.JavaSourceRootType;

public class EntryDialog extends DialogWrapper {

	private static final int COMPONENT_GAP_5PX = 5;
	private static final int COMPONENT_GAP_10PX = 10;
	private static final int COMPONENT_GAP_20PX = 20;
	private static final int COMPONENT_GAP_25PX = 25;
	private static final int COMPONENT_GAP_40PX = 40;
	private static final int COMPONENT_GAP_83PX = 83;
	private static final int COMPONENT_GAP_500PX = 500;

	private static final int TF_COLS = 50;
	private static final int COMBO_BOX_WIDTH = 120;

	private MainPanel panel;

	// 模块下拉
	private ComboBox<MyModule> moduleCheckBox;

	// 数据库表列名下拉
	private ComboBox<Column> dbPrimaryKeyCb;

	private String[] labelAttrs = {
			"Module Name",
			"column",
			"jdbc type",
			"java type",
			"Model  Package",
			"Model      Dir",
			"Dao    Package",
			"Dao        Dir",
			"Mapper Package",
			"Mapper     Dir"
	};
	private JLabel[] labels;

	private String[] tfAttrs = {
			"jdbc type",
			"java type",
			"Model Package",
			"Model Source Dir",
			"Dao Package",
			"Dao Source Dir",
			"Mapper Package",
			"Mapper Resource Rir"
	};
	private JTextField[] tfs;

	private String[] btnNames = {"...", "...", "..."};

	/**
	 * <ul>
	 *     <li>0 model</li>
	 *     <li>1 dao</li>
	 *     <li>2 mapper</li>
	 * </ul>
	 */
	private JButton[] btns;

	// Global profile
	private Profile profile;

	private Project project;
	private Table table;

	public EntryDialog(Project p, Table t) {
		super(p);
		this.project = p;
		this.table = t;
		this.panel = new MainPanel();
		// panel layout
		this.panel.add(this.initPanel());
		this.initData();
		super.init();
		super.setTitle(t.getName());
	}

	private Box initPanel() {
		int labelSz = this.labelAttrs.length;
		this.labels = new JLabel[labelSz];
		for (int i = 0; i < labelSz; i++) {
			this.labels[i] = new JLabel(this.labelAttrs[i]);
		}
		int tfSz = this.tfAttrs.length;
		this.tfs = new JTextField[tfSz];
		for (int i = 0; i < tfSz; i++) {
			this.tfs[i] = new JTextField(TF_COLS);
		}

		Box box = Box.createVerticalBox();
		// 项目模块
		Box moduleBox = Box.createHorizontalBox();
		moduleBox.setBorder(BorderFactory.createTitledBorder(new LineBorder(JBColor.BLACK), "module info", TitledBorder.LEFT, TitledBorder.TOP));
		this.moduleCheckBox = new ComboBox<>(COMBO_BOX_WIDTH);
		this.moduleCheckBox.setSwingPopup(false);
		this.moduleCheckBox.addActionListener(e -> {
			MyModule module = (MyModule) EntryDialog.this.moduleCheckBox.getSelectedItem();
			if (Objects.isNull(module)) {
				return;
			}
			EntryDialog.this.moduleSelected(module);
		});
		// 项目模块
		Deque<MyModule> modules = new ArrayDeque<>();
		for (Module m : ModuleManager.getInstance(this.project).getModules()) {
			if (CollectionUtils.isEmpty(ModuleRootManager.getInstance(m).getSourceRoots(JavaSourceRootType.SOURCE))) {
				modules.addLast(new MyModule(m.getName(), m));
			} else {
				modules.addFirst(new MyModule(m.getName(), m));
			}
		}
		for (MyModule module : modules) {
			// 显示在下拉框的是实例的toString方法
			this.moduleCheckBox.addItem(module);
		}
		moduleBox.add(Box.createHorizontalStrut(COMPONENT_GAP_5PX));
		moduleBox.add(this.labels[0]);
		moduleBox.add(Box.createHorizontalStrut(COMPONENT_GAP_500PX));
		moduleBox.add(this.moduleCheckBox);
		box.add(moduleBox);
		box.add(Box.createVerticalStrut(COMPONENT_GAP_40PX));
		// 数据表列
		this.dbPrimaryKeyCb = new ComboBox<>(COMBO_BOX_WIDTH);
		this.dbPrimaryKeyCb.addActionListener(e -> {
			Column col = (Column) EntryDialog.this.dbPrimaryKeyCb.getSelectedItem();
			if (Objects.isNull(col)) {
				return;
			}
			String jdbcType = col.getDbType();
			String javaType = col.getJdkType();
			this.tfs[0].setText(jdbcType);
			this.tfs[1].setText(javaType);
		});
		Deque<Column> cols = new ArrayDeque<>();
		for (Column col : this.table.getColumns()) {
			// 推断出primary key放到 队列头 将来放到首个下拉选项
			if (col.isPrimaryKey()) {
				cols.offerFirst(col);
			} else {
				cols.offerLast(col);
			}
		}
		for (Column col : cols) {
			// 在下拉框显示的是column实例的toString方法
			this.dbPrimaryKeyCb.addItem(col);
		}
		// 数据库
		Box dbBox = Box.createHorizontalBox();
		dbBox.setBorder(BorderFactory.createTitledBorder(new LineBorder(JBColor.BLACK), "db info", TitledBorder.LEFT, TitledBorder.TOP));
		Box b1 = Box.createVerticalBox();
		b1.add(this.labels[1]);
		b1.add(Box.createVerticalStrut(COMPONENT_GAP_10PX));
		b1.add(this.dbPrimaryKeyCb);
		dbBox.add(b1);
		Box b2 = Box.createVerticalBox();
		b2.add(this.labels[2]);
		b2.add(Box.createVerticalStrut(COMPONENT_GAP_10PX));
		b2.add(this.tfs[0]);
		dbBox.add(b2);
		Box b3 = Box.createVerticalBox();
		b3.add(this.labels[3]);
		b3.add(Box.createVerticalStrut(COMPONENT_GAP_10PX));
		b3.add(this.tfs[1]);
		dbBox.add(b3);
		box.add(dbBox);
		box.add(Box.createVerticalStrut(COMPONENT_GAP_40PX));
		// 源码路径
		int btnSz = this.btnNames.length;
		this.btns = new JButton[btnSz];
		for (int i = 0; i < btnSz; i++) {
			int cpyi = i;
			this.btns[i] = new JButton(new AbstractAction(this.btnNames[cpyi]) {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 显示文件选择器
					int idx = (cpyi << 1) + 1;
					String prePath = EntryDialog.this.tfs[idx].getText();
					System.out.println(prePath);
					JFileChooser fc = new JFileChooser(prePath);
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int option = fc.showSaveDialog(EntryDialog.this.panel);
					if (option == JFileChooser.APPROVE_OPTION) {
						File f = fc.getSelectedFile();
						String path = f.getAbsolutePath();
						EntryDialog.this.tfs[idx].setText(path);
					}
				}
			});
		}
		Box srcBox = Box.createVerticalBox();
		srcBox.setBorder(BorderFactory.createTitledBorder(new LineBorder(JBColor.BLACK), "src info", TitledBorder.LEFT, TitledBorder.TOP));
		srcBox.add(this.subBox(0));
		srcBox.add(Box.createVerticalStrut(COMPONENT_GAP_20PX));
		srcBox.add(this.subBox(1));
		srcBox.add(Box.createVerticalStrut(COMPONENT_GAP_20PX));
		srcBox.add(this.subBox(2));
		box.add(srcBox);
		return box;
	}

	/**
	 * @param i the box idx
	 *          <ul>
	 *            <li>0 model</li>
	 *            <li>1 dao</li>
	 *            <li>2 mapper</li>
	 *          </ul>
	 */
	private Box subBox(int i) {
		int j = i << 1;
		// 文本框脚标
		int x = j + 2;
		// label脚标
		int y = j + 4;
		Box box = Box.createVerticalBox();
		// package
		Box b1 = Box.createHorizontalBox();
		b1.add(Box.createHorizontalStrut(COMPONENT_GAP_5PX));
		b1.add(this.labels[y]);
		b1.add(Box.createHorizontalStrut(COMPONENT_GAP_5PX));
		b1.add(this.tfs[x]);
		b1.add(Box.createHorizontalStrut(COMPONENT_GAP_83PX));
		// dir
		Box b2 = Box.createHorizontalBox();
		b2.add(Box.createHorizontalStrut(COMPONENT_GAP_5PX));
		b2.add(this.labels[y + 1]);
		b2.add(Box.createHorizontalStrut(COMPONENT_GAP_25PX));
		b2.add(this.tfs[x + 1]);
		b2.add(Box.createHorizontalStrut(COMPONENT_GAP_5PX));
		b2.add(this.btns[i]);

		box.add(b1);
		box.add(Box.createHorizontalStrut(COMPONENT_GAP_10PX));
		box.add(b2);
		return box;
	}

	private void initData() {
		this.profile = new Profile();
		// 项目模块下拉框
	}

	private void moduleSelected(MyModule m) {
		if (Objects.isNull(m)) {
			return;
		}
		VirtualFile vf = ProjectUtil.guessModuleDir(m.getModule());
		if (Objects.isNull(vf)) {
			return;
		}
		// module path, like, /Users/dingrui/MyDev/code/java/plugin-test/ma
		String path = vf.getPath();
		String src = path + "/src/main/java";
		String resource = path + "/src/main/resources";
		// model
		this.tfs[2].setText("com.model");
		this.tfs[3].setText(src);
		// dao
		this.tfs[4].setText("com.dao");
		this.tfs[5].setText(src);
		// mapper
		this.tfs[6].setText("com.mapper");
		this.tfs[7].setText(resource);
	}

	@Override
	protected @Nullable JComponent createCenterPanel() {
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
		MyModule module = (MyModule) this.moduleCheckBox.getSelectedItem();
		this.profile.setModuleName(module.getName());
		int sz = this.tfAttrs.length;
		for (int i = 0; i < sz; i++) {
			if (StringUtils.isBlank(this.tfs[i].getText())) {
				Messages.showWarningDialog(this.tfAttrs[i] + " could not be none.", "Notice");
				return;
			}
		}
		// like, com.model
		String modelPackage = this.tfs[2].getText();
		this.profile.setModelPackage(modelPackage.replaceAll(" ", ""));
		// like, /Users/dingrui/MyDev/code/java/plugin-test/ma/src/main/java
		String modelSrcDir = this.tfs[3].getText();
		this.profile.setModelSrcDir(modelSrcDir.replaceAll(" ", ""));
		// like, com.dao
		String daoPackage = this.tfs[4].getText();
		this.profile.setDaoPackage(daoPackage.replaceAll(" ", ""));
		// like, /Users/dingrui/MyDev/code/java/plugin-test/ma/src/main/java
		String daoSrcDir = this.tfs[5].getText();
		this.profile.setDaoSrcDir(daoSrcDir.replaceAll(" ", ""));
		// like, com.mapper
		String mapperDir = this.tfs[6].getText();
		this.profile.setMapperDir(mapperDir.replaceAll(" ", ""));
		// like, /Users/dingrui/MyDev/code/java/plugin-test/ma/src/main/resources
		String mapperResourcesDir = this.tfs[7].getText();
		this.profile.setMapperResourcesDir(mapperResourcesDir.replaceAll(" ", ""));
	}
}
