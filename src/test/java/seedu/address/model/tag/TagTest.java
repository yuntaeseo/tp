package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(0, null, null, null));
    }

    //  TODO: Add more tests to tags
    // @Test
    // public void constructor_invalidTagName_throwsIllegalArgumentException() {
    //     String invalidTagName = "";
    //     assertThrows(IllegalArgumentException.class, () -> new Tag(1, "bruh", "", "1234567"));
    // }

    @Test
    public void isValidTagNameId() {
        // null tag name
        assertFalse(Tag.isValidTagId("bruh"));
    }

}
