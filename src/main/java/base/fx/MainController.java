package base.fx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.File;
import java.util.List;

public class MainController {
    private final static String EVERYTHING_IS_VALID = "все валидное, можно начинать";

    @FXML
    private Label status;
    @FXML
    private TextArea sizeArea;
    @FXML
    private ListView<String> listPhotoNames;

    private List<File> listFiles;
    FileManager manager = new FileManager();
    ConverterBindFx converter = new ConverterBindFx(this);

    public List<File> loadFiles() {
        listFiles = manager.openFiles();
        if (listFiles != null) {
            listPhotoNames.setItems(manager.prepareObservableList(listFiles));
            cleanStatus();
        }
        return listFiles;
    }

    public void startConvert() {
        int frameSize = readSize(sizeArea);
        if (!Validator.checkListFile(status, listFiles) || !Validator.size(status, frameSize)) return;
        status.setText(EVERYTHING_IS_VALID);
        new Thread(() -> converter.convert(listFiles, frameSize)).start();
    }

    private int readSize(TextArea textField) {
        String read = textField.getText();
        int result;
        try {
            result = Integer.parseInt(read);
        } catch (NumberFormatException e) {
            result = -1;
        }
        return result;
    }

    private void cleanStatus() {
        status.setText("");
    }

    public Label getStatus() {
        return status;
    }
}
