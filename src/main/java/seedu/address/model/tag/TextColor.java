package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.ColorUtil;

/**
 * Represents a text's color in RGB format.
 */
public class TextColor {
    public static final String MESSAGE_CONSTRAINTS = "Text colors should be in HEX format (without the '#'): RRGGBB";

    /**
     * Regex for enforcing the HEX format (without the '#' at the beginning).
     */
    public static final String VALIDATION_REGEX = "^(?:[0-9a-fA-F]{3}){1,2}$";

    public final String value;

    /**
     * Constructs a {@code TextColor}.
     *
     * @param value a valid RGB value.
     */
    public TextColor(String value) {
        requireNonNull(value);
        checkArgument(isValidTextColor(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Gets the {@code TextColor} to be displayed based on {@code TagColor}.
     * @return {@code TextColor} that represents white/black color.
     */
    public static TextColor fromTagColor(TagColor backgroundColor) {
        return new TextColor(ColorUtil.getTextColor(backgroundColor.value));
    }

    /**
     * Returns true if a given string is a valid text color.
     */
    public static boolean isValidTextColor(String test) {
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
        if (!(other instanceof TextColor)) {
            return false;
        }

        TextColor otherTextColor = (TextColor) other;
        return value.equals(otherTextColor.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
