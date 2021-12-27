package base.fx;

public enum DifferenceType {
    PIXEL("pixel"), PERCENT("percent");
    String text;

    DifferenceType(String name) {
        text = name;
    }

    @Override
    public String toString() {
        return text;
    }
    public static DifferenceType readType(String text) {
        for (DifferenceType t : DifferenceType.values()) {
            if (t.text.equalsIgnoreCase(text)) {
                return t;
            }
        }
        return null;
    }
}
