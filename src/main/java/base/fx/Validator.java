package base.fx;

import javafx.scene.control.Label;

import java.io.File;
import java.util.List;

public class Validator {
    public static boolean size(Label label, int frameSize) {
        if (frameSize <= 0) {
            label.setText("укажите валидный размер полей!");
            return false;
        }
        return true;
    }

    public static boolean checkListFile(Label status, List<File> listFiles) {
        if (listFiles == null || listFiles.isEmpty()) {
            status.setText("вначале выберете файл!");
            return false;
        }
        return true;
    }
}
