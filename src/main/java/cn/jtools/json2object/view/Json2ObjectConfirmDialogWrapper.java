package cn.jtools.json2object.view;

import cn.jtools.json2object.anactions.FieldNode;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

/**
 * @author liujun
 */
public class Json2ObjectConfirmDialogWrapper extends DialogWrapper {

    private final Json2ObjectConfirmView.ConfirmModel confirmModel;

    public Json2ObjectConfirmDialogWrapper(Json2ObjectConfirmView.ConfirmModel confirmModel){
        super(true);

        this.confirmModel = confirmModel;
        init();
        setSize(800,500);
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return new Json2ObjectConfirmView(confirmModel);
    }


}
