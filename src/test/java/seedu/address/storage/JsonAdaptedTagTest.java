package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTag.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.FRIENDS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.id.Id;
import seedu.address.model.tag.TagColor;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;

public class JsonAdaptedTagTest {
    private static final Integer INVALID_ID = -1;
    private static final String INVALID_NAME = "*";
    private static final String INVALID_COLOR = "AABBGG";

    private static final Integer VALID_ID = 1;
    private static final String VALID_NAME = "friends";
    private static final String VALID_DESC = "close friends";
    private static final String VALID_COLOR = "AABBCC";

    @Test
    public void toModelType_validTagDetails_returnsTag() throws Exception {
        JsonAdaptedTag jsonTag = new JsonAdaptedTag(FRIENDS);
        assertEquals(FRIENDS, jsonTag.toModelType());
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(INVALID_ID, VALID_NAME, VALID_DESC, VALID_COLOR);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(null, VALID_NAME, VALID_DESC, VALID_COLOR);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(VALID_ID, INVALID_NAME, VALID_DESC, VALID_COLOR);
        String expectedMessage = TagName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(VALID_ID, null, VALID_DESC, VALID_COLOR);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TagName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_nullDesc_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(VALID_ID, VALID_NAME, null, VALID_COLOR);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TagDesc.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_invalidColor_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(VALID_ID, VALID_NAME, VALID_DESC, INVALID_COLOR);
        String expectedMessage = TagColor.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_nullColor_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(VALID_ID, VALID_NAME, VALID_DESC, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TagColor.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }
}
