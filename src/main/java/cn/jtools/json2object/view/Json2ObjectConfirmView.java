package cn.jtools.json2object.view;

import cn.jtools.json2object.anactions.FieldNode;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.ui.JBValue;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
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
        settingPanel.setBorder(BorderFactory.createTitledBorder("setting"));
        settingPanel.setLayout(new GridLayout(10,1));

        JCheckBox lombokCheckBox = new JCheckBox("Lombok");
        lombokCheckBox.addItemListener(event -> {
            confirmModel.setLombok(ItemEvent.SELECTED == event.getStateChange());
        });
        settingPanel.add(lombokCheckBox);
        add(settingPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Getter
    @Setter
    public static class ConfirmModel{
        private List<FieldNode> fieldNodes;
        private boolean isLombok;

        public List<FieldNode> getFieldNodes() {
            return fieldNodes;
        }

        public void setFieldNodes(List<FieldNode> fieldNodes) {
            this.fieldNodes = fieldNodes;
        }

        public boolean isLombok() {
            return isLombok;
        }

        public void setLombok(boolean lombok) {
            isLombok = lombok;
        }
    }
}
