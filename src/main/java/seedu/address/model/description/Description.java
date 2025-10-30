package seedu.address.model.description;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a description (for anything).
 */
public class Description {
    public static final String MESSAGE_CONSTRAINTS =
            "Description should not be empty and should not have leading or trailing whitespaces";

    public final String value;

    /**
     * Constructs a {@code Description}
     *
     * @param desc A valid description.
     */
    public Description(String desc) {
        requireNonNull(desc);
        checkArgument(isValidDescription(desc), MESSAGE_CONSTRAINTS);
        value = desc;
    }

    /**
     * Returns true if a given string is a valid description.
     * The description should not be blank, and should not contain leading or trailing whitespaces.
     */
    public static boolean isValidDescription(String test) {
        return test.trim().equals(test) && !test.trim().isEmpty();
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
        if (!(other instanceof Description)) {
            return false;
        }

        Description otherDesc = (Description) other;
        return value.equals(otherDesc.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
