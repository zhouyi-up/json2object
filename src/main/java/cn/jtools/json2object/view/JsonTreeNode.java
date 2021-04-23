package cn.jtools.json2object.view;

import cn.jtools.json2object.anactions.FieldNode;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author liujun
 */
public class JsonTreeNode extends DefaultMutableTreeNode {

    private FieldNode fieldNode;

    public JsonTreeNode(FieldNode fieldNode){
        super(fieldNode.getName() + ":" + fieldNode.getType());
        this.fieldNode = fieldNode;
    }

    public FieldNode getFieldNode() {
        return fieldNode;
    }

    public void setFieldNode(FieldNode fieldNode) {
        this.fieldNode = fieldNode;
    }
}
