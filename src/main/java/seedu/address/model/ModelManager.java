package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.description.Description;
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
    private final ObservableList<Pair<Person, Relationship>> internalRelQuery;
    private final ObservableList<Pair<Person, Relationship>> relQuery;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.filteredTags = new FilteredList<>(this.addressBook.getTagList());
        this.filteredRelationships = new FilteredList<>(this.addressBook.getRelationshipList());
        this.internalRelQuery = FXCollections.observableArrayList();
        this.relQuery = FXCollections.unmodifiableObservableList(internalRelQuery);
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
    public ObservableList<Pair<Person, Relationship>> getRelationshipQuery() {
        return relQuery;
    }

    @Override
    public void queryImmediateRelationship(Id id) {
        requireNonNull(id);
        internalRelQuery.clear();

        ObservableList<Person> people = addressBook.getPersonList();
        ObservableList<Relationship> relationships = addressBook.getRelationshipList();

        for (Relationship rel : relationships) {
            if (rel.getPart1().equals(id) || rel.getPart2().equals(id)) {
                Id otherId = rel.getPart1().equals(id) ? rel.getPart2() : rel.getPart1();
                Person other = people.filtered(person -> person.getId().equals(otherId)).get(0);
                internalRelQuery.add(new Pair<>(other, rel));
            }
        }
    }

    @Override
    public void queryLink(Id person1, Id person2) {
        requireAllNonNull(person1, person2);
        internalRelQuery.clear();

        // Algorithm: Breath-First Search using Adjacency List

        // Setting up the list of nodes
        Person[] people = addressBook.getPersonList().toArray(new Person[addressBook.getPersonList().size()]);
        HashMap<Id, Integer> idIndexMap = new HashMap<>();
        for (int i = 0; i < people.length; i++) {
            idIndexMap.put(people[i].getId(), i);
        }

        // adjList[i] is the list of indexes of all Person adjacent (immediately related) to people[i]
        // Setting up the adjacency list
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            adjList.add(new ArrayList<>());
        }
        ObservableList<Relationship> relationships = addressBook.getRelationshipList();
        for (Relationship rel : relationships) {
            adjList.get(idIndexMap.get(rel.getPart1())).add(idIndexMap.get(rel.getPart2()));
            adjList.get(idIndexMap.get(rel.getPart2())).add(idIndexMap.get(rel.getPart1()));
        }

        // Setting up the variables needed for BFS traversal
        HashSet<Integer> visited = new HashSet<>();
        Queue<ArrayList<Integer>> nextPath = new ArrayDeque<>();
        ArrayList<Integer> result = new ArrayList<>();
        Integer start = idIndexMap.get(person1);
        Integer end = idIndexMap.get(person2);
        nextPath.add(new ArrayList<>());
        nextPath.peek().add(start);

        // Actual BFS algorithm
        while (!nextPath.isEmpty()) {
            ArrayList<Integer> path = nextPath.remove();
            Integer last = path.get(path.size() - 1);
            visited.add(last);

            if (last.equals(end)) {
                result = path;
                break;
            }

            for (Integer adj : adjList.get(last)) {
                if (!visited.contains(adj)) {
                    ArrayList<Integer> newPath = new ArrayList<>(path);
                    newPath.add(adj);
                    nextPath.add(newPath);
                }
            }
        }

        // There is no link between person1 and person2
        if (result.isEmpty()) {
            return;
        }

        // Trace the route that we took and fill up internalRelQuery
        for (int i = 0; i < result.size() - 1; i++) {
            Person cur = people[result.get(i)];
            Person next = people[result.get(i + 1)];
            internalRelQuery.add(
                new Pair<>(
                    cur,
                    relationships.filtered(rel ->
                        rel.isSameRelationship(new Relationship(cur.getId(), next.getId(), new Description(""))))
                            .get(0)
                )
            );
        }

        internalRelQuery.add(new Pair<>(people[end], null));
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
