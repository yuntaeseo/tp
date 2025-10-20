package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void constructorString_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id((String) null));
    }

    @Test
    public void constructorInteger_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id((Integer) null));
    }

    @Test
    public void constructorString_invalidId_throwsIllegalArgumentException() {
        String invalidIdString = "";
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidIdString));
    }

    @Test
    public void constructorInteger_invalidId_throwsIllegalArgumentException() {
        Integer invalidIdInteger = -1;
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidIdInteger));
    }

    @Test
    public void isValidIdString() {
        // null id
        assertThrows(NullPointerException.class, () -> Id.isValidId((String) null));

        // invalid id
        assertFalse(Id.isValidId("")); // empty string
        assertFalse(Id.isValidId(" ")); // spaces only
        assertFalse(Id.isValidId("-1")); // negative number
        assertFalse(Id.isValidId("0")); // zero
        assertFalse(Id.isValidId("AABBGG")); // contains characters

        // valid id
        assertTrue(Id.isValidId("1"));
        assertTrue(Id.isValidId("100"));
        assertTrue(Id.isValidId("394583"));
    }

    @Test
    public void isValidIdInteger() {
        // null id
        assertThrows(NullPointerException.class, () -> Id.isValidId((Integer) null));

        // invalid id
        assertFalse(Id.isValidId(0)); // zero
        assertFalse(Id.isValidId(-1)); // negative number

        // valid id
        assertTrue(Id.isValidId(1));
        assertTrue(Id.isValidId(100));
        assertTrue(Id.isValidId(394583));
    }

    @Test
    public void equals() {
        Id id = new Id("1");

        // same values -> returns true
        assertTrue(id.equals(new Id("1")));

        // same object -> returns true
        assertTrue(id.equals(id));

        // null -> returns false
        assertFalse(id.equals(null));

        // different types -> returns false
        assertFalse(id.equals(5.0f));

        // different values -> returns false
        assertFalse(id.equals(new Id("3")));
    }
}
