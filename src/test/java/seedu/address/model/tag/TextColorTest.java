package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TextColorTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TextColor(null));
    }

    @Test
    public void constructor_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TextColor("GGGGGG"));
        assertThrows(IllegalArgumentException.class, () -> new TextColor("0"));
    }

    @Test
    public void isValidTextColor() {
        // null -> returns false
        assertThrows(NullPointerException.class, () -> TextColor.isValidTextColor(null));

        // invalid colors -> returns false
        assertFalse(TextColor.isValidTextColor("")); // empty string
        assertFalse(TextColor.isValidTextColor("12")); // only 2 characters
        assertFalse(TextColor.isValidTextColor("12345")); // 5 characters
        assertFalse(TextColor.isValidTextColor("1")); // only 1 character
        assertFalse(TextColor.isValidTextColor("GGGGGG")); // contains non-hexadecimal characters

        // valid colors -> returns true
        assertTrue(TextColor.isValidTextColor("000")); // 3 characters, black
        assertTrue(TextColor.isValidTextColor("FFF")); // 3 characters, white
        assertTrue(TextColor.isValidTextColor("ABCABC")); // 6 characters
        assertTrue(TextColor.isValidTextColor("AB12B7")); // mix
    }

    @Test
    public void equals() {
        TextColor textColor = new TextColor("121212");

        // same values -> returns true
        assertTrue(textColor.equals(new TextColor("121212")));

        // same object -> returns true
        assertTrue(textColor.equals(textColor));

        // null -> returns false
        assertFalse(textColor.equals(null));

        // different types -> returns false
        assertFalse(textColor.equals(5.0f));

        // different values -> returns false
        assertFalse(textColor.equals(new TextColor("BBCCDD")));
    }
}
