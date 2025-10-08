package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Tag's description.
 */
public class TagDesc {
    public final String value;

    /**
     * Constructs a {@code TagDesc}
     *
     * @param tagDesc A valid tag description.
     */
    public TagDesc(String tagDesc) {
        requireNonNull(tagDesc);
        value = tagDesc;
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
        if (!(other instanceof TagDesc)) {
            return false;
        }

        TagDesc otherTagDesc = (TagDesc) other;
        return value.equals(otherTagDesc.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
