package selenium.enums;

public enum Title {
    MR(1),
    MS(2),
    MRS(3);

    public final int num;

    Title(int num) {
        this.num = num;
    }

    public static Title getByValue(String value) {
        for (Title title : Title.values()) {
            if (title.name().equalsIgnoreCase(value) ||
                    String.valueOf(title.num).equalsIgnoreCase(value)) {
                return title;
            }
        }
        return null;
    }
}
