package base.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class FileManager {
    public List<File> openFiles() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenMultipleDialog(new Stage());
    }

    public ObservableList<String> prepareObservableList(List<File> files) {
        ObservableList<String> result = FXCollections.observableArrayList();
        files.stream().map(File::getName).forEach(result::add);
        return result;
    }
}
