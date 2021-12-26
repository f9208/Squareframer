package base.fx;

import javafx.scene.control.Label;

import java.io.File;
import java.util.List;

public class Validator {
    private final static int MAX_FRAME_SIZE = 10000;
    private static final String TOO_BIG = "∆елаемые пол€ слишком большие! ¬ведите число до 10000";
    private static final String NOT_POSITIVE = "¬ведите положительное целое число!";

    public static boolean sizeFrame(Label label, double sizeFrame) {
        if (sizeFrame <= 0) {
            label.setText(NOT_POSITIVE);
            return false;
        }
        if (sizeFrame > MAX_FRAME_SIZE) {
            label.setText(TOO_BIG);
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
