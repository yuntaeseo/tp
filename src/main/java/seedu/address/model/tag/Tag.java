package seedu.address.model.tag;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.id.Id;
import seedu.address.model.id.IdManager;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {
    private static final IdManager idManager = new IdManager();

    private final Id id;
    private final TagName name;
    private final TagDesc desc;
    private final TagColor color;

    /**
     * Constructs a {@code Tag}.
     *
     * Every fields must be present and not null.
     * Only use this constructor when Id is guaranteed to be unique.
     */
    public Tag(Id id, TagName name, TagDesc desc, TagColor color) {
        requireAllNonNull(id, name, desc, color);
        idManager.setLargest(id);
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.color = color;
    }

    /**
     * Constructs a {@code Tag}, without needing to provide an id.
     * The id will automatically be deduced from {@code largestId}.
     */
    public Tag(TagName name, TagDesc desc, TagColor color) {
        this(idManager.getNewId(), name, desc, color);
    }

    public Id getId() {
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
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", id)
                .add("name", name)
                .add("description", desc)
                .add("color", color)
                .toString();
    }

}
