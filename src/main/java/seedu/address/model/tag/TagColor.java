package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag's color in HEX format.
 */
public class TagColor {
    public static final String MESSAGE_CONSTRAINTS = "Tags colors should be in HEX format: RRGGBB";
    public static final String VALIDATION_REGEX = "^(?:[0-9a-fA-F]{3}){1,2}$";

    public final String value;

    /**
     * Constructs a {@code TagColor}
     *
     * @param tagColor A valid tag color.
     */
    public TagColor(String tagColor) {
        requireNonNull(tagColor);
        checkArgument(isValidTagColor(tagColor), MESSAGE_CONSTRAINTS);
        value = tagColor;
    }

    /**
     * Returns true if a given string is a valid tag color.
     */
    public static boolean isValidTagColor(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagColor)) {
            return false;
        }

        TagColor otherTagColor = (TagColor) other;
        return value.equals(otherTagColor.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
