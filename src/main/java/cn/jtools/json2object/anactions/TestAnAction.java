package cn.jtools.json2object.anactions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;

public class TestAnAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        WriteCommandAction.runWriteCommandAction(e.getProject(), ()-> {

        });
    }
}
