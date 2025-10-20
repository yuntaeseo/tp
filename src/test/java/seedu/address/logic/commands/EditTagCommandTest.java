package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.id.Id;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagColor;
import seedu.address.model.tag.TagDesc;
import seedu.address.model.tag.TagName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EditTagCommand}.
 */
public class EditTagCommandTest {

    private ModelStubWithTags modelStub;
    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    @BeforeEach
    public void setup() {
        tag1 = new Tag(new Id(1), new TagName("Friends"), new TagDesc("Schoolmates"), new TagColor("0000FF"));
        tag2 = new Tag(new Id(2), new TagName("Work"), new TagDesc("Office mates"), new TagColor("0000FF"));
        tag3 = new Tag(new Id(3), new TagName("Family"), new TagDesc("Close relatives"), new TagColor("0000FF"));
        modelStub = new ModelStubWithTags(List.of(tag1, tag2, tag3));
    }

    @Test
    public void execute_allFieldsSpecified_success() throws Exception {
        TagName newName = new TagName("BestFriends");
        TagDesc newDesc = new TagDesc("High school buddies");
        TagColor newColor = new TagColor("00FF00");

        EditTagCommand editCommand = new EditTagCommand(1, newName, newDesc, newColor);
        Tag expectedEdited = new Tag(new Id(1), newName, newDesc, newColor);

        String expectedMessage = String.format("Edited Tag: %s", expectedEdited);

        ModelStubWithTags expectedModel = new ModelStubWithTags(List.of(expectedEdited, tag2, tag3));
        assertCommandSuccess(editCommand, modelStub, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() throws Exception {
        TagDesc newDesc = new TagDesc("Gaming friends only");

        EditTagCommand editCommand = new EditTagCommand(1, null, newDesc, null);
        Tag expectedEdited = new Tag(new Id(1), tag1.getName(), newDesc, tag1.getColor());

        String expectedMessage = String.format(EditTagCommand.MESSAGE_EDIT_SUCCESS, expectedEdited);

        ModelStubWithTags expectedModel = new ModelStubWithTags(List.of(expectedEdited, tag2, tag3));
        assertCommandSuccess(editCommand, modelStub, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidId_throwsCommandException() {
        EditTagCommand editCommand = new EditTagCommand(99, new TagName("Invalid"), null, null);

        java.util.List<Tag> before = List.copyOf(modelStub.getFilteredTagList());

        seedu.address.logic.commands.exceptions.CommandException ex =
                assertThrows(seedu.address.logic.commands.exceptions.CommandException.class, () ->
                        editCommand.execute(modelStub));
        assertEquals(EditTagCommand.MESSAGE_TAG_NOT_FOUND, ex.getMessage());

        assertEquals(before, modelStub.getFilteredTagList());
    }

    @Test
    public void execute_noFieldsProvided_throwsCommandException() {
        EditTagCommand editCommand = new EditTagCommand(1, null, null, null);

        java.util.List<Tag> before = List.copyOf(modelStub.getFilteredTagList());

        seedu.address.logic.commands.exceptions.CommandException ex =
                assertThrows(seedu.address.logic.commands.exceptions.CommandException.class, () ->
                        editCommand.execute(modelStub));
        assertEquals("At least one field to edit must be provided.", ex.getMessage());

        assertEquals(before, modelStub.getFilteredTagList());
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        EditTagCommand editCommand = new EditTagCommand(1, new TagName(tag2.getName().value), null, null);

        java.util.List<Tag> before = List.copyOf(modelStub.getFilteredTagList());

        seedu.address.logic.commands.exceptions.CommandException ex =
                assertThrows(seedu.address.logic.commands.exceptions.CommandException.class, () ->
                        editCommand.execute(modelStub));
        System.out.println(ex.getMessage());
        assertEquals(EditTagCommand.MESSAGE_DUPLICATE_TAG, ex.getMessage());

        assertEquals(before, modelStub.getFilteredTagList());
    }

    @Test
    public void equals() {
        TagName name = new TagName("BestFriends");
        TagDesc desc = new TagDesc("High school buddies");
        TagColor color = new TagColor("00FF00");

        EditTagCommand editFirstCommand = new EditTagCommand(1, name, desc, color);
        EditTagCommand editSecondCommand = new EditTagCommand(2, name, desc, color);

        // same object -> true
        assertTrue(editFirstCommand.equals(editFirstCommand));

        // same values -> true
        EditTagCommand copy = new EditTagCommand(1, name, desc, color);
        assertTrue(editFirstCommand.equals(copy));

        // different types -> false
        assertFalse(editFirstCommand.equals(1));

        // null -> false
        assertFalse(editFirstCommand.equals(null));

        // different id -> false
        assertFalse(editFirstCommand.equals(editSecondCommand));
    }

    @Test
    public void toStringMethod() {
        TagName name = new TagName("Friends");
        TagDesc desc = new TagDesc("Old mates");
        TagColor color = new TagColor("00FF00");

        EditTagCommand editCommand = new EditTagCommand(1, name, desc, color);
        String expected = EditTagCommand.class.getCanonicalName()
                + "{idToEdit=1, newName=" + name + ", newDesc=" + desc + ", newColor=" + color + "}";
        assertEquals(expected, editCommand.toString());
    }

    // -------------------------------------------------------------------------
    // Model Stub for Tags
    // -------------------------------------------------------------------------
    private static class ModelStubWithTags extends ModelStub {

        private final ObservableList<Tag> tags = FXCollections.observableArrayList();

        ModelStubWithTags(List<Tag> initialTags) {
            tags.addAll(initialTags);
        }

        @Override
        public ObservableList<Tag> getFilteredTagList() {
            return tags;
        }

        @Override
        public boolean hasTag(seedu.address.model.tag.Tag tag) {
            return tags.stream().anyMatch(tag::isSameTag);
        }

        @Override
        public void deleteTag(Tag tag) {
            tags.remove(tag);
        }

        @Override
        public void addTag(Tag tag) {
            tags.add(tag);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
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

        @Override
        public void setTag(Tag target, Tag editedTag) {
            int index = tags.indexOf(target);
            if (index >= 0) {
                tags.set(index, editedTag);
            }
        }
    }

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
        public void setPerson(seedu.address.model.person.Person target, seedu.address.model.person.Person
                editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public javafx.collections.ObservableList<seedu.address.model.person.Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(java.util.function.Predicate<seedu.address.model.person.Person>
                predicate) {
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
