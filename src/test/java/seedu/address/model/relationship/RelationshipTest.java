package seedu.address.model.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRelationships.ONE;
import static seedu.address.testutil.TypicalRelationships.TWO;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RelationshipBuilder;

public class RelationshipTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Relationship(null, null, null));
    }

    @Test
    public void isSameRelationship() {
        // same object -> returns true
        assertTrue(ONE.isSameRelationship(ONE));

        // null -> returns false
        assertFalse(ONE.isSameRelationship(null));

        // same to and from, all other attributes different -> returns true
        Relationship editedRelationship = new RelationshipBuilder(ONE).withDesc("unexpected").build();
        assertTrue(ONE.isSameRelationship(editedRelationship));

        // different to, all other attributes same -> returns false
        editedRelationship = new RelationshipBuilder(ONE).withTo(1000).build();
        assertFalse(ONE.isSameRelationship(editedRelationship));

        // different from, all other attributes same -> returns false
        editedRelationship = new RelationshipBuilder(ONE).withFrom(30474).build();
        assertFalse(ONE.isSameRelationship(editedRelationship));

        // different to and from, all other attributes same -> returns false
        editedRelationship = new RelationshipBuilder(ONE).withTo(103845).withFrom(2459).build();
        assertFalse(ONE.isSameRelationship(editedRelationship));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Relationship oneCopy = new RelationshipBuilder(ONE).build();
        assertTrue(ONE.equals(oneCopy));

        // same object -> returns true
        assertTrue(ONE.equals(ONE));

        // null -> returns false
        assertFalse(ONE.equals(null));

        // different type -> returns false
        assertFalse(ONE.equals(0.5f));

        // different tag -> returns false
        assertFalse(ONE.equals(TWO));

        // different to -> returns false
        Relationship editedRelationship = new RelationshipBuilder(ONE).withTo(100).build();
        assertFalse(ONE.equals(editedRelationship));

        // different from -> returns false
        editedRelationship = new RelationshipBuilder(ONE).withFrom(10000).build();
        assertFalse(ONE.equals(editedRelationship));

        // different desc -> returns false
        editedRelationship = new RelationshipBuilder(ONE).withDesc("unexpected").build();
        assertFalse(ONE.equals(editedRelationship));
    }

    @Test
    public void toStringMethod() {
        String expected = Relationship.class.getCanonicalName() + "{from=" + ONE.getFrom() + ", to=" + ONE.getTo()
                + ", description=" + ONE.getDescription() + "}";
        assertEquals(expected, ONE.toString());
    }
}
