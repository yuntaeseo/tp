package seedu.address.model.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRelationships.ONE_TWO;
import static seedu.address.testutil.TypicalRelationships.TWO_THREE;

import org.junit.jupiter.api.Test;

import seedu.address.model.id.Id;
import seedu.address.testutil.RelationshipBuilder;

public class RelationshipTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Relationship(null, null, null));
    }

    @Test
    public void isSameRelationship() {
        // same object -> returns true
        assertTrue(ONE_TWO.isSameRelationship(ONE_TWO));

        // null -> returns false
        assertFalse(ONE_TWO.isSameRelationship(null));

        // same part1 and part2, all other attributes different -> returns true
        Relationship editedRelationship = new RelationshipBuilder(ONE_TWO).withDesc("unexpected").build();
        assertTrue(ONE_TWO.isSameRelationship(editedRelationship));

        // same but reversed part1 and part2, all other attributes same -> returns true
        editedRelationship = new RelationshipBuilder(ONE_TWO).withPart1(ONE_TWO.getPart2().value)
            .withPart2(ONE_TWO.getPart1().value).build();
        assertTrue(ONE_TWO.isSameRelationship(editedRelationship));

        // same but reversed part1 and part2, all other attributes different -> returns true
        editedRelationship = new RelationshipBuilder().withPart1(ONE_TWO.getPart2().value)
            .withPart2(ONE_TWO.getPart1().value).withDesc("unexpected").build();
        assertTrue(ONE_TWO.isSameRelationship(editedRelationship));

        // different part1, all other attributes same -> returns false
        editedRelationship = new RelationshipBuilder(ONE_TWO).withPart1(30474).build();
        assertFalse(ONE_TWO.isSameRelationship(editedRelationship));

        // different part2, all other attributes same -> returns false
        editedRelationship = new RelationshipBuilder(ONE_TWO).withPart2(1000).build();
        assertFalse(ONE_TWO.isSameRelationship(editedRelationship));

        // different part1 and part2, all other attributes same -> returns false
        editedRelationship = new RelationshipBuilder(ONE_TWO).withPart2(103845).withPart1(2459).build();
        assertFalse(ONE_TWO.isSameRelationship(editedRelationship));
    }

    @Test
    public void hasPersonWithId_doesNotHaveId_returnsFalse() {
        assertFalse(ONE_TWO.hasPersonWithId(new Id(3)));
        assertFalse(ONE_TWO.hasPersonWithId(new Id(100)));
    }

    @Test
    public void hasPersonWithId_hasId_returnsTrue() {
        assertTrue(ONE_TWO.hasPersonWithId(new Id(1)));
        assertTrue(ONE_TWO.hasPersonWithId(new Id(2)));
    }

    @Test
    public void getCounterpartId() {
        Id one = new Id(1);
        Id two = new Id(2);
        assertEquals(one, ONE_TWO.getCounterpartId(two));
        assertEquals(two, ONE_TWO.getCounterpartId(one));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Relationship oneCopy = new RelationshipBuilder(ONE_TWO).build();
        assertTrue(ONE_TWO.equals(oneCopy));

        // same object -> returns true
        assertTrue(ONE_TWO.equals(ONE_TWO));

        // null -> returns false
        assertFalse(ONE_TWO.equals(null));

        // different type -> returns false
        assertFalse(ONE_TWO.equals(0.5f));

        // different relationship -> returns false
        assertFalse(ONE_TWO.equals(TWO_THREE));

        // different part1 -> returns false
        Relationship editedRelationship = new RelationshipBuilder(ONE_TWO).withPart1(10000).build();
        assertFalse(ONE_TWO.equals(editedRelationship));

        // different part2 -> returns false
        editedRelationship = new RelationshipBuilder(ONE_TWO).withPart2(100).build();
        assertFalse(ONE_TWO.equals(editedRelationship));

        // different desc -> returns false
        editedRelationship = new RelationshipBuilder(ONE_TWO).withDesc("unexpected").build();
        assertFalse(ONE_TWO.equals(editedRelationship));
    }

    @Test
    public void toStringMethod() {
        String expected = Relationship.class.getCanonicalName() + "{part1=" + ONE_TWO.getPart1() + ", part2="
            + ONE_TWO.getPart2() + ", description=" + ONE_TWO.getDescription() + "}";
        assertEquals(expected, ONE_TWO.toString());
    }
}
