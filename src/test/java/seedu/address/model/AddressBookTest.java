package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_1;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalRelationships.ONE_TWO;
import static seedu.address.testutil.TypicalTags.EX_GIRLFRIEND;
import static seedu.address.testutil.TypicalTags.FRIENDS;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.id.Id;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.relationship.exceptions.DuplicateRelationshipException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.RelationshipBuilder;
import seedu.address.testutil.TagBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertEquals(Collections.emptyList(), addressBook.getTagList());
        assertEquals(Collections.emptyList(), addressBook.getRelationshipList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ReadOnlyAddressBook readOnlyAddressBook = SampleDataUtil.getSampleAddressBook();
        addressBook.resetData(readOnlyAddressBook);
        assertEquals(readOnlyAddressBook, addressBook);

        AddressBook ab = getTypicalAddressBook();
        addressBook.resetData(ab);
        assertEquals(ab, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_1)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons, null, null);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTags_throwsDuplicateTagException() {
        // Two tag with the same identity
        Tag editedFriends = new TagBuilder(FRIENDS).withDesc("bruh").withColor("AABBCC").build();
        List<Tag> newTags = Arrays.asList(FRIENDS, editedFriends);
        AddressBookStub newData = new AddressBookStub(null, newTags, null);

        assertThrows(DuplicateTagException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateRelationships_throwsDuplicateRelationshipException() {
        // Two relationships with the same identity
        Relationship editedOneTwo = new RelationshipBuilder(ONE_TWO).withDesc("unexpected").build();
        List<Relationship> newRelationships = Arrays.asList(ONE_TWO, editedOneTwo);
        AddressBookStub newData = new AddressBookStub(null, null, newRelationships);

        assertThrows(DuplicateRelationshipException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasTagId_idNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTagId(new Id(1)));
        assertFalse(addressBook.hasTagId(new Id(10)));
        assertFalse(addressBook.hasTagId(new Id(67)));
        assertFalse(addressBook.hasTagId(new Id(65166666)));
    }

    @Test
    public void hasTagId_idInAddressBook_returnsTrue() {
        Tag one = new TagBuilder(FRIENDS).withId(1).build();
        Tag two = new TagBuilder(EX_GIRLFRIEND).withId(2).build();
        addressBook.addTag(one);
        addressBook.addTag(two);

        assertTrue(addressBook.hasTagId(new Id(1)));
        assertTrue(addressBook.hasTagId(new Id(2)));
    }

    @Test
    public void hasTagIds_idsInAddressBook_returnsTrue() {
        addressBook.addTag(new TagBuilder().withName("wan").withId(1).build());
        addressBook.addTag(new TagBuilder().withName("tu").withId(2).build());
        addressBook.addTag(new TagBuilder().withName("tre").withId(3).build());

        assertTrue(addressBook.hasTagIds(Set.of(new Id(1), new Id(3))));
        assertTrue(addressBook.hasTagIds(Set.of(new Id(2), new Id(1))));
        assertTrue(addressBook.hasTagIds(Set.of(new Id(1))));
        assertTrue(addressBook.hasTagIds(Set.of(new Id(1), new Id(2), new Id(3))));
    }

    @Test
    public void hasTagIds_idsNotInAddressBook_returnsFalse() {
        addressBook.addTag(new TagBuilder().withName("er").withId(2).build());
        addressBook.addTag(new TagBuilder().withName("san").withId(3).build());
        addressBook.addTag(new TagBuilder().withName("wu").withId(5).build());

        assertFalse(addressBook.hasTagIds(Set.of(new Id(1))));
        assertFalse(addressBook.hasTagIds(Set.of(new Id(1), new Id(2))));
        assertFalse(addressBook.hasTagIds(Set.of(new Id(1), new Id(2), new Id(3), new Id(5))));
    }



    //  NOTE: PERSON

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_1)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }



    //  NOTE: TAGS

    @Test
    public void hasTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTag(null));
    }

    @Test
    public void hasTag_tagNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTag(FRIENDS));
    }

    @Test
    public void hasTag_tagInAddressBook_returnsTrue() {
        addressBook.addTag(FRIENDS);
        assertTrue(addressBook.hasTag(FRIENDS));
    }

    @Test
    public void hasTag_tagWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTag(FRIENDS);
        Tag editedFriends = new TagBuilder(FRIENDS).withDesc("bruh").withColor("FFFFFF").build();
        assertTrue(addressBook.hasTag(editedFriends));
    }

    @Test
    public void getTagList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTagList().remove(0));
    }



    //  NOTE: RELATIONSHIPS

    @Test
    public void hasRelationship_nullRelationship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasRelationship(null));
    }

    @Test
    public void hasRelationship_relationshipNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasRelationship(ONE_TWO));
    }

    @Test
    public void hasRelationship_relationshipInAddressBook_returnsTrue() {
        addressBook.addRelationship(ONE_TWO);
        assertTrue(addressBook.hasRelationship(ONE_TWO));
    }

    @Test
    public void hasRelationship_relationshipWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addRelationship(ONE_TWO);
        Relationship editedOneTwo = new RelationshipBuilder(ONE_TWO).withPart1(ONE_TWO.getPart2().value)
            .withPart2(ONE_TWO.getPart1().value).withDesc("bruh").build();
        assertTrue(addressBook.hasRelationship(editedOneTwo));
    }

    @Test
    public void getRelationshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getRelationshipList().remove(0));
    }



    @Test
    public void equals() {
        AddressBook empty = new AddressBook();
        addressBook.resetData(empty);

        // same values -> return true
        assertTrue(addressBook.equals(empty));

        // same object -> return true
        assertTrue(addressBook.equals(addressBook));

        // null -> returns false
        assertFalse(addressBook.equals(null));

        // different types -> returns false
        assertFalse(addressBook.equals(0.5f));

        // different person list -> returns false
        addressBook.addPerson(ALICE);
        assertFalse(addressBook.equals(empty));
        addressBook.resetData(empty);

        // different tag list -> returns false
        addressBook.addTag(FRIENDS);
        assertFalse(addressBook.equals(empty));
        addressBook.resetData(empty);

        // different relationship list -> returns false
        addressBook.addRelationship(ONE_TWO);
        assertFalse(addressBook.equals(empty));
        addressBook.resetData(empty);
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList()
            + ", tags=" + addressBook.getTagList() + ", relationships=" + addressBook.getRelationshipList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Tag> tags = FXCollections.observableArrayList();
        private final ObservableList<Relationship> relationships = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Tag> tags, Collection<Relationship> relationships) {
            if (persons != null) {
                this.persons.setAll(persons);
            }

            if (tags != null) {
                this.tags.setAll(tags);
            }

            if (relationships != null) {
                this.relationships.setAll(relationships);
            }
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Tag> getTagList() {
            return tags;
        }

        @Override
        public ObservableList<Relationship> getRelationshipList() {
            return relationships;
        }
    }
}
