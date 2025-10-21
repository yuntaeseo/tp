package seedu.address.model.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRelationships.ONE_TWO;
import static seedu.address.testutil.TypicalRelationships.TWO_THREE;

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
        assertFalse(uniqueRelationshipList.contains(ONE_TWO));
    }

    @Test
    public void contains_relationshipInList_returnsTrue() {
        // exactly the same relationship
        uniqueRelationshipList.add(ONE_TWO);
        assertTrue(uniqueRelationshipList.contains(ONE_TWO));

        // to and from identity
        uniqueRelationshipList.add(new RelationshipBuilder(TWO_THREE).withDesc("unexpected").build());
        assertTrue(uniqueRelationshipList.contains(TWO_THREE));
    }

    @Test void add_nullRelationship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRelationshipList.add(null));
    }

    @Test
    public void add_duplicateRelationship_throwsDuplicateRelationshipException() {
        uniqueRelationshipList.add(ONE_TWO);
        assertThrows(DuplicateRelationshipException.class, () -> uniqueRelationshipList.add(ONE_TWO));
    }

    @Test
    public void setRelationship_nullTargetRelationship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRelationshipList.setRelationship(null, ONE_TWO));
    }

    @Test
    public void setRelationship_nullEditedRelationship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRelationshipList.setRelationship(ONE_TWO, null));
    }

    @Test
    public void setRelationship_targetRelationshipNotInList_throwsRelationshipNotFoundException() {
        assertThrows(RelationshipNotFoundException.class, () ->
            uniqueRelationshipList.setRelationship(ONE_TWO, ONE_TWO));
    }

    @Test
    public void setRelationship_editedRelationshipIsSameRelationship_success() {
        uniqueRelationshipList.add(ONE_TWO);
        uniqueRelationshipList.setRelationship(ONE_TWO, ONE_TWO);
        UniqueRelationshipList expectedList = new UniqueRelationshipList();
        expectedList.add(ONE_TWO);
        assertEquals(expectedList, uniqueRelationshipList);
    }

    @Test
    public void setRelationship_editedRelationshipHasDifferentIdentity_success() {
        uniqueRelationshipList.add(ONE_TWO);
        uniqueRelationshipList.setRelationship(ONE_TWO, TWO_THREE);
        UniqueRelationshipList expectedList = new UniqueRelationshipList();
        expectedList.add(TWO_THREE);
        assertEquals(expectedList, uniqueRelationshipList);
    }

    @Test
    public void setRelationship_editedRelationshipHasNonUniquieIdentity_throwsDuplicateRelationshipException() {
        uniqueRelationshipList.add(ONE_TWO);
        uniqueRelationshipList.add(TWO_THREE);
        assertThrows(DuplicateRelationshipException.class, () ->
            uniqueRelationshipList.setRelationship(ONE_TWO, TWO_THREE));
    }

    @Test
    public void remove_nullRelationship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRelationshipList.remove(null));
    }

    @Test
    public void remove_relationshipDoesNotExist_throwsRelationshipNotFoundException() {
        assertThrows(RelationshipNotFoundException.class, () -> uniqueRelationshipList.remove(ONE_TWO));
    }

    @Test
    public void remove_existingRelationship_removesRelationship() {
        uniqueRelationshipList.add(ONE_TWO);
        uniqueRelationshipList.remove(ONE_TWO);
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
        uniqueRelationshipList.add(ONE_TWO);
        UniqueRelationshipList expectedList = new UniqueRelationshipList();
        expectedList.add(TWO_THREE);
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
        uniqueRelationshipList.add(ONE_TWO);
        List<Relationship> relationshipList = Collections.singletonList(TWO_THREE);
        uniqueRelationshipList.setRelationships(relationshipList);
        UniqueRelationshipList expectedUniqueRelationshipList = new UniqueRelationshipList();
        expectedUniqueRelationshipList.add(TWO_THREE);
        assertEquals(expectedUniqueRelationshipList, uniqueRelationshipList);
    }

    @Test
    public void setRelationships_listWithDuplicateRelationships_throwsDuplicateRelationshipException() {
        List<Relationship> listWithDuplicate = Arrays.asList(ONE_TWO, TWO_THREE, ONE_TWO);
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
        uniqueRelationshipList.add(ONE_TWO);
        uniqueRelationshipList.add(TWO_THREE);
        Iterator<Relationship> i = uniqueRelationshipList.iterator();
        assertTrue(i.next().equals(ONE_TWO));
        assertTrue(i.next().equals(TWO_THREE));
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
        uniqueRelationshipList.add(ONE_TWO);
        uniqueRelationshipList.add(TWO_THREE);
        UniqueRelationshipList compare = new UniqueRelationshipList();
        compare.add(ONE_TWO);
        compare.add(TWO_THREE);
        assertEquals(uniqueRelationshipList, compare);
    }

    @Test
    public void equals_differentObject_failure() {
        uniqueRelationshipList.add(ONE_TWO);
        UniqueRelationshipList compare = new UniqueRelationshipList();
        compare.add(ONE_TWO);
        compare.add(TWO_THREE);
        assertNotEquals(uniqueRelationshipList, compare);
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueRelationshipList.asUnmodifiableObservableList().toString(),
            uniqueRelationshipList.toString());
    }
}
