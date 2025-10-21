package seedu.address.model.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRelationships.ONE;
import static seedu.address.testutil.TypicalRelationships.TWO;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.relationship.exceptions.DuplicateRelationshipException;
import seedu.address.model.relationship.exceptions.RelationshipNotFoundException;
import seedu.address.testutil.RelationshipBuilder;

public class UniqueRelationshipListTest {
    private final UniqueRelationshipList uniqueRelationshipList = new UniqueRelationshipList();

    @Test
    public void contains_nullRelationship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRelationshipList.contains(null));
    }

    @Test
    public void contains_relationshipNotInList_returnsFalse() {
        assertFalse(uniqueRelationshipList.contains(ONE));
    }

    @Test
    public void contains_relationshipInList_returnsTrue() {
        // exactly the same relationship
        uniqueRelationshipList.add(ONE);
        assertTrue(uniqueRelationshipList.contains(ONE));

        // to and from identity
        uniqueRelationshipList.add(new RelationshipBuilder(TWO).withDesc("unexpected").build());
        assertTrue(uniqueRelationshipList.contains(TWO));
    }

    @Test void add_nullRelationship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRelationshipList.add(null));
    }

    @Test
    public void add_duplicateRelationship_throwsDuplicateRelationshipException() {
        uniqueRelationshipList.add(ONE);
        assertThrows(DuplicateRelationshipException.class, () -> uniqueRelationshipList.add(ONE));
    }

    @Test
    public void setRelationship_nullTargetRelationship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRelationshipList.setRelationship(null, ONE));
    }

    @Test
    public void setRelationship_nullEditedRelationship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRelationshipList.setRelationship(ONE, null));
    }

    @Test
    public void setRelationship_targetRelationshipNotInList_throwsRelationshipNotFoundException() {
        assertThrows(RelationshipNotFoundException.class, () -> uniqueRelationshipList.setRelationship(ONE, ONE));
    }

    @Test
    public void setRelationship_editedRelationshipIsSameRelationship_success() {
        uniqueRelationshipList.add(ONE);
        uniqueRelationshipList.setRelationship(ONE, ONE);
        UniqueRelationshipList expectedList = new UniqueRelationshipList();
        expectedList.add(ONE);
        assertEquals(expectedList, uniqueRelationshipList);
    }

    @Test
    public void setRelationship_editedRelationshipHasDifferentIdentity_success() {
        uniqueRelationshipList.add(ONE);
        uniqueRelationshipList.setRelationship(ONE, TWO);
        UniqueRelationshipList expectedList = new UniqueRelationshipList();
        expectedList.add(TWO);
        assertEquals(expectedList, uniqueRelationshipList);
    }

    @Test
    public void setRelationship_editedRelationshipHasNonUniquieIdentity_throwsDuplicateRelationshipException() {
        uniqueRelationshipList.add(ONE);
        uniqueRelationshipList.add(TWO);
        assertThrows(DuplicateRelationshipException.class, () -> uniqueRelationshipList.setRelationship(ONE, TWO));
    }

    @Test
    public void remove_nullRelationship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRelationshipList.remove(null));
    }

    @Test
    public void remove_relationshipDoesNotExist_throwsRelationshipNotFoundException() {
        assertThrows(RelationshipNotFoundException.class, () -> uniqueRelationshipList.remove(ONE));
    }

    @Test
    public void remove_existingRelationship_removesRelationship() {
        uniqueRelationshipList.add(ONE);
        uniqueRelationshipList.remove(ONE);
        UniqueRelationshipList expectedList = new UniqueRelationshipList();
        assertEquals(expectedList, uniqueRelationshipList);
    }

    @Test
    public void setRelationships_nullUniqueRelationshipList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            uniqueRelationshipList.setRelationships((UniqueRelationshipList) null));
    }

    @Test
    public void setRelationships_uniqueRelationshipList_replacesownListWithProvidedUniqueRelationshipList() {
        uniqueRelationshipList.add(ONE);
        UniqueRelationshipList expectedList = new UniqueRelationshipList();
        expectedList.add(TWO);
        uniqueRelationshipList.setRelationships(expectedList);
        assertEquals(expectedList, uniqueRelationshipList);
    }

    @Test
    public void setRelationships_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            uniqueRelationshipList.setRelationships((List<Relationship>) null));
    }

    @Test
    public void setRelationships_list_replacesOwnListWithProvidedList() {
        uniqueRelationshipList.add(ONE);
        List<Relationship> relationshipList = Collections.singletonList(TWO);
        uniqueRelationshipList.setRelationships(relationshipList);
        UniqueRelationshipList expectedUniqueRelationshipList = new UniqueRelationshipList();
        expectedUniqueRelationshipList.add(TWO);
        assertEquals(expectedUniqueRelationshipList, uniqueRelationshipList);
    }

    @Test
    public void setRelationships_listWithDuplicateRelationships_throwsDuplicateRelationshipException() {
        List<Relationship> listWithDuplicate = Arrays.asList(ONE, TWO, ONE);
        assertThrows(DuplicateRelationshipException.class, () ->
            uniqueRelationshipList.setRelationships(listWithDuplicate));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
            uniqueRelationshipList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void iterator() {
        uniqueRelationshipList.add(ONE);
        uniqueRelationshipList.add(TWO);
        Iterator<Relationship> i = uniqueRelationshipList.iterator();
        assertTrue(i.next().equals(ONE));
        assertTrue(i.next().equals(TWO));
        assertFalse(i.hasNext());
    }

    @Test
    public void equals_sameObject() {
        assertTrue(uniqueRelationshipList.equals(uniqueRelationshipList));
    }

    @Test
    public void equals_differentType() {
        assertFalse(uniqueRelationshipList.equals(0.5f));
    }

    @Test
    public void equals_differentObject_success() {
        uniqueRelationshipList.add(ONE);
        uniqueRelationshipList.add(TWO);
        UniqueRelationshipList compare = new UniqueRelationshipList();
        compare.add(ONE);
        compare.add(TWO);
        assertEquals(uniqueRelationshipList, compare);
    }

    @Test
    public void equals_differentObject_failure() {
        uniqueRelationshipList.add(ONE);
        UniqueRelationshipList compare = new UniqueRelationshipList();
        compare.add(ONE);
        compare.add(TWO);
        assertNotEquals(uniqueRelationshipList, compare);
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueRelationshipList.asUnmodifiableObservableList().toString(),
            uniqueRelationshipList.toString());
    }
}
