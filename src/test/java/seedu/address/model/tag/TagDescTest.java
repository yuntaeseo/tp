package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagDescTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagDesc(null));
    }

    @Test
    public void equals() {
        TagDesc desc = new TagDesc("Valid Desc");

        // same values -> returns true
        assertTrue(desc.equals(new TagDesc("Valid Desc")));

        // same object -> returns true
        assertTrue(desc.equals(desc));

        // null -> returns false
        assertFalse(desc.equals(null));

        // different types -> returns false
        assertFalse(desc.equals(5.0f));

        // different values -> returns false
        assertFalse(desc.equals(new TagName("Other Valid Desc")));
    }
}
