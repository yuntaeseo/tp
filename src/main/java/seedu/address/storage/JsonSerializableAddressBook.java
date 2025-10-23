package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.id.Id;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.tag.Tag;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TAG = "Tag list contains duplicate tag(s).";
    public static final String MESSAGE_DUPLICATE_RELATIONSHIP =
            "Relationship list contains duplicate relationships(s).";
    public static final String MESSAGE_NONEXISTENT_TAG_ID = "Tag ID does not exist in tag list.";
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedRelationship> relationships = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons and tags.
     */
    @JsonCreator
    public JsonSerializableAddressBook(
        @JsonProperty("persons") List<JsonAdaptedPerson> persons,
        @JsonProperty("tags") List<JsonAdaptedTag> tags,
        @JsonProperty("relationships") List<JsonAdaptedRelationship> relationships
    ) {
        this.persons.addAll(persons);
        this.tags.addAll(tags);
        this.relationships.addAll(relationships);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tags.addAll(source.getTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
        relationships.addAll(source.getRelationshipList().stream().map(JsonAdaptedRelationship::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedTag jsonAdaptedTag : tags) {
            Tag tag = jsonAdaptedTag.toModelType();
            if (addressBook.hasTag(tag)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TAG);
            }
            addressBook.addTag(tag);
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            if (!addressBook.hasTagIds(person.getTagIds())) {
                Set<Id> validTagIds = person.getTagIds()
                        .stream()
                        .filter(addressBook::hasTagId)
                        .collect(Collectors.toSet());
                person = new Person(
                        person.getId(),
                        person.getName(),
                        person.getPhone(),
                        person.getEmail(),
                        person.getAddress(),
                        validTagIds,
                        person.getNote()
                );

            }
            addressBook.addPerson(person);
        }

        for (JsonAdaptedRelationship jsonAdaptedRelationship : relationships) {
            Relationship relationship = jsonAdaptedRelationship.toModelType();
            if (addressBook.hasRelationship(relationship)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_RELATIONSHIP);
            }
            addressBook.addRelationship(relationship);
        }

        return addressBook;
    }

}
