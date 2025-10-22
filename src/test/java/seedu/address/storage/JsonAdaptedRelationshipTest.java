package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedRelationship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRelationships.ONE_TWO;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.description.Description;
import seedu.address.model.id.Id;

public class JsonAdaptedRelationshipTest {
    private static final Integer INVALID_PART1 = 0;
    private static final Integer INVALID_PART2 = -1;
    private static final String INVALID_DESC = "     ";

    private static final Integer VALID_PART1 = 1;
    private static final Integer VALID_PART2 = 2;
    private static final String VALID_DESC = "friends";

    @Test
    public void toModelType_validRelationshipDetails_returnsRelationship() throws Exception {
        JsonAdaptedRelationship jsonRel = new JsonAdaptedRelationship(ONE_TWO);
        assertEquals(ONE_TWO, jsonRel.toModelType());
    }

    @Test
    public void toModelType_invalidPart1_throwsIllegalValueException() {
        JsonAdaptedRelationship rel = new JsonAdaptedRelationship(INVALID_PART1, VALID_PART2, VALID_DESC);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, rel::toModelType);
    }

    @Test
    public void toModelType_nullPart1_throwsIllegalValueException() {
        JsonAdaptedRelationship rel = new JsonAdaptedRelationship(null, VALID_PART2, VALID_DESC);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, rel::toModelType);
    }

    @Test
    public void toModelType_invalidPart2_throwsIllegalValueException() {
        JsonAdaptedRelationship rel = new JsonAdaptedRelationship(VALID_PART1, INVALID_PART2, VALID_DESC);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, rel::toModelType);
    }

    @Test
    public void toModelType_nullPart2_throwsIllegalValueException() {
        JsonAdaptedRelationship rel = new JsonAdaptedRelationship(VALID_PART1, null, VALID_DESC);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, rel::toModelType);
    }

    @Test
    public void toModelType_invalidDesc_throwsIllegalValueException() {
        JsonAdaptedRelationship rel = new JsonAdaptedRelationship(VALID_PART1, VALID_PART2, INVALID_DESC);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, rel::toModelType);
    }

    @Test
    public void toModelType_nullDesc_throwsIllegalValueException() {
        JsonAdaptedRelationship rel = new JsonAdaptedRelationship(VALID_PART1, VALID_PART2, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, rel::toModelType);
    }
}
