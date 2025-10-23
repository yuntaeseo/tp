package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.CompositePersonPredicate;
import seedu.address.model.person.FieldContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validSingleNameArg_returnsFindCommand() {
        // no leading and trailing whitespaces
        List<FieldContainsKeywordsPredicate> fieldPredicates = new ArrayList<>();
        FieldContainsKeywordsPredicate namePredicate = new FieldContainsKeywordsPredicate(
                PersonFieldExtractor.GET_NAME,
                Arrays.asList("Ali", "bob"));
        fieldPredicates.add(namePredicate);

        FindCommand expectedFindCommand = new FindCommand(new CompositePersonPredicate(fieldPredicates));
        assertParseSuccess(parser, "n/Ali n/bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ Alice \n \t n/\tBob  \t", expectedFindCommand);
    }

}
