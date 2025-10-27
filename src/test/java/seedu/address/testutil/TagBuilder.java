package seedu.address.testutil;

import seedu.address.model.id.Id;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.Color;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;

/**
 * A utility class to help with building Tag objects.
 */
public class TagBuilder {

    public static final Id DEFAULT_ID = new Id(1);
    public static final String DEFAULT_NAME = "friends";
    public static final String DEFAULT_DESC = "people who I am close with";
    public static final String DEFAULT_COLOR = "123456";

    private Id id;
    private TagName name;
    private TagDesc desc;
    private Color color;

    /**
     * Creates a {@code TagBuilder} with the default details.
     */
    public TagBuilder() {
        id = DEFAULT_ID;
        name = new TagName(DEFAULT_NAME);
        desc = new TagDesc(DEFAULT_DESC);
        color = new Color(DEFAULT_COLOR);
    }

    /**
     * Initializes the TagBuilder with the data of {@code tagToCopy}.
     */
    public TagBuilder(Tag tagToCopy) {
        id = tagToCopy.getId();
        name = tagToCopy.getName();
        desc = tagToCopy.getDesc();
        color = tagToCopy.getColor();
    }

    /**
     * Sets the {@code id} of the {@code Tag} that we are building.
     */
    public TagBuilder withId(Integer id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Tag} that we are building.
     */
    public TagBuilder withName(String name) {
        this.name = new TagName(name);
        return this;
    }

    /**
     * Sets the {@code Desc} of the {@code Tag} that we are building.
     */
    public TagBuilder withDesc(String desc) {
        this.desc = new TagDesc(desc);
        return this;
    }

    /**
     * Sets the {@code Color} of the {@code Tag} that we are building.
     */
    public TagBuilder withColor(String color) {
        this.color = new Color(color);
        return this;
    }

    public Tag build() {
        return id.value == -1 ? new Tag(name, desc, color) : new Tag(id, name, desc, color);
    }
}
