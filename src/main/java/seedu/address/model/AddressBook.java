package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.relationship.UniqueRelationshipList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueTagList tags;
    private final UniqueRelationshipList relationships;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tags = new UniqueTagList();
        relationships = new UniqueRelationshipList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTags(newData.getTagList());
        setRelationships(newData.getRelationshipList());
    }


    //  NOTE: PERSON

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }



    //  NOTE: TAGS

    /**
     * Replaces the contents of the tag list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) {
        this.tags.setTags(tags);
    }

    /**
     * Returns true if a tag with the same identity as {@code tag} exists in the address book.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tags.contains(tag);
    }

    /**
     * Adds a tag into the address book.
     * The tag must not already exist in the address book.
     */
    public void addTag(Tag tag) {
        tags.add(tag);
    }

    /**
     * Replaces the given tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the address book.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in the address book.
     */
    public void setTag(Tag target, Tag editedTag) {
        tags.setTag(target, editedTag);
    }

    /**
     * Removes {@code tag} from this {@code AddressBook}.
     * {@code tag} must exist in the address book.
     */
    public void removeTag(Tag tag) {
        tags.remove(tag);
    }



    //  NOTE: RELATIONSHIPS

    /**
     * Replaces the contents of the relationship list with {@code relationships}.
     * {@code relationships} must not contain duplicate relationships.
     */
    public void setRelationships(List<Relationship> relationships) {
        this.relationships.setRelationships(relationships);
    }

    /**
     * Returns true if a relationship with the same identity as {@code relationship} exists in the address book.
     */
    public boolean hasRelationship(Relationship relationship) {
        requireNonNull(relationship);
        return relationships.contains(relationship);
    }

    /**
     * Adds a relationship into the address book.
     * The relationship must not already exist in the address book.
     */
    public void addRelationship(Relationship relationship) {
        relationships.add(relationship);
    }

    /**
     * Replaces the given relationship {@code target} in the list with {@code editedRelationship}.
     * {@code target} must exist in the address book.
     * The identity of {@code editedRelationship} must not be the same as another existing
     * relationship in the address book.
     */
    public void setRelationship(Relationship target, Relationship editedRelationship) {
        relationships.setRelationship(target, editedRelationship);
    }

    /**
     * Removes {@code relationship} from this {@code AddressBook}.
     * {@code relationship} must exist in the address book.
     */
    public void removeRelationship(Relationship relationship) {
        relationships.remove(relationship);
    }



    //  NOTE: UTILS

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("tags", tags)
                .add("relationships", relationships)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return tags.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Relationship> getRelationshipList() {
        return relationships.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons)
            && tags.equals(otherAddressBook.tags)
            && relationships.equals(otherAddressBook.relationships);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
