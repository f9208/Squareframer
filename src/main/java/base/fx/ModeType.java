package base.fx;

public enum ModeType {
    EQUALS("одинаковые поля"), DIFFERENT("разные поля"), POLAROID("полароид");
    String text;

    ModeType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
