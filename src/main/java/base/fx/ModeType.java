package base.fx;

public enum ModeType {
    EQUALS("���������� ����"), DIFFERENT("������ ����"), POLAROID("��������");
    String text;

    ModeType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
