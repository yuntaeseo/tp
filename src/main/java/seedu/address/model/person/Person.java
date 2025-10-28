package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.id.Id;
import seedu.address.model.id.IdManager;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    private static final IdManager idManager = new IdManager();

    // Identity fields
    private final Id id;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Id> tagIds = new HashSet<>();
    private final Note note;

    /**
     * Every field must be present and not null.
     */
    public Person(Id id, Name name, Phone phone, Email email, Address address, Set<Id> tagIds, Note note) {
        requireAllNonNull(name, phone, email, address, tagIds, note);
        idManager.setLargest(id);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tagIds.addAll(tagIds);
        this.note = note;
    }

    /**
     * Constructs a {@code Person}, without needing to provide an ID.
     * The ID will be automatically generated from {@code idManager}.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Id> tagIds, Note note) {
        this(idManager.getNewId(), name, phone, email, address, tagIds, note);
    }

    /**
     * Constructs a {@code Person} by copying the fields from {@code toCopy}.
     * A new ID will be automatically generated from {@code idManager}.
     */
    public Person(Person toCopy) {
        this(toCopy.name, toCopy.phone, toCopy.email, toCopy.address, toCopy.getTagIds(), toCopy.note);
    }

    public Id getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable set of tag IDs, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Id> getTagIds() {
        return Collections.unmodifiableSet(tagIds);
    }

    public Note getNote() {
        return note;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tagIds.equals(otherPerson.tagIds)
                && note.equals(otherPerson.note);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, name, phone, email, address, tagIds, note);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", id)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tagIds)
                .add("note", note)
                .toString();
    }

}
