package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagColor;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;

public class AddTagCommandTest {

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null));
    }

    @Test
    public void execute_tagAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTagAdded modelStub = new ModelStubAcceptingTagAdded();
        Tag validTag = new Tag(new TagName("Friends"), new TagDesc("Schoolmates"), new TagColor("0000FF"));

        CommandResult commandResult = new AddTagCommand(validTag).execute(modelStub);

        assertEquals(String.format(AddTagCommand.MESSAGE_SUCCESS, validTag),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTag), modelStub.tagsAdded);
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        Tag validTag = new Tag(new TagName("Friends"), new TagDesc("Schoolmates"), new TagColor("0000FF"));
        AddTagCommand addTagCommand = new AddTagCommand(validTag);
        ModelStub modelStub = new ModelStubWithTag(validTag);

        assertThrows(CommandException.class, "This tag already exists in the address book", () ->
                addTagCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tag friends = new Tag(new TagName("Friends"), new TagDesc("Schoolmates"), new TagColor("0000FF"));
        Tag coworkers = new Tag(new TagName("Coworkers"), new TagDesc("Office mates"), new TagColor("0000FF"));
        AddTagCommand addFriendsCommand = new AddTagCommand(friends);
        AddTagCommand addCoworkersCommand = new AddTagCommand(coworkers);

        // same object -> returns true
        assertTrue(addFriendsCommand.equals(addFriendsCommand));

        // same values -> returns true
        AddTagCommand addFriendsCommandCopy = new AddTagCommand(friends);
        assertTrue(addFriendsCommand.equals(addFriendsCommandCopy));

        // different types -> returns false
        assertFalse(addFriendsCommand.equals(1));

        // null -> returns false
        assertFalse(addFriendsCommand.equals(null));

        // different tag -> returns false
        assertFalse(addFriendsCommand.equals(addCoworkersCommand));
    }

    @Test
    public void toStringMethod() {
        Tag tag = new Tag(new TagName("Friends"), new TagDesc("Schoolmates"), new TagColor("0000FF"));
        AddTagCommand addTagCommand = new AddTagCommand(tag);
        String expected = AddTagCommand.class.getCanonicalName() + "{toAdd=" + tag + "}";
        assertEquals(expected, addTagCommand.toString());
    }

    // -------------------------------------------------------------------------
    // Model stubs
    // -------------------------------------------------------------------------
    /**
     * A default model stub where all methods fail.
     */
    private class ModelStub implements Model {

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

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("Not called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("Not called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("Not called.");
        }

        @Override
        public ObservableList<Tag> getFilteredTagList() {
            throw new AssertionError("Not called.");
        }

        @Override
        public ObservableList<Tag> getTagList() {
            throw new AssertionError("Not called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("Not called.");
        }

        @Override
        public void updateFilteredTagList(Predicate<Tag> predicate) {
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
    }

    /**
     * A Model stub that contains a single tag.
     */
    private class ModelStubWithTag extends ModelStub {

        private final Tag tag;

        ModelStubWithTag(Tag tag) {
            requireNonNull(tag);
            this.tag = tag;
        }

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return this.tag.isSameTag(tag);
        }
    }

    /**
     * A Model stub that always accepts the tag being added.
     */
    private class ModelStubAcceptingTagAdded extends ModelStub {

        final ArrayList<Tag> tagsAdded = new ArrayList<>();

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return tagsAdded.stream().anyMatch(tag::isSameTag);
        }

        @Override
        public void addTag(Tag tag) {
            requireNonNull(tag);
            tagsAdded.add(tag);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
