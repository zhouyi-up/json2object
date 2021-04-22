package cn.jtools.json2object.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * @author liujun
 */
public class Json2ObjectView extends JPanel {

    private EditModel editModel;

    public Json2ObjectView(EditModel editModel){
        this.editModel = editModel;

        setLayout(new BorderLayout());

        JTextArea jTextArea = new JTextArea();
        jTextArea.setColumns(100);
        jTextArea.setRows(40);

        jTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                editModel.setJsonContext(jTextArea.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                editModel.setJsonContext(jTextArea.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                editModel.setJsonContext(jTextArea.getText());
            }
        });

        add(jTextArea);
        setVisible(true);
    }


    public static class EditModel{
        private String jsonContext;

        public String getJsonContext() {
            return jsonContext;
        }

        public void setJsonContext(String jsonContext) {
            this.jsonContext = jsonContext;
        }
    }
}
