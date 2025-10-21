package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.description.Description;
import seedu.address.model.id.Id;
import seedu.address.model.relationship.Relationship;

/**
 * Jackson-friendly version of {@link Relationship}
 */
class JsonAdaptedRelationship {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Relationship's %s field is missing";

    private final Integer part1;
    private final Integer part2;
    private final String desc;

    /**
     * Consturcts a {@code JsonAdaptedRelationship} with the given details.
     */
    @JsonCreator
    public JsonAdaptedRelationship(@JsonProperty("from") Integer part1, @JsonProperty("to") Integer part2,
        @JsonProperty("desc") String desc) {
        this.part1 = part1;
        this.part2 = part2;
        this.desc = desc;
    }

    /**
     * Converts a given {@code Relationship} into this class for Jackson use.
     */
    public JsonAdaptedRelationship(Relationship source) {
        this.part1 = source.getPart1().value;
        this.part2 = source.getPart2().value;
        this.desc = source.getDescription().value;
    }

    /**
     * Converts this Jackson-friendly adapted relationship object into the model's {@code Relationship} object.
     *
     * @throw IllegalValueException if there were any data constraints violated in the adapted relationship.
     */
    public Relationship toModelType() throws IllegalValueException {
        if (part1 == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Id.isValidId(part1)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        final Id modelPart1 = new Id(part1);

        if (part2 == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Id.isValidId(part2)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        final Id modelPart2 = new Id(part2);

        if (desc == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(desc)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(desc);

        return new Relationship(modelPart1, modelPart2, modelDescription);
    }

}
