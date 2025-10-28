package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.getLargestTagId;
import static seedu.address.testutil.TestUtil.getLastIndex;
import static seedu.address.testutil.TestUtil.getPerson;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.id.Id;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPerson_doesNotIncrementId() throws Exception {
        Person testPerson = new PersonBuilder().build();
        Id expectedId = new Id(testPerson.getId().value + 1);

        // Invalid tag ID
        Person invalidPerson = new PersonBuilder().withId(AddCommandParser.DUMMY_ID)
                .withTags(getLargestTagId(model) + 1).build();
        AddCommand badAddCommand = new AddCommand(invalidPerson);
        assertThrows(CommandException.class, AddCommand.MESSAGE_TAG_NOT_FOUND, () -> badAddCommand.execute(model));

        Person validPerson = new PersonBuilder().withId(AddCommandParser.DUMMY_ID).withName("test").build();
        AddCommand goodAddCommand = new AddCommand(validPerson);
        goodAddCommand.execute(model);

        Person addedPerson = getPerson(model, getLastIndex(model));
        assertEquals(expectedId, addedPerson.getId());
    }
}
