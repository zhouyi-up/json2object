package cn.jtools.json2object.view;

import cn.jtools.json2object.anactions.FieldNode;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.ui.JBValue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

/**
 * @author liujun
 */
public class Json2ObjectConfirmView extends JPanel {

    private final static Vector<String> TITLE_VECTOR = new Vector<>();

    Tree tree;

    private final Json2ObjectConfirmView.ConfirmModel confirmModel;

    public Json2ObjectConfirmView(ConfirmModel confirmModel){
        this.confirmModel = confirmModel;
        setLayout(new BorderLayout());

        JsonRootTreeNode jsonRootTreeNode = new JsonRootTreeNode();
        if (confirmModel.getFieldNodes() != null && !confirmModel.getFieldNodes().isEmpty()){
            confirmModel.getFieldNodes().forEach(f -> {
                jsonRootTreeNode.add(new JsonTreeNode(f));
            });
        }

        tree = new Tree(jsonRootTreeNode);
        tree.setCellRenderer(new JsonTreeCellRenderer());
        JBScrollPane jbScrollPane = new JBScrollPane(tree);

        add(jbScrollPane, BorderLayout.WEST);


        JPanel settingPanel = new JPanel();
        settingPanel.setLayout(new GridLayout(10,1));

        JCheckBox jCheckBox = new JCheckBox("Lombok");
        settingPanel.add(jCheckBox);
        add(settingPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static class ConfirmModel{
        private List<FieldNode> fieldNodes;

        public List<FieldNode> getFieldNodes() {
            return fieldNodes;
        }

        public void setFieldNodes(List<FieldNode> fieldNodes) {
            this.fieldNodes = fieldNodes;
        }
    }
}
