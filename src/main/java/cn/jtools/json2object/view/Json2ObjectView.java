package cn.jtools.json2object.view;

import cn.jtools.json2object.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * @author liujun
 */
public class Json2ObjectView extends JPanel {

    private EditModel editModel;
    private ObjectMapper objectMapper;
    private JLabel label;

    public Json2ObjectView(EditModel editModel){
        this.editModel = editModel;
        objectMapper = new ObjectMapper();

        setLayout(new BorderLayout());

        JTextArea jTextArea = new JTextArea();
        jTextArea.setColumns(100);
        jTextArea.setRows(40);

        jTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                editModel.setJsonContext(jTextArea.getText());
                checkJson(jTextArea, editModel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                editModel.setJsonContext(jTextArea.getText());
                checkJson(jTextArea, editModel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                editModel.setJsonContext(jTextArea.getText());
                checkJson(jTextArea, editModel);
            }
        });

        add(jTextArea, BorderLayout.CENTER);

        label = new JLabel();
        add(label, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void checkJson(JTextArea jTextArea, EditModel editModel) {
        String error = JsonUtils.checkJson(jTextArea.getText());
        editModel.setExitAndOk(error == null);
        label.setText(error == null?"success":error);
        label.setForeground(error == null?Color.GREEN:Color.RED);
    }


    @Getter
    @Setter
    public static class EditModel{
        private String jsonContext;
        private boolean isExitAndOk;

        public String getJsonContext() {
            return jsonContext;
        }

        public void setJsonContext(String jsonContext) {
            this.jsonContext = jsonContext;
        }

        public boolean isExitAndOk() {
            return isExitAndOk;
        }

        public void setExitAndOk(boolean exitAndOk) {
            isExitAndOk = exitAndOk;
        }
    }
}
