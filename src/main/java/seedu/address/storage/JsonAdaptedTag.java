package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagColor;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tag's %s field is missing!";

    private final String id;
    private final String name;
    private final String desc;
    private final String color;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given tag details.
     */
    @JsonCreator
    public JsonAdaptedTag(@JsonProperty("id") String id, @JsonProperty("name") String name,
            @JsonProperty("desc") String desc, @JsonProperty("color") String color) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.color = color;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        id = Integer.toString(source.getId());
        name = source.getName().value;
        desc = source.getDesc().value;
        color = source.getColor().value;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "id"));
        }
        if (!id.matches("^[0-9]+$")) {
            throw new IllegalValueException("Id must be a number");
        }
        final int modelId = Integer.parseInt(id);

        if (name == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, TagName.class.getSimpleName()));
        }
        if (!TagName.isValidTagName(name)) {
            throw new IllegalValueException(TagName.MESSAGE_CONSTRAINTS);
        }
        final TagName modelName = new TagName(name);

        if (desc == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, TagDesc.class.getSimpleName()));
        }
        final TagDesc modelDesc = new TagDesc(desc);


        if (color == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, TagColor.class.getSimpleName()));
        }
        if (!TagColor.isValidTagColor(color)) {
            throw new IllegalValueException(TagColor.MESSAGE_CONSTRAINTS + ": " + color);
        }
        final TagColor modelColor = new TagColor(color);

        return new Tag(modelId, modelName, modelDesc, modelColor);
    }

}
