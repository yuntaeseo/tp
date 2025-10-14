package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new TagName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> TagName.isValidTagName(null));

        // invalid name
        assertFalse(TagName.isValidTagName("")); // empty string
        assertFalse(TagName.isValidTagName(" ")); // spaces only
        assertFalse(TagName.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(TagName.isValidTagName("friends*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TagName.isValidTagName("ex girlfriends")); // alphabets only
        assertTrue(TagName.isValidTagName("12345")); // numbers only
        assertTrue(TagName.isValidTagName("2nd worst enemies")); // alphanumeric characters
        assertTrue(TagName.isValidTagName("Best Friends")); // with capital letters
        assertTrue(TagName.isValidTagName("This Tag name is very long")); // long names
    }

    @Test
    public void equals() {
        TagName name = new TagName("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new TagName("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new TagName("Other Valid Name")));
    }
}
