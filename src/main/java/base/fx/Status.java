package base.fx;

public class Status {
    public static final String TOO_BIG = "Желаемые поля слишком большие! Введите число до 10000";
    public static final String NOT_POSITIVE = "Введите положительное число";
    public static final String CHOICE_FILES = "Выберете файлы изображений";
    public static final String EVERYTHING_IS_VALID = "Все валидное, можно начинать";
    public static final String PUT_FRAME_SIZE = "Укажите размер полей";
    public static final String NOT_A_NUMBER = "В размере рамки указывайте только числа";
    public static final String DEFAULT = CHOICE_FILES + "\n" + PUT_FRAME_SIZE;

    private Status() {
    }
}