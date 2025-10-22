package seedu.address.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.tag.Tag;

/**
 * A non-functional model for testing purposes.
 *
 * Inherit from this class with appropriate functions for your tests.
 */
public class ModelStub implements Model {

    //  NOTE: USER PREFERENCES
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("Not called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("Not called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("Not called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("Not called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("Not called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("Not called.");
    }



    //  NOTE: ADDRESS BOOK

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("Not called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("Not called.");
    }



    //  NOTE: PERSON

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("Not called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("Not called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("Not called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("Not called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("Not called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("Not called.");
    }

    @Override
    public boolean hasPersonWithId(seedu.address.model.id.Id id) {
        throw new AssertionError("Not called.");
    }



    //  NOTE: TAGS

    @Override
    public ObservableList<Tag> getFilteredTagList() {
        throw new AssertionError("Not called.");
    }

    @Override
    public void updateFilteredTagList(Predicate<Tag> predicate) {
        throw new AssertionError("Not called.");
    }

    @Override
    public ObservableList<Tag> getTagList() {
        throw new AssertionError("Not called.");
    }

    @Override
    public boolean hasTag(Tag tag) {
        throw new AssertionError("Not called.");
    }

    @Override
    public void addTag(Tag tag) {
        throw new AssertionError("Not called.");
    }

    @Override
    public void deleteTag(Tag tag) {
        throw new AssertionError("Not called.");
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        throw new AssertionError("Not called.");
    }



    //  NOTE: RELATIONSHIPS

    @Override
    public ObservableList<Relationship> getFilteredRelationshipList() {
        throw new AssertionError("Not called.");
    }

    @Override
    public void updateFilteredRelationshipList(Predicate<Relationship> predicate) {
        throw new AssertionError("Not called.");
    }

    @Override
    public boolean hasRelationship(Relationship relationship) {
        throw new AssertionError("Not called.");
    }

    @Override
    public void addRelationship(Relationship relationship) {
        throw new AssertionError("Not called.");
    }

    @Override
    public void deleteRelationship(Relationship relationship) {
        throw new AssertionError("Not called.");
    }

    @Override
    public void setRelationship(Relationship target, Relationship editedRelationship) {
        throw new AssertionError("Not called.");
    }

}

