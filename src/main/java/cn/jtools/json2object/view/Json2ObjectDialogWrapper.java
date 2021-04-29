package cn.jtools.json2object.view;

import com.intellij.openapi.externalSystem.model.task.event.DefaultOperationResult;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author liujun
 */
public class Json2ObjectDialogWrapper extends DialogWrapper {

    private final Json2ObjectView.EditModel editModel;

    public Json2ObjectDialogWrapper(Json2ObjectView.EditModel editModel){
        super(false);

        this.editModel = editModel;

        setTitle("Json To Object");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return new Json2ObjectView(editModel);
    }

    @Override
    protected void doOKAction() {
        if (editModel.isExitAndOk()){
            super.doOKAction();
            return;
        }
        new ConfirmDialogWrapper().show();
    }
}
