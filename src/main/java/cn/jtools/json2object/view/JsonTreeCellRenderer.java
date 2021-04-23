package cn.jtools.json2object.view;

import com.intellij.icons.AllIcons;
import com.intellij.ui.ColoredTreeCellRenderer;
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
            }
            if (value instanceof JsonTreeNode){
                JsonTreeNode jsonTreeNode = (JsonTreeNode) value;
                append(jsonTreeNode.getFieldNode().getName() + " : " + jsonTreeNode.getFieldNode().getType());
                setIcon(AllIcons.FileTypes.JsonSchema);
            }
        }
    }
}
