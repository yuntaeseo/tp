package seedu.address.logic.commands;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagColor;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTagCommand}.
 */
public class DeleteTagCommandTest {

    private ModelStubWithTags modelStub;

    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    @BeforeEach
    public void setup() {
        tag1 = new Tag(1, new TagName("Friends"), new TagDesc("Schoolmates"), new TagColor("0000FF"));
        tag2 = new Tag(2, new TagName("Work"), new TagDesc("Office mates"), new TagColor("0000FF"));
        tag3 = new Tag(3, new TagName("Family"), new TagDesc("Close relatives"), new TagColor("0000FF"));
        modelStub = new ModelStubWithTags(List.of(tag1, tag2, tag3));
    }

    @Test
    public void execute_validId_success() throws Exception {
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(2);
        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_SUCCESS, tag2);

        ModelStubWithTags expectedModel = new ModelStubWithTags(List.of(tag1, tag3));

        assertCommandSuccess(deleteTagCommand, modelStub, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidId_throwsCommandException() {
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(99);

        java.util.List<Tag> before = List.copyOf(modelStub.getFilteredTagList());

        seedu.address.logic.commands.exceptions.CommandException ex =
                assertThrows(seedu.address.logic.commands.exceptions.CommandException.class,
                        () -> deleteTagCommand.execute(modelStub));
        assertEquals(DeleteTagCommand.MESSAGE_TAG_NOT_FOUND, ex.getMessage());

        assertEquals(before, modelStub.getFilteredTagList());
    }
    
    @Test
    public void equals() {
        DeleteTagCommand deleteFirstCommand = new DeleteTagCommand(1);
        DeleteTagCommand deleteSecondCommand = new DeleteTagCommand(2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTagCommand deleteFirstCommandCopy = new DeleteTagCommand(1);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different ID -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        int id = 1;
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(id);
        String expected = DeleteTagCommand.class.getCanonicalName() + "{idToDelete=" + id + "}";
        assertEquals(expected, deleteTagCommand.toString());
    }

    // -------------------------------------------------------------------------
    // Model Stub for Tags
    // -------------------------------------------------------------------------
    private static class ModelStubWithTags extends ModelStub {

        private final ObservableList<Tag> tags = FXCollections.observableArrayList();
        private final seedu.address.model.AddressBook backingAddressBook = new seedu.address.model.AddressBook();

        ModelStubWithTags(List<Tag> initialTags) {
            tags.addAll(initialTags);
            initialTags.forEach(backingAddressBook::addTag);
        }

        @Override
        public ObservableList<Tag> getFilteredTagList() {
            return tags;
        }

        @Override
        public void deleteTag(Tag tag) {
            tags.remove(tag);
            backingAddressBook.removeTag(tag);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return backingAddressBook;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ModelStubWithTags)) {
                return false;
            }
            ModelStubWithTags that = (ModelStubWithTags) other;
            return this.tags.equals(that.tags);
        }
    }

    /**
     * Default Model stub (all methods fail unless overridden).
     */
    private static class ModelStub implements Model {

        @Override
        public void setUserPrefs(seedu.address.model.ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public seedu.address.model.ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public seedu.address.commons.core.GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(seedu.address.commons.core.GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public java.nio.file.Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(java.nio.file.Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(seedu.address.model.ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public seedu.address.model.ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(seedu.address.model.person.Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(seedu.address.model.person.Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(seedu.address.model.person.Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(seedu.address.model.person.Person target, seedu.address.model.person.Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public javafx.collections.ObservableList<seedu.address.model.person.Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(java.util.function.Predicate<seedu.address.model.person.Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public javafx.collections.ObservableList<seedu.address.model.tag.Tag> getFilteredTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public javafx.collections.ObservableList<seedu.address.model.tag.Tag> getTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTagList(java.util.function.Predicate<seedu.address.model.tag.Tag> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTag(seedu.address.model.tag.Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTag(seedu.address.model.tag.Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(seedu.address.model.tag.Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTag(seedu.address.model.tag.Tag target, seedu.address.model.tag.Tag editedTag) {
            throw new AssertionError("This method should not be called.");
        }
    }

}
