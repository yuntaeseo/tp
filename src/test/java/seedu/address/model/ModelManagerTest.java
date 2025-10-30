package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RELATIONSHIPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalRelationships.ONE_TWO;
import static seedu.address.testutil.TypicalTags.FRIENDS;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.description.Description;
import seedu.address.model.id.Id;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.relationship.Relationship;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.TagBuilder;
import seedu.address.testutil.TypicalRelationships;
import seedu.address.testutil.TypicalTags;


public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }



    //  NOTE: USER PREFERENCES

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }



    //  NOTE: ADDRESS BOOK

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }



    //  NOTE: PERSON

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }



    //  NOTE: TAGS

    @Test
    public void getFilteredTagList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTagList().remove(0));
    }

    @Test
    public void getTagList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getTagList().remove(0));
    }

    @Test
    public void hasTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTag(null));
    }

    @Test
    public void hasTag_tagNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasTag(FRIENDS));
    }

    @Test
    public void hasTag_tagInAddressBook_returnsTrue() {
        modelManager.addTag(FRIENDS);
        assertTrue(modelManager.hasTag(FRIENDS));
    }

    @Test
    public void hasTagId() {
        modelManager.addTag(new TagBuilder().withName("yi").withId(1).build());
        modelManager.addTag(new TagBuilder().withName("one hundred").withId(100).build());

        assertTrue(modelManager.hasTagId(new Id(1)));
        assertTrue(modelManager.hasTagId(new Id(100)));
        assertFalse(modelManager.hasTagId(new Id(101010)));
        assertFalse(modelManager.hasTagId(new Id(65166666)));
    }

    @Test
    public void hasTagIds() {
        modelManager.addTag(new TagBuilder().withName("one").withId(1).build());
        modelManager.addTag(new TagBuilder().withName("two").withId(2).build());
        modelManager.addTag(new TagBuilder().withName("big").withId(1274983).build());

        assertTrue(modelManager.hasTagIds(Set.of(new Id(1), new Id(2))));
        assertTrue(modelManager.hasTagIds(Set.of(new Id(2))));
        assertFalse(modelManager.hasTagIds(Set.of(new Id(3))));
        assertFalse(modelManager.hasTagIds(Set.of(new Id(67))));
    }



    //  NOTE: RELATIONSHIPS

    @Test
    public void getFilteredRelationshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredRelationshipList().remove(0));
    }

    @Test
    public void getRelationshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getRelationshipList().remove(0));
    }

    @Test
    public void hasRelationship_nullRelationship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasRelationship(null));
    }

    @Test
    public void hasRelationship_relationshipNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasRelationship(ONE_TWO));
    }

    @Test
    public void hasRelationship_relationshipInAddressBook_returnsTrue() {
        modelManager.addRelationship(ONE_TWO);
        assertTrue(modelManager.hasRelationship(ONE_TWO));
    }

    @Test
    public void queryImmediateRelationship_nullId() {
        assertThrows(NullPointerException.class, () -> modelManager.queryImmediateRelationship(null));
    }

    @Test
    public void queryImmediateRelationship_emptyResult() {
        modelManager.addPerson(ALICE);
        modelManager.queryImmediateRelationship(ALICE.getId());
        assertEquals(0, modelManager.getRelationshipQuery().size());
    }

    @Test
    public void queryImmediateRelationship_nonEmptyResult() {
        modelManager.addPerson(ALICE);
        modelManager.addPerson(BOB);
        modelManager.addPerson(BENSON);
        Relationship rel1 = new Relationship(ALICE.getId(), BOB.getId(), new Description("ab"));
        Relationship rel2 = new Relationship(ALICE.getId(), BENSON.getId(), new Description("ab"));
        modelManager.addRelationship(rel1);
        modelManager.addRelationship(rel2);
        modelManager.queryImmediateRelationship(ALICE.getId());
        assertEquals(modelManager.getRelationshipQuery().get(0), new Pair<>(BOB, rel1));
        assertEquals(modelManager.getRelationshipQuery().get(1), new Pair<>(BENSON, rel2));
    }

    @Test
    public void queryLink_nullId() {
        assertThrows(NullPointerException.class, () -> modelManager.queryLink(null, null));
    }

    @Test
    public void queryLink_noLink() {
        modelManager.addPerson(ALICE);
        modelManager.addPerson(BOB);
        modelManager.addPerson(BENSON);
        modelManager.addPerson(CARL);
        Relationship rel1 = new Relationship(ALICE.getId(), BOB.getId(), new Description("ab"));
        Relationship rel2 = new Relationship(ALICE.getId(), BENSON.getId(), new Description("ab"));
        Relationship rel3 = new Relationship(BOB.getId(), BENSON.getId(), new Description("bb"));
        modelManager.addRelationship(rel1);
        modelManager.addRelationship(rel2);
        modelManager.addRelationship(rel3);
        modelManager.queryLink(ALICE.getId(), CARL.getId());
        assertTrue(modelManager.getRelationshipQuery().size() == 0);
    }

    @Test
    public void queryLink_linkExist() {
        modelManager.addPerson(ALICE);
        modelManager.addPerson(BOB);
        modelManager.addPerson(BENSON);
        modelManager.addPerson(CARL);
        Relationship rel1 = new Relationship(ALICE.getId(), BOB.getId(), new Description("ab"));
        Relationship rel2 = new Relationship(BOB.getId(), BENSON.getId(), new Description("bb"));
        Relationship rel3 = new Relationship(BENSON.getId(), ALICE.getId(), new Description("ba"));
        Relationship rel4 = new Relationship(BENSON.getId(), CARL.getId(), new Description("bc"));
        modelManager.addRelationship(rel1);
        modelManager.addRelationship(rel2);
        modelManager.addRelationship(rel3);
        modelManager.addRelationship(rel4);
        modelManager.queryLink(ALICE.getId(), CARL.getId());
        assertEquals(3, modelManager.getRelationshipQuery().size());
        assertEquals(modelManager.getRelationshipQuery().get(0), new Pair<>(ALICE, rel3));
        assertEquals(modelManager.getRelationshipQuery().get(1), new Pair<>(BENSON, rel4));
        assertEquals(modelManager.getRelationshipQuery().get(2), new Pair<>(CARL, null));
    }

    @Test
    public void equals() {
        // sample addressBook with two person and typical tags
        AddressBook addressBook = new AddressBookBuilder()
                .withPerson(ALICE).withPerson(BENSON)
                .withTags(TypicalTags.getTypicalTags())
                .withRelationships(TypicalRelationships.getTypicalRelationships())
                .build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredPerson -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        // after resetting filter -> returns true
        assertTrue(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // different filteredTag -> returns false
        modelManager.updateFilteredTagList(tag -> tag.equals(TypicalTags.getTypicalTags().get(0)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));
        modelManager.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
        // after resetting filter -> returns true
        assertTrue(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // different filteredRelationship -> returns false
        modelManager.updateFilteredRelationshipList(rel ->
            rel.equals(TypicalRelationships.getTypicalRelationships().get(0)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));
        modelManager.updateFilteredRelationshipList(PREDICATE_SHOW_ALL_RELATIONSHIPS);
        // after resetting filter -> returns true
        assertTrue(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
