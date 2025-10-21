package seedu.address.model.tag;

import seedu.address.commons.util.ColorUtil;

/**
 * Represents a text's color in RGB format.
 */
public class TextColor {
    public final String value;

    public TextColor(String value) {
        this.value = value;
    }

    /**
     * Gets the {@code TextColor} to be displayed based on {@code TagColor}.
     * @return {@code TextColor} that represents white/black color.
     */
    public static TextColor fromTagColor(TagColor backgroundColor) {
        return new TextColor(ColorUtil.getTextColor(backgroundColor.value));
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
