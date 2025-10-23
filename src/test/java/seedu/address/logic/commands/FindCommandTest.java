package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.PersonFieldExtractor.GET_ADDRESS;
import static seedu.address.logic.parser.PersonFieldExtractor.GET_EMAIL;
import static seedu.address.logic.parser.PersonFieldExtractor.GET_NAME;
import static seedu.address.logic.parser.PersonFieldExtractor.GET_PHONE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.CompositePersonPredicate;
import seedu.address.model.person.FieldContainsKeywordsPredicate;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    // execute(): single field - OR relationships within the field keywords (name1 OR name2)
    @Test
    public void execute_nameSubstrings_matchEither_listAllMatches() {
        // Name: “Ali” OR “Mei”
        // Match 4: ALICE, BENSON (Benson Meier), DANIEL (Daniel Meier), HOON Meier
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        CompositePersonPredicate pred = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(GET_NAME, List.of("Ali", "Mei"))
        ));

        FindCommand cmd = new FindCommand(pred);
        expectedModel.updateFilteredPersonList(pred);

        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
        assertEquals(List.of(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    // execute(): multiple fields - AND relationships across fields (name)
    // Composite example: (name1 OR name2) AND phone AND (address1 OR address2 OR address3)
    @Test
    public void execute_nameAndAddress_mustMatchBoth_listAllIntersectingMatches() {
        // Name: "Benso" AND Address: "Clementi"
        // Match 1: BENSON
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        CompositePersonPredicate pred = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(GET_NAME,  List.of("Benso")),
                new FieldContainsKeywordsPredicate(GET_ADDRESS, List.of("Clementi"))
        ));

        FindCommand cmd = new FindCommand(pred);
        expectedModel.updateFilteredPersonList(pred);

        assertCommandSuccess(cmd, model, expectedMessage, expectedModel);
        assertEquals(List.of(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_phoneSubstring_listAllMatches() {
        // Phone: "9876"
        // Match 1: BENSON (phone "98765432")
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);

        CompositePersonPredicate predicate = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(GET_PHONE, List.of("9876"))
        ));

        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_nameAndEmailSubstrings_BothMustMatch_listAllIntersectingMatches() {
        // Name: “son” (BENSON) AND email: “@example.com”
        // Match 1: BENSON (email “johnd@example.com”)
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);

        CompositePersonPredicate predicate = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(GET_NAME,  List.of("son")),
                new FieldContainsKeywordsPredicate(GET_EMAIL, List.of("@example.com"))
        ));

        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(BENSON), model.getFilteredPersonList());
    }

    // Edge case that would not happen in real execution, but this behaviour is expected
    // Since if no filter is provided, you should expect all values to be displayed.
    @Test
    public void execute_zeroPredicate_returnsAll() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);

        CompositePersonPredicate predicate = new CompositePersonPredicate(Collections.emptyList());

        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(List.of(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    void execute_nullModel_throwsNullPointerException() {
        CompositePersonPredicate pred = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(GET_NAME, List.of("Alice"))
        ));
        FindCommand cmd = new FindCommand(pred);

        assertThrows(NullPointerException.class, () -> cmd.execute(null));
    }

    @Test
    void equals_samePredicate_true() {
        CompositePersonPredicate predA = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(GET_ADDRESS, List.of("street"))
        ));
        FindCommand a = new FindCommand(predA);
        FindCommand b = new FindCommand(new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(GET_ADDRESS, List.of("street"))
        )));

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(null, a);
        assertNotEquals(new Object(), a);
    }

    @Test
    void equals_differentPredicates_false() {
        FindCommand a = new FindCommand(new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(GET_NAME, List.of("Alice"))
        )));
        FindCommand b = new FindCommand(new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(GET_NAME, List.of("Bob"))
        )));
        assertNotEquals(a, b);
    }

    @Test
    public void toStringMethod() {
        CompositePersonPredicate pred = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(GET_EMAIL, List.of("@example.com"))
        ));
        FindCommand cmd = new FindCommand(pred);

        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + pred + "}";
        assertEquals(expected, cmd.toString());
    }
}
