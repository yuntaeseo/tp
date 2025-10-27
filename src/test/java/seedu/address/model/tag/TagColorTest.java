package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagColorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Color(null));
    }

    @Test
    public void constructor_invalidColor_throwsIllegalArgumentException() {
        String invalidColor = "";
        assertThrows(IllegalArgumentException.class, () -> new Color(invalidColor));
    }

    @Test
    public void isValidColor() {
        // null name
        assertThrows(NullPointerException.class, () -> Color.isValidColor(null));

        // invalid name
        assertFalse(Color.isValidColor("")); // empty string
        assertFalse(Color.isValidColor(" ")); // spaces only
        assertFalse(Color.isValidColor("12345")); // 5 characters only
        assertFalse(Color.isValidColor("AABBGG")); // contains non-hexadecimal characters

        // valid name
        assertTrue(Color.isValidColor("AABBCC")); // alphabets uppercase only
        assertTrue(Color.isValidColor("aabbcc")); // alphabets lowercase only
        assertTrue(Color.isValidColor("123456")); // numbers only
        assertTrue(Color.isValidColor("AA11cc")); // combined
    }

    @Test
    public void equals() {
        Color color = new Color("AABBCC");

        // same values -> returns true
        assertTrue(color.equals(new Color("AABBCC")));

        // same object -> returns true
        assertTrue(color.equals(color));

        // null -> returns false
        assertFalse(color.equals(null));

        // different types -> returns false
        assertFalse(color.equals(5.0f));

        // different values -> returns false
        assertFalse(color.equals(new Color("BBCCDD")));
    }
}
