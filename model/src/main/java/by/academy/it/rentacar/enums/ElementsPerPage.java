package by.academy.it.rentacar.enums;

/**
 * Created by Nata on 02.06.2016.
 */
public enum ElementsPerPage {
    TREE(3), FIVE(5), TEN(10);

    private int countElements;

    ElementsPerPage(final int countElements) {
        this.countElements = countElements;
    }

    public int getValue() {
        return countElements;
    }

    public static ElementsPerPage fromValue(int value) {
        ElementsPerPage count = null;
        for (ElementsPerPage counts : values()) {
            if (counts.getValue() == value) {
                count = counts;
            }
        }
        return count;
    }

    public String value() {
        return name();
    }

    public String getName() {
        return this.name();
    }

    @Override
    public String toString() {
        return this.name();
    }
}
