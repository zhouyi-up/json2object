package cn.jtools.json2object.view;

import cn.jtools.json2object.anactions.FieldNode;
import cn.jtools.json2object.model.JsonTypeModel;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author liujun
 */
public class JsonTreeNode extends DefaultMutableTreeNode {

    private JsonTypeModel jsonTypeModel;
    private boolean isRoot;

    public JsonTreeNode(JsonTypeModel jsonTypeModel, boolean isRoot){
        super(jsonTypeModel);
        this.jsonTypeModel = jsonTypeModel;
        this.isRoot = isRoot;
    }

    public JsonTypeModel getJsonTypeModel() {
        return jsonTypeModel;
    }

    public void setJsonTypeModel(JsonTypeModel jsonTypeModel) {
        this.jsonTypeModel = jsonTypeModel;
    }

    @Override
    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }
}
