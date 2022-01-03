package base.fx;

import java.io.File;
import java.util.List;

import static base.fx.Status.*;

public class Validator {
    private static final int MAX_FRAME_SIZE = 10000;

    private Validator() {
    }

    public static String checkSizeFrame(String sizeFrame) {
        if (sizeFrame.trim().isEmpty()) return PUT_FRAME_SIZE;
        double doubleSize;
        try {
            doubleSize = Double.parseDouble(sizeFrame);
        } catch (NumberFormatException e) {
            return NOT_A_NUMBER;
        }
        if (doubleSize <= 0) {
            return NOT_POSITIVE;
        }
        if (doubleSize > MAX_FRAME_SIZE) {
            return TOO_BIG;
        }
        return null;
    }

    public static String checkSizeAndFiles(String sizeFrame, List<File> listFiles) {
        StringBuilder result = new StringBuilder();
        if (listFiles == null || listFiles.isEmpty()) {
            result.append(CHOICE_FILES);
        }
        String message = checkSizeFrame(sizeFrame);
        if (message != null) {
            result.append("\n").append(message);
        }
        return result.toString();
    }

    public static String checkDFields() {
        System.out.println("валидирую поля");
        return "";
    }

}
