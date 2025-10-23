package seedu.address.model;

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.id.Id;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Tag> PREDICATE_SHOW_ALL_TAGS = unused -> true;
    Predicate<Relationship> PREDICATE_SHOW_ALL_RELATIONSHIPS = unused -> true;



    //  NOTE: USER PREFERENCES

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();



    //  NOTE: ADDRESS BOOK

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    ReadOnlyAddressBook getAddressBook();



    //  NOTE: PERSON

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns an unmodifiable view of the entire person list.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns true if a person with the given Id exists in the address book.
     */
    boolean hasPersonWithId(Id id);


    //  NOTE: TAGS

    /** Returns an unmodifiable view of the filtered tag list */
    ObservableList<Tag> getFilteredTagList();

    /**
     * Updates the filter of the filtered tag list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTagList(Predicate<Tag> predicate);

    /**
     * Returns an unmodifiable view of the entire tag list.
     */
    ObservableList<Tag> getTagList();

    /**
     * Returns true if a tag with the same identity as {@code tag} exists in the address book.
     */
    boolean hasTag(Tag tag);

    /**
     * Returns true if a tag ID exists in the address book.
     */
    boolean hasTagId(Id id);

    /**
     * Returns true if all tag IDs in {@code ids} exist in the address book.
     */
    boolean hasTagIds(Collection<Id> ids);

    /**
     * Adds the given tag.
     * {@code tag} must not already exist in the address book.
     */
    void addTag(Tag tag);

    /**
     * Deletes the given tag.
     * The tag must exist in the address book.
     */
    void deleteTag(Tag tag);

    /**
     * Replaces the given tag {@code target} with {@code editedTag}.
     * {@code target} must exist in the address book.
     * The identity of {@code editedTag} must not be the same as another existing tag in the address book.
     */
    void setTag(Tag target, Tag editedTag);



    //  NOTE: RELATIONSHIPS

    /** Returns an unmodifiable view of the filtered relationship list */
    ObservableList<Relationship> getFilteredRelationshipList();

    /**
     * Updates the filter of the filtered relationship list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRelationshipList(Predicate<Relationship> predicate);

    /**
     * Returns an unmodifiable view of the entire relationship list.
     */
    ObservableList<Relationship> getRelationshipList();

    /**
     * Returns true if a relationship with the same identity as {@code relationship} exists in the address book.
     */
    boolean hasRelationship(Relationship relationship);

    /**
     * Adds the given relationship.
     * {@code relationship} must not already exist in the address book.
     */
    void addRelationship(Relationship relationship);

    /**
     * Deletes the given relationship.
     * The relationship must exist in the address book.
     */
    void deleteRelationship(Relationship relationship);

    /**
     * Replaces the given relationship {@code target} with {@code editedRelationship}.
     * {@code target} must exist in the address book.
     * The identity of {@code editedRelationship} must not be the same as another existing relationship
     * in the address book.
     */
    void setRelationship(Relationship target, Relationship editedRelationship);

}
