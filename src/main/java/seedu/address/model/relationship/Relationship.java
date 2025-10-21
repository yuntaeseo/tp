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
    /** First participant */
    private final Id part1;
    /** Second participant */
    private final Id part2;
    private final Description desc;

    /**
     * Constructs a {@code Relationship}.
     *
     * Every fields must be present and not null.
     */
    public Relationship(Id part1, Id part2, Description desc) {
        requireAllNonNull(part1, part2, desc);
        this.part1 = part1;
        this.part2 = part2;
        this.desc = desc;
    }

    public Id getPart1() {
        return part1;
    }

    public Id getPart2() {
        return part2;
    }

    public Description getDescription() {
        return desc;
    }

    /**
     * Returns true if both relationships have the same pair of participants.
     * This defines a weaker notion of equality between two relationships.
     */
    public boolean isSameRelationship(Relationship other) {
        if (other == this) {
            return true;
        }

        if (other == null) {
            return false;
        }

        return (this.part1.equals(other.part1) && this.part2.equals(other.part2))
            || (this.part1.equals(other.part2) && this.part2.equals(other.part1));
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

        return this.part1.equals(otherRelationship.part1)
                && this.part2.equals(otherRelationship.part2)
                && this.desc.equals(otherRelationship.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(part1, part2, desc);
    }

    /**
     * Format state as text for viewing
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("part1", part1).add("part2", part2).add("description", desc).toString();
    }
}
