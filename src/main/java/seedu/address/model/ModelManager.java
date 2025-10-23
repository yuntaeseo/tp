package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.id.Id;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Tag> filteredTags;
    private final FilteredList<Relationship> filteredRelationships;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTags = new FilteredList<>(this.addressBook.getTagList());
        filteredRelationships = new FilteredList<>(this.addressBook.getRelationshipList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }



    //  NOTE: USER PREFERENCES

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }



    //  NOTE: ADDRESS BOOK

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }



    //  NOTE: PERSON

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return addressBook.getPersonList();
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasPersonWithId(Id id) {
        requireNonNull(id);
        return addressBook.hasPersonWithId(id);
    }


    //  NOTE: TAGS

    @Override
    public ObservableList<Tag> getFilteredTagList() {
        return filteredTags;
    }

    @Override
    public void updateFilteredTagList(Predicate<Tag> predicate) {
        requireNonNull(predicate);
        filteredTags.setPredicate(predicate);
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return addressBook.getTagList();
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return addressBook.hasTag(tag);
    }

    @Override
    public boolean hasTagId(Id id) {
        return addressBook.hasTagId(id);
    }

    @Override
    public boolean hasTagIds(Collection<Id> ids) {
        return addressBook.hasTagIds(ids);
    }

    @Override
    public void addTag(Tag tag) {
        addressBook.addTag(tag);
        updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
    }

    @Override
    public void deleteTag(Tag tag) {
        addressBook.removeTag(tag);
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        addressBook.setTag(target, editedTag);
    }



    //  NOTE: RELATIONSHIPS

    @Override
    public ObservableList<Relationship> getFilteredRelationshipList() {
        return filteredRelationships;
    }

    @Override
    public ObservableList<Relationship> getRelationshipList() {
        return addressBook.getRelationshipList();
    }

    @Override
    public void updateFilteredRelationshipList(Predicate<Relationship> predicate) {
        requireNonNull(predicate);
        filteredRelationships.setPredicate(predicate);
    }

    @Override
    public boolean hasRelationship(Relationship relationship) {
        requireNonNull(relationship);
        return addressBook.hasRelationship(relationship);
    }

    @Override
    public void addRelationship(Relationship relationship) {
        addressBook.addRelationship(relationship);
        updateFilteredRelationshipList(PREDICATE_SHOW_ALL_RELATIONSHIPS);
    }

    @Override
    public void deleteRelationship(Relationship relationship) {
        addressBook.removeRelationship(relationship);
    }

    @Override
    public void removeRelationshipsIfContainsPerson(Id personId) {
        addressBook.removeRelationshipsIfContainsPerson(personId);
    }

    @Override
    public void setRelationship(Relationship target, Relationship editedRelationship) {
        addressBook.setRelationship(target, editedRelationship);
    }



    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherManager = (ModelManager) other;
        return addressBook.equals(otherManager.addressBook)
                && userPrefs.equals(otherManager.userPrefs)
                && filteredPersons.equals(otherManager.filteredPersons)
                && filteredTags.equals(otherManager.filteredTags)
                && filteredRelationships.equals(otherManager.filteredRelationships);
    }

}
