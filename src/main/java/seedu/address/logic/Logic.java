package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.tag.Tag;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of persons.
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the entire list of persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the filtered list of tags.
     */
    ObservableList<Tag> getFilteredTagList();

    /**
     * Returns an unmodifiable view of the entire list of tags.
     */
    ObservableList<Tag> getTagList();

    /**
     * Returns an unmodifiable view of the filtered list of relationships.
     */
    ObservableList<Relationship> getFilteredRelationshipList();

    /**
     * Returns an unmodifiable view of the entire list of relationships.
     */
    ObservableList<Relationship> getRelationshipList();

    /**
     * Returns an unmodifiable view of the most recent relationship query results.
     */
    ObservableList<Pair<Person, Relationship>> getRelationshipQuery();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
