package cn.jtools.json2object.view;

import cn.jtools.json2object.anactions.FieldNode;

import javax.swing.*;
import java.util.List;

/**
 * @author liujun
 */
public class Json2ObjectConfirmView extends JPanel {

    private Json2ObjectConfirmView.ConfirmModel confirmModel;

    public Json2ObjectConfirmView(ConfirmModel confirmModel){
        this.confirmModel = confirmModel;

        
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
