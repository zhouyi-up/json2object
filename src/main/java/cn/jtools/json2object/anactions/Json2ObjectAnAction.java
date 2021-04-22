package cn.jtools.json2object.anactions;

import cn.jtools.json2object.utils.JsonUtils;
import cn.jtools.json2object.view.Json2ObjectDialogWrapper;
import cn.jtools.json2object.view.Json2ObjectView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ui.popup.PopupComponent;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author liujun
 */
public class Json2ObjectAnAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Json2ObjectView.EditModel editModel = new Json2ObjectView.EditModel();
        Json2ObjectDialogWrapper json2ObjectDialogWrapper = new Json2ObjectDialogWrapper(editModel);
        boolean get = json2ObjectDialogWrapper.showAndGet();
        if (!get){
            return;
        }

        List<FieldNode> fieldNodeList = new ArrayList<>();

        String jsonContext = editModel.getJsonContext();
        System.out.println(jsonContext);
        if (StringUtils.isNotEmpty(jsonContext)){
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode jsonNode = mapper.readTree(jsonContext);
                Iterator<String> stringIterator = jsonNode.fieldNames();
                while (stringIterator.hasNext()){
                    String key = stringIterator.next();
                    JsonNode valueNode = jsonNode.path(key);
                    fieldNodeList.add(new FieldNode(key, JsonUtils.check(valueNode).getTypeName()));
                }
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
                //TODO
            }
        }


    }
}
