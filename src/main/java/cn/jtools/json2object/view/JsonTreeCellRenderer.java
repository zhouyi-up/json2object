package cn.jtools.json2object.view;

import cn.jtools.json2object.enums.FieldType;
import cn.jtools.json2object.model.JsonTypeModel;
import cn.jtools.json2object.utils.JsonUtils;
import com.intellij.icons.AllIcons;
import com.intellij.ui.ColoredTreeCellRenderer;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author liujun
 */
public class JsonTreeCellRenderer extends ColoredTreeCellRenderer {

    @Override
    public void customizeCellRenderer(@NotNull JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        if (value instanceof DefaultMutableTreeNode){
            if (value instanceof JsonRootTreeNode){
                append("Root");
                setIcon(AllIcons.FileTypes.Json);
                return;
            }
            if (value instanceof JsonTreeNode){
                JsonTreeNode jsonTreeNode = (JsonTreeNode) value;
                JsonTypeModel jsonTypeModel = jsonTreeNode.getJsonTypeModel();
                String displayName = "";
                if (StringUtils.isEmpty(jsonTypeModel.getTypeName())){
                    displayName = "- :" + jsonTypeModel.getType().getTypeName();
                }else {
                    displayName = jsonTypeModel.getTypeName() + " : " + jsonTypeModel.getType().getTypeName();
                }
                append(displayName);
                setIcon(AllIcons.FileTypes.JsonSchema);
            }
        }
    }
}
