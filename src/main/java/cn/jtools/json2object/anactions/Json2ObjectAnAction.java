package cn.jtools.json2object.anactions;

import cn.jtools.json2object.model.JsonTypeModel;
import cn.jtools.json2object.utils.JsonUtils;
import cn.jtools.json2object.view.Json2ObjectConfirmDialogWrapper;
import cn.jtools.json2object.view.Json2ObjectConfirmView;
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

        JsonTypeModel jsonTypeModel = new JsonTypeModel();

        String jsonContext = editModel.getJsonContext();
        if (StringUtils.isNotEmpty(jsonContext)){
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode jsonNode = mapper.readTree(jsonContext);
                JsonUtils.generate(jsonNode,jsonTypeModel);
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
                //TODO
                return;
            }
        }
        Json2ObjectConfirmView.ConfirmModel confirmModel = new Json2ObjectConfirmView.ConfirmModel();
        confirmModel.setJsonTypeModel(jsonTypeModel);

        Json2ObjectConfirmDialogWrapper json2ObjectConfirmDialogWrapper = new Json2ObjectConfirmDialogWrapper(confirmModel);
        boolean confirm = json2ObjectConfirmDialogWrapper.showAndGet();
        if (confirm){

        }
    }
}
