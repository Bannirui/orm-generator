package com.github.bannirui.ormgenerator.action;

import com.github.bannirui.ormgenerator.bean.Table;
import com.github.bannirui.ormgenerator.dialog.EntryDialog;
import com.intellij.database.psi.DbTable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import java.util.Objects;

public class OrmGenAct extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent e) {
		PsiElement ele = e.getData(LangDataKeys.PSI_ELEMENT);
		if (Objects.isNull(ele) || !(ele instanceof DbTable t)) {
			Messages.showWarningDialog("You should at least select one table.", "Notice");
			return;
		}
		Table table = new Table(t);
		new EntryDialog(e.getProject(), table).show();
	}
}
