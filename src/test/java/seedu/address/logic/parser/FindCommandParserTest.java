package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.CompositePersonPredicate;
import seedu.address.model.person.FieldContainsKeywordsPredicate;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                "At least one field to find must be provided. " +
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_withPreamble_throwsParseException() {
        assertParseFailure(parser, " somePreamble n/Ali n/bob",
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgWithPrefix_throwsParseException() {
        assertParseFailure(parser, " n/   a/ \t e/  ",
                "At least one field to find must be provided. " +
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // Note the space " " in front of the prefix
        String inputNoWhiteSpace = " n/Ali n/bob";
        String inputMultipleWhitespaces = " n/ Ali \n \t n/\tbob  \t";

        CompositePersonPredicate expectedPredicates = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_NAME, List.of("Ali", "bob"))
                ));
        FindCommand expectedCommand = new FindCommand(expectedPredicates);

        assertParseSuccess(parser, inputNoWhiteSpace, expectedCommand);
        assertParseSuccess(parser, inputMultipleWhitespaces, expectedCommand);
    }

    @Test
    public void parse_multipleFields_returnsFindCommand() {
        String input = " n/Alice p/9123 e/@example.com a/little india a/#35 t/1 t/10" ;
        // Note the order of the address should not change, since List put them in order of appearance
        String inputDifferentOrder = " a/little india a/#35  n/Alice t/1 \t e/@example.com t/10 \n p/9123";

        CompositePersonPredicate expectedPredicates = new CompositePersonPredicate(List.of(
                new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_NAME, List.of("Alice")),
                new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_PHONE, List.of("9123")),
                new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_EMAIL, List.of("@example.com")),
                new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_ADDRESS, List.of("little india", "#35")),
                new FieldContainsKeywordsPredicate(PersonFieldExtractor.GET_TAGS, List.of("1", "10"))
                ));
        FindCommand expectedCommand = new FindCommand(expectedPredicates);

        assertParseSuccess(parser, input, expectedCommand);
        assertParseSuccess(parser, inputDifferentOrder, expectedCommand);
    }
}
