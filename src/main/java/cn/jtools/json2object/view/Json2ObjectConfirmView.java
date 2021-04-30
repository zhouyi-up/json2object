package cn.jtools.json2object.view;

import cn.jtools.json2object.enums.FieldType;
import cn.jtools.json2object.model.JsonTypeModel;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * @author liujun
 */
public class Json2ObjectConfirmView extends JPanel {

    private final static Vector<String> TITLE_VECTOR = new Vector<>();

    Tree tree;
    JsonRootTreeNode jsonRootTreeNode;

    private final Json2ObjectConfirmView.ConfirmModel confirmModel;

    public Json2ObjectConfirmView(ConfirmModel confirmModel){
        this.confirmModel = confirmModel;

        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(10);
        borderLayout.setHgap(10);
        setLayout(borderLayout);

        initTreePanel(confirmModel);
        initSettingPanel(confirmModel);

        setVisible(true);
    }

    /**
     * 初始化json树
     * @param confirmModel
     */
    private void initTreePanel(ConfirmModel confirmModel) {
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BorderLayout());

        DefaultActionGroup defaultActionGroup = new DefaultActionGroup();
        AnAction expAll = new AnAction(AllIcons.Actions.Expandall) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                expandAll(new TreePath(jsonRootTreeNode), true);
            }
        };
        defaultActionGroup.add(expAll);

        AnAction collapseall = new AnAction(AllIcons.Actions.Collapseall) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {
                expandAll(new TreePath(jsonRootTreeNode), false);
            }
        };
        defaultActionGroup.add(collapseall);

        ActionManager instance = ActionManager.getInstance();
        JComponent jsonTree = instance.createActionToolbar("JsonTree", defaultActionGroup, true)
                .getComponent();

        dataPanel.add(jsonTree, BorderLayout.NORTH);


        jsonRootTreeNode = new JsonRootTreeNode();
        initTree(jsonRootTreeNode, confirmModel.getJsonTypeModel());
        tree = new Tree(jsonRootTreeNode);
        tree.setCellRenderer(new JsonTreeCellRenderer());
        JBScrollPane jbScrollPane = new JBScrollPane(tree);

        dataPanel.add(jbScrollPane, BorderLayout.CENTER);
        add(dataPanel, BorderLayout.CENTER);

    }

    /**
     * 初始化勾选选项
     * @param confirmModel
     */
    private void initSettingPanel(ConfirmModel confirmModel) {
        JPanel settingPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(10, 1);
        gridLayout.setVgap(10);

        settingPanel.setLayout(gridLayout);

        //base
        JPanel basePanel = new JPanel();
        GridLayout panelGridLayout = new GridLayout(2, 3);
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


        JCheckBox toStringCheck = new JCheckBox("toString()");
        toStringCheck.addItemListener(e -> {

        });
        basePanel.add(toStringCheck);

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

        add(settingPanel, BorderLayout.EAST);
    }

    /**
     * 初始化树结构
     * @param jsonTreeNode
     * @param jsonTypeModel
     */
    public void initTree(JsonTreeNode jsonTreeNode, JsonTypeModel jsonTypeModel){
        List<JsonTypeModel> childList = jsonTypeModel.getChildList();
        for (JsonTypeModel typeModel : childList) {
            FieldType type = typeModel.getType();
            JsonTreeNode child;
            if (type == FieldType.ARR || type == FieldType.OBJECT){
                child = new JsonTreeNode(typeModel, false);
                initTree(child, typeModel);
            }else {
                child = new JsonTreeNode(typeModel, false);
            }
            jsonTreeNode.add(child);
        }
    }

    public void expandAll(TreePath path, boolean expand){
        TreeNode lastPathComponent = (TreeNode) path.getLastPathComponent();
        if (lastPathComponent.getChildCount() > 0) {

            for ( Enumeration<? extends TreeNode> children = lastPathComponent.children();children.hasMoreElements();){
                TreeNode treeNode = children.nextElement();
                TreePath treePath = path.pathByAddingChild(treeNode);
                expandAll(treePath, expand);
            }
        }
        if (expand){
            tree.expandPath(path);
        }else {
            tree.collapsePath(path);
        }

    }

    @Getter
    @Setter
    public static class ConfirmModel{
        private JsonTypeModel jsonTypeModel;
        private boolean isLombok;

        public JsonTypeModel getJsonTypeModel() {
            return jsonTypeModel;
        }

        public void setJsonTypeModel(JsonTypeModel jsonTypeModel) {
            this.jsonTypeModel = jsonTypeModel;
        }

        public boolean isLombok() {
            return isLombok;
        }

        public void setLombok(boolean lombok) {
            isLombok = lombok;
        }
    }
}
