package seedu.address.testutil;

import seedu.address.model.description.Description;
import seedu.address.model.id.Id;
import seedu.address.model.relationship.Relationship;

/**
 * A utility class to help with building Relationship objects.
 */
public class RelationshipBuilder {
    public static final Integer DEFAULT_FROM = 2;
    public static final Integer DEFAULT_TO = 1;
    public static final String DEFAULT_DESC = "minh made this";

    private Id from;
    private Id to;
    private Description desc;

    /**
     * Creates a {@code RelationshipBuilder} with the default details.
     */
    public RelationshipBuilder() {
        from = new Id(DEFAULT_FROM);
        to = new Id(DEFAULT_TO);
        desc = new Description(DEFAULT_DESC);
    }

    /**
     * Initializes the RelationshipBuilder with the data of {@code copy}.
     */
    public RelationshipBuilder(Relationship copy) {
        from = copy.getFrom();
        to = copy.getTo();
        desc = copy.getDescription();
    }

    /**
     * Sets the {@code from} of the {@code Relationship} that we are building.
     */
    public RelationshipBuilder withFrom(Integer from) {
        this.from = new Id(from);
        return this;
    }

    /**
     * Sets the {@code to} of the {@code Relationship} that we are building.
     */
    public RelationshipBuilder withTo(Integer to) {
        this.to = new Id(to);
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
        return new Relationship(from, to, desc);
    }
}
