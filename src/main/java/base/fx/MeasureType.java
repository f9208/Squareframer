package base.fx;

public enum MeasureType {
    PIXEL("pixel"), PERCENT("percent");
    String text;

    MeasureType(String name) {
        text = name;
    }

    @Override
    public String toString() {
        return text;
    }
    public static MeasureType readType(String text) {
        for (MeasureType t : MeasureType.values()) {
            if (t.text.equalsIgnoreCase(text)) {
                return t;
            }
        }
        return null;
    }
}
