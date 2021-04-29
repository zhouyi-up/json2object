package cn.jtools.json2object.view;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author liujun
 */
public class ConfirmDialogWrapper extends DialogWrapper {

    public ConfirmDialogWrapper(){
        super(false);
        init();
        setTitle("Stop");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return new JLabel("Please check your json.");
    }
}
