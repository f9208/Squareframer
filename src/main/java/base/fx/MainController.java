package base.fx;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private final static String EVERYTHING_IS_VALID = "Все валидное, можно начинать";
    private static final String DEFAULT = "Выберите файл и укажите размер рамки";
    @FXML
    private ColorPicker colorChoice;
    @FXML
    private Label status;
    @FXML
    private TextArea sizeArea;
    @FXML
    private ListView<String> viewPhotoNames;

    private List<File> listFiles;
    FileManager manager = new FileManager();
    ConverterBindFx converter = new ConverterBindFx(this);

    public List<File> getFiles() {
        listFiles = manager.openFiles();
        if (listFiles != null) {
            viewPhotoNames.setItems(manager.prepareObservableList(listFiles));
            cleanLabel(status);
        }
        return listFiles;
    }

    public void startConvert() {
        int frameSize = readSize(sizeArea);
        if (!Validator.checkListFile(status, listFiles) || !Validator.sizeFrame(status, frameSize)) return;
        status.setText(EVERYTHING_IS_VALID);
        Thread convertThread = new Thread(() -> converter.convert(listFiles, frameSize, readColor(getColorValue())));
        convertThread.start();

    }

    private int readSize(TextArea textField) {
        String read = textField.getText().trim();
        int result;
        try {
            result = Integer.parseInt(read);
        } catch (NumberFormatException e) {
            result = -1;
        }
        return result;
    }

    public void resetFields() {
        status.setText(DEFAULT);
        sizeArea.setText("");
        listFiles = new ArrayList<>();
        viewPhotoNames.getItems().clear();
        colorChoice.setValue(Color.WHITE);
    }

    private void cleanLabel(Label label) {
        label.setText("");
    }

    public Label getStatus() {
        return status;
    }

    public Color getColorValue() {
        return colorChoice.getValue();
    }

    public java.awt.Color readColor(Color sourceColor) {
        return new java.awt.Color((float) sourceColor.getRed(),
                (float) sourceColor.getGreen(),
                (float) sourceColor.getBlue(),
                (float) sourceColor.getOpacity());
    }
}
