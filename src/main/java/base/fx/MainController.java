package base.fx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import lombok.Getter;

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
    private TextField sizeField;
    @FXML
    private ListView<String> listViewPhotoNames;
    @FXML
    @Getter
    private Button convertButton;
    private List<File> listFiles;
    FileManager manager = new FileManager();
    ConverterBindFx converter = new ConverterBindFx(this);

    public List<File> getFiles() {
        listFiles = manager.openFiles();
        if (listFiles != null) {
            listViewPhotoNames.setItems(manager.prepareObservableList(listFiles));
        }
        if (sizeField.getText().isEmpty()) {
            status.setText("Выберите размер поля");
        }
        //todo как то не очень по отношению к смене статуса
        return listFiles;
    }

    public void startConvert() {
        int frameSize = readSize(sizeField);
        if (!Validator.checkListFile(status, listFiles) || !Validator.sizeFrame(status, frameSize)) return;
        status.setText(EVERYTHING_IS_VALID);
        Thread convertThread = new Thread(() -> converter.convert(listFiles, frameSize, readColor(getColorValue())));
        convertThread.start();
        convertButton.setDisable(true);
    }

    private int readSize(TextField textField) {
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
        sizeField.setText("");
        listFiles = new ArrayList<>();
        listViewPhotoNames.getItems().clear();
        colorChoice.setValue(Color.WHITE);
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
