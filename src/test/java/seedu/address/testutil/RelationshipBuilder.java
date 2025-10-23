package seedu.address.testutil;

import seedu.address.model.description.Description;
import seedu.address.model.id.Id;
import seedu.address.model.relationship.Relationship;

/**
 * A utility class to help with building Relationship objects.
 */
public class RelationshipBuilder {
    public static final Integer DEFAULT_PART1 = 1;
    public static final Integer DEFAULT_PART2 = 2;
    public static final String DEFAULT_DESC = "minh made this";

    private Id part1;
    private Id part2;
    private Description desc;

    /**
     * Creates a {@code RelationshipBuilder} with the default details.
     */
    public RelationshipBuilder() {
        part1 = new Id(DEFAULT_PART1);
        part2 = new Id(DEFAULT_PART2);
        desc = new Description(DEFAULT_DESC);
    }

    /**
     * Initializes the RelationshipBuilder with the data of {@code copy}.
     */
    public RelationshipBuilder(Relationship copy) {
        part1 = copy.getPart1();
        part2 = copy.getPart2();
        desc = copy.getDescription();
    }

    /**
     * Sets the {@code part1} of the {@code Relationship} that we are building.
     */
    public RelationshipBuilder withPart1(Integer part1) {
        this.part1 = new Id(part1);
        return this;
    }

    /**
     * Sets the {@code part2} of the {@code Relationship} that we are building.
     */
    public RelationshipBuilder withPart2(Integer part2) {
        this.part2 = new Id(part2);
        return this;
    }

    /**
     * Sets the {@code desc} of the {@code Relationship} that we are building.
     */
    public RelationshipBuilder withDesc(String desc) {
        this.desc = new Description(desc);
        return this;
    }

    /**
     * Create the {@code Relationship} with the given data.
     */
    public Relationship build() {
        return new Relationship(part1, part2, desc);
    }
}
