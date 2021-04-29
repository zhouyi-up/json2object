package cn.jtools.json2object.view;

import cn.jtools.json2object.anactions.FieldNode;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
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
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(10);
        borderLayout.setHgap(10);
        setLayout(borderLayout);

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
        GridLayout gridLayout = new GridLayout(10, 1);
        gridLayout.setVgap(10);

        settingPanel.setLayout(gridLayout);

        //base
        JPanel basePanel = new JPanel();
        GridLayout panelGridLayout = new GridLayout(1, 10);
        panelGridLayout.setHgap(10);

        basePanel.setLayout(panelGridLayout);
        basePanel.setBorder(BorderFactory.createTitledBorder("Base"));
        JCheckBox getCheck = new JCheckBox("get()");
        getCheck.addItemListener(event -> {

        });
        basePanel.add(getCheck);
        JCheckBox setCheck = new JCheckBox("set()");
        setCheck.addItemListener(event -> {

        });
        basePanel.add(setCheck);
        settingPanel.add(basePanel);

        // Lombok
        JPanel lombokPanel = new JPanel();
        lombokPanel.setBorder(BorderFactory.createTitledBorder("Lombok"));
        lombokPanel.setLayout(panelGridLayout);
        JCheckBox lombokCheckBox = new JCheckBox("Data");
        lombokCheckBox.addItemListener(event -> {
            confirmModel.setLombok(ItemEvent.SELECTED == event.getStateChange());
        });
        lombokPanel.add(lombokCheckBox);
        settingPanel.add(lombokPanel);

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
