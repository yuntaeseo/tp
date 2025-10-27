package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a color in HEX format.
 */
public class Color {
    public static final String MESSAGE_CONSTRAINTS = "Colors should be in HEX format (without the '#'): RRGGBB";

    /**
     * Regex for enforcing the HEX format (without the '#' at the beginning).
     */
    public static final String VALIDATION_REGEX = "^(?:[0-9a-fA-F]{6})$";

    public final String value;

    /**
     * Constructs a {@code Color}
     *
     * @param color A valid color.
     */
    public Color(String color) {
        requireNonNull(color);
        checkArgument(isValidColor(color), MESSAGE_CONSTRAINTS);
        value = color;
    }

    /**
     * Returns true if a given string is a valid color.
     */
    public static boolean isValidColor(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.toUpperCase();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Color)) {
            return false;
        }

        Color otherColor = (Color) other;
        return value.equals(otherColor.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
