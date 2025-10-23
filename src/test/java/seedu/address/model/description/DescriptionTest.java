package seedu.address.model.description;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidColor = " bruh ";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidColor));
    }

    @Test
    public void isValidDescription() {
        // null name
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("    ")); // spaces only
        assertFalse(Description.isValidDescription("   dededed")); // leading whitespace
        assertFalse(Description.isValidDescription("dedededed ")); // trailing whitespace
        assertFalse(Description.isValidDescription("   dedededed ")); // both

        // valid description
        assertTrue(Description.isValidDescription("")); // empty string
        assertTrue(Description.isValidDescription("bruhhurb"));
        assertTrue(Description.isValidDescription("i am minh"));
    }

    @Test
    public void equals() {
        Description desc = new Description("bruh");

        // same values -> returns true
        assertTrue(desc.equals(new Description("bruh")));

        // same object -> returns true
        assertTrue(desc.equals(desc));

        // null -> returns false
        assertFalse(desc.equals(null));

        // different types -> returns false
        assertFalse(desc.equals(5.0f));

        // different values -> returns false
        assertFalse(desc.equals(new Description("lmao")));
    }
}
