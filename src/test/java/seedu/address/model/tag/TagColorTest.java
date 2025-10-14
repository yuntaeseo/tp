package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagColorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagColor(null));
    }

    @Test
    public void constructor_invalidColor_throwsIllegalArgumentException() {
        String invalidColor = "";
        assertThrows(IllegalArgumentException.class, () -> new TagColor(invalidColor));
    }

    @Test
    public void isValidColor() {
        // null name
        assertThrows(NullPointerException.class, () -> TagColor.isValidTagColor(null));

        // invalid name
        assertFalse(TagColor.isValidTagColor("")); // empty string
        assertFalse(TagColor.isValidTagColor(" ")); // spaces only
        assertFalse(TagColor.isValidTagColor("12345")); // 5 characters only
        assertFalse(TagColor.isValidTagColor("AABBGG")); // contains non-hexadecimal characters

        // valid name
        assertTrue(TagColor.isValidTagColor("AABBCC")); // alphabets uppercase only
        assertTrue(TagColor.isValidTagColor("aabbcc")); // alphabets lowercase only
        assertTrue(TagColor.isValidTagColor("123456")); // numbers only
        assertTrue(TagColor.isValidTagColor("AA11cc")); // combined
    }

    @Test
    public void equals() {
        TagColor color = new TagColor("AABBCC");

        // same values -> returns true
        assertTrue(color.equals(new TagColor("AABBCC")));

        // same object -> returns true
        assertTrue(color.equals(color));

        // null -> returns false
        assertFalse(color.equals(null));

        // different types -> returns false
        assertFalse(color.equals(5.0f));

        // different values -> returns false
        assertFalse(color.equals(new TagColor("BBCCDD")));
    }
}
