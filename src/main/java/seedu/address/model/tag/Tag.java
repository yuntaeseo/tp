package seedu.address.model.tag;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String ID_MESSAGE_CONSTRAINTS = "Tags ids must be positive integers";

    /** Keep track of the largest tag ID in the application. */
    private static int largestId = 0;

    private final int id;
    private final TagName name;
    private final TagDesc desc;
    private final TagColor color;

    /**
     * Constructs a {@code Tag}.
     *
     * Every field must be present and not null.
     */
    public Tag(int id, TagName name, TagDesc desc, TagColor color) {
        requireAllNonNull(id, name, desc, color);
        largestId = Math.max(largestId, id);
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.color = color;
    }

    /**
     * Constructs a {@code Tag}, without needing to provide an ID.
     * The ID will automatically be deduced from {@code largestId}.
     */
    public Tag(TagName name, TagDesc desc, TagColor color) {
        this(largestId + 1, name, desc, color);
    }

    public int getId() {
        return id;
    }

    public TagName getName() {
        return name;
    }

    public TagDesc getDesc() {
        return desc;
    }

    public TagColor getColor() {
        return color;
    }

    /**
     * Returns true if a given string is a valid tag ID.
     */
    public static boolean isValidTagId(String test) {
        try {
            Integer i = Integer.parseInt(test);
            return i > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if both tags have the same name.
     * This defines a weaker notion of equality between two tags.
     */
    public boolean isSameTag(Tag otherTag) {
        if (otherTag == this) {
            return true;
        }

        return otherTag != null && otherTag.getName().equals(getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return name.equals(otherTag.name)
                && desc.equals(otherTag.desc)
                && color.equals(otherTag.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, color);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", id)
                .add("name", name)
                .add("description", desc)
                .add("color", color)
                .toString();
    }

}
