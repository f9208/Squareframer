package base.fx;

import base.utils.wrapers.PercentWrapper;
import base.utils.wrapers.RectangularWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private static final String EVERYTHING_IS_VALID = "Все валидное, можно начинать";
    private static final String DEFAULT = "Выберите файл и укажите размер рамки";
    @FXML
    private ColorPicker colorChoice;
    @FXML
    @Getter
    private Label status;
    @FXML
    private TextField sizePX;
    @FXML
    private TextField sizePercent;
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
        if (sizePX.getText().isEmpty() && sizePercent.getText().isEmpty()) {
            status.setText("Выберите размер поля");
        }
        //todo как то не очень красиво по отношению к смене статуса
        return listFiles;
    }

    public void startConvert() {
        if (!sizePX.getText().isEmpty() && sizePercent.getText().isEmpty()) {
            double frameSize = readSize(sizePX);
            if (!Validator.checkListFile(status, listFiles) || !Validator.sizeFrame(status, frameSize)) return;
            status.setText(EVERYTHING_IS_VALID);
            Thread convertThread = new Thread(() -> converter.convert(listFiles, frameSize, frameSize, new RectangularWrapper(readColor(getColorValue()))));
            convertThread.start();
            convertButton.setDisable(true);
        }
        if (!sizePercent.getText().isEmpty() && sizePX.getText().isEmpty()) {
            double frameSize = readSize(sizePercent);
            if (!Validator.checkListFile(status, listFiles) || !Validator.sizeFrame(status, frameSize)) return;
            status.setText(EVERYTHING_IS_VALID);
            Thread convertThread = new Thread(() -> converter.convert(listFiles, frameSize, frameSize, new PercentWrapper(readColor(getColorValue()))));
            convertThread.start();
            convertButton.setDisable(true);
        }
    }

    private double readSize(TextField textField) {
        String read = textField.getText().trim();
        double result;
        try {
            result = Double.parseDouble(read);
        } catch (NumberFormatException e) {
            result = -1;
        }
        return result;
    }

    public void resetFields() {
        status.setText(DEFAULT);
        sizePX.setText("");
        sizePercent.setText("");
        listFiles = new ArrayList<>();
        listViewPhotoNames.getItems().clear();
        colorChoice.setValue(Color.WHITE);
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
