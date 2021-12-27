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
import java.util.Objects;

import static base.fx.Status.*;

public class MainController {
    @FXML
    private ColorPicker colorChoice;
    @FXML
    @Getter
    private Label statusBar;
    @FXML
    private TextField sizeFrame;
    @FXML
    private ListView<String> listViewPhotoNames;
    @FXML
    @Getter
    private Button convertButton;
    @FXML
    private ChoiceBox<String> typeFrame; //если сразу вставить enum - по дефолту чекбокса отображается как пустая

    public void initialize() {
        statusBar.setText(DEFAULT);
    }

    private List<File> listFiles;
    FileManager manager = new FileManager();
    ConverterBindFx converter = new ConverterBindFx(this);

    public void getFiles() {
        List<File> openedFiles = manager.openFiles();
        if (openedFiles != null) {
            listFiles = openedFiles;
            listViewPhotoNames.setItems(manager.prepareObservableList(listFiles));
        }

        String message = Validator.checkSizeAndFiles(sizeFrame.getText(), listFiles);
        if (!message.isEmpty()) {
            statusBar.setText(message);
        } else {
            statusBar.setText(EVERYTHING_IS_VALID);
        }
    }

    public void startConvert() {
        String message = Validator.checkSizeAndFiles(sizeFrame.getText().trim(), listFiles);
        if (!message.isEmpty()) {
            statusBar.setText(message);
            return;
        }
        double frameSize = readSize(sizeFrame);
        DifferenceType type = DifferenceType.readType(typeFrame.getValue());
        Thread convertThread;
        switch (Objects.requireNonNull(type)) {
            case PIXEL: {
                convertThread = new Thread(() -> converter.convert(listFiles, frameSize, frameSize, new RectangularWrapper(readColor(getColorValue()))));
                convertThread.start();
                break;
            }
            case PERCENT: {
                convertThread = new Thread(() -> converter.convert(listFiles, frameSize, frameSize, new PercentWrapper(readColor(getColorValue()))));
                convertThread.start();
                break;
            }
            default:
                return;
        }
        convertButton.setDisable(true);
    }

    //предполагается что значения приходят численно валидные
    private double readSize(TextField textField) {
        String read = textField.getText().trim();
        return Double.parseDouble(read);
    }

    @FXML
    private void resetFields() {
        statusBar.setText(DEFAULT);
        sizeFrame.setText("");
        listFiles = new ArrayList<>();
        listViewPhotoNames.getItems().clear();
    }

    public Color getColorValue() {
        return colorChoice.getValue();
    }

    private java.awt.Color readColor(Color sourceColor) {
        return new java.awt.Color((float) sourceColor.getRed(),
                (float) sourceColor.getGreen(),
                (float) sourceColor.getBlue(),
                (float) sourceColor.getOpacity());
    }
}
