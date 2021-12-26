package base.fx;

import javafx.scene.control.Label;

import java.io.File;
import java.util.List;

public class Validator {
    private final static int MAX_FRAME_SIZE = 10000;
    private static final String TOO_BIG = "�������� ���� ������� �������! ������� ����� �� 10000";
    private static final String NOT_POSITIVE = "������� ������������� ����� �����!";

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
            status.setText("������� �������� ����!");
            return false;
        }
        return true;
    }
}
