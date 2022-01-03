package base.fx;

import base.utils.wrapers.IFrameWrapper;
import base.utils.wrapers.PercentToPixelWrapper;
import base.utils.wrapers.RectangularWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static base.fx.MeasureType.PIXEL;
import static base.fx.ModeType.*;
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
    private ChoiceBox<MeasureType> measure;
    @FXML
    private ChoiceBox<ModeType> mode;
    @FXML
    private TextField dTop;
    @FXML
    private TextField dBottom;
    @FXML
    private TextField dLeft;
    @FXML
    private TextField dRight;

    public void initialize() {
        statusBar.setText(DEFAULT);
        mode.setValue(EQUALS);
        measure.setValue(PIXEL);
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

    public void startConvert() { //todo причесать названия переменных
        ModeType modeType = mode.getValue();
        final double left;
        final double right;
        final double top;
        final double bottom;
        double fixedFrameSize;
        if (modeType == EQUALS || modeType == POLAROID) {
            String message = Validator.checkSizeAndFiles(sizeFrame.getText().trim(), listFiles);
            if (!message.isEmpty()) {
                statusBar.setText(message);
                return;
            }
            fixedFrameSize = readSize(sizeFrame);
            left = fixedFrameSize;
            right = fixedFrameSize;
            top = fixedFrameSize;
            bottom = modeType == EQUALS ? fixedFrameSize : 3 * fixedFrameSize;
        } else if (modeType == DIFFERENT) {
            String message = Validator.checkDFields();
            if (!message.isEmpty()) {
                statusBar.setText(message);
            }
            left = Double.parseDouble(dLeft.getText());
            right = Double.parseDouble(dRight.getText());
            top = Double.parseDouble(dTop.getText());
            bottom = Double.parseDouble(dBottom.getText());
        }else {return;}

        MeasureType type = measure.getValue();
        final IFrameWrapper wrapper;
        wrapper = new RectangularWrapper(readColor(getColorValue()));
        Thread convertThread;
        switch (Objects.requireNonNull(type)) { //todo практически дублирование кода
            case PIXEL: {
                convertThread = new Thread(() -> converter.convert(listFiles, wrapper, left, right, top, bottom));
                convertThread.start();
                break;
            }
            case PERCENT: {
                convertThread = new Thread(() -> converter.convert(listFiles, new PercentToPixelWrapper(wrapper), left, right, top, bottom));
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
        dLeft.setText("");
        dRight.setText("");
        dTop.setText("");
        dBottom.setText("");
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

    public void modeControl() {
        if (mode.getValue() == ModeType.DIFFERENT) {
            dTop.setVisible(true);
            dBottom.setVisible(true);
            dLeft.setVisible(true);
            dRight.setVisible(true);
            sizeFrame.setVisible(false);

        } else {
            dTop.setVisible(false);
            dBottom.setVisible(false);
            dLeft.setVisible(false);
            dRight.setVisible(false);
            sizeFrame.setVisible(true);
        }
    }
}
