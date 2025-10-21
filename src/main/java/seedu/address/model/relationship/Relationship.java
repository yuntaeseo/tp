package seedu.address.model.relationship;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.description.Description;
import seedu.address.model.id.Id;

/**
 * Represent a 1-to-1 relationship between two Person in the address book.
 */
public class Relationship {
    private final Id from;
    private final Id to;
    private final Description desc;

    /**
     * Constructs a {@code Relationship}.
     *
     * Every fields must be present and not null.
     */
    public Relationship(Id from, Id to, Description desc) {
        requireAllNonNull(to, from, desc);
        this.from = from;
        this.to = to;
        this.desc = desc;
    }

    public Id getTo() {
        return to;
    }

    public Id getFrom() {
        return from;
    }

    public Description getDescription() {
        return desc;
    }

    /**
     * Returns true if both relationships have the same 'to' and 'from' ids.
     * This defines a weaker notion of equality between two tags.
     */
    public boolean isSameRelationship(Relationship other) {
        if (other == this) {
            return true;
        }

        return other != null && this.from == other.from && this.to == other.to;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof Relationship)) {
            return false;
        }

        Relationship otherRelationship = (Relationship) other;

        return this.from == otherRelationship.from
                && this.to == otherRelationship.to
                && this.desc == otherRelationship.desc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, desc);
    }

    /**
     * Format state as text for viewing
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("from", from).add("to", to).add("description", desc).toString();
    }
}
